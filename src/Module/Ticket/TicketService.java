package Module.Ticket;

import Manager.Entity.DatabaseEntity;
import Manager.Interface.IDatabaseControllService;
import Manager.Interface.IDatabaseService;
import Manager.Service.DatabaseControllService;
import Manager.Service.DatabaseService;
import Module.Employee.EmployeeService;
import Module.Enum.EnumValue;
import Module.Enum.ReadStatus;
import Module.Enum.TicketStatus;
import Module.File.FileService;
import Module.Location.LocationEntity;
import Module.Location.LocationService;
import Module.TicketImage.TicketImageService;
import Module.TicketRead.TicketReadEntity;
import Module.TicketRead.TicketReadService;
import Module.TicketRelater.TicketRelaterEntity;
import Module.TicketRelater.TicketRelaterService;
import Module.TicketRelater.TicketrelaterModel;
import Module.TicketThread.TicketThreadEntity;
import Module.TicketThread.TicketthreadModel;
import Module.User.TokenEntity;
import Module.User.TokenService;
import Module.User.UserEntity;
import Module.User.UserService;
import com.google.common.collect.Lists;
import com.mysql.cj.core.util.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Cookie;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class TicketService {
    private static SessionFactory factory;
    private static int currentActive;
    LocationService locationService = new LocationService();
    TokenService tokenService = new TokenService();
    UserService userService = new UserService();
    EmployeeService employeeService = new EmployeeService();
    TicketRelaterService ticketRelaterService = new TicketRelaterService();
    TicketImageService ticketImageService = new TicketImageService();
    TicketReadService ticketReadService = new TicketReadService();
    FileService fileService = new FileService();

    public TicketService(SessionFactory factory) {
        this.factory = factory;
    }

    public TicketService() {
        if (factory == null || currentActive != DatabaseEntity.Active) {
            IDatabaseService databaseService = new DatabaseService();
            IDatabaseControllService databaseControllService = new DatabaseControllService();
            factory = databaseControllService.createConfiguration(databaseService.get(DatabaseEntity.Active)).buildSessionFactory();
            currentActive = DatabaseEntity.Active;
        }
    }

    public static void setFactory(SessionFactory factory) {
        TicketService.factory = factory;
    }


    public TicketEntity get(int id) {
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<TicketModel> criteria = builder.createQuery(TicketModel.class);
        Root<TicketModel> TicketModels = criteria.from(TicketModel.class);
        criteria.where(builder.equal(TicketModels.get("id"), id));
        try {
            TicketModel ticketModel = session.createQuery(criteria).getSingleResult();
            TicketEntity ticketEntity = new TicketEntity(ticketModel,ticketModel.getTicketrelatersById());
            return ticketEntity;
        } catch (NoResultException e) {
            return null;
        } finally {
            session.close();
        }
    }

    public TicketEntity getFullInfo(int id) {
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<TicketModel> criteria = builder.createQuery(TicketModel.class);
        Root<TicketModel> TicketModels = criteria.from(TicketModel.class);
        criteria.where(builder.equal(TicketModels.get("id"), id));
        try {
            TicketModel ticketModel = session.createQuery(criteria).getSingleResult();
            TicketEntity ticketEntity = new TicketEntity(ticketModel, ticketModel.getTicketthreadsById(), ticketModel.getTicketimagesById(), ticketModel.getEmployeeByCreateEmployeeId(), ticketModel.getLocationByLocationId(), ticketModel.getTicketrelatersById());
            if (ticketEntity.assignedEmployeeId != null)
                ticketEntity.assignedEmployeeEntity = employeeService.get(ticketEntity.assignedEmployeeId);
            if (ticketEntity.ticketRelaterEntities != null && ticketEntity.ticketRelaterEntities.size() > 0) {
                ticketEntity.ticketRelaterEntities.parallelStream().forEach(ticketRelaterEntity -> {
                    ticketRelaterEntity.employeeEntity = employeeService.get(ticketRelaterEntity.employeeId);
                });
            }
            if (ticketEntity.ticketImageEntities != null && ticketEntity.ticketImageEntities.size() > 0) {
                ticketEntity.ticketImageEntities.parallelStream().forEach(ticketImageEntity -> {
                    ticketImageEntity.fileEntity = fileService.get(ticketImageEntity.fileId);
                });
            }
            if (ticketEntity.ticketThreadEntities != null && ticketEntity.ticketThreadEntities.size() > 0) {
                ticketEntity.ticketThreadEntities.parallelStream().forEach(ticketThreadEntity -> {
                    ticketThreadEntity.employeeEntity = employeeService.get(ticketThreadEntity.employeeId);
                });
            }
            return ticketEntity;
        } catch (NoResultException e) {
            return null;
        } finally {
            session.close();
        }
    }


    public TicketEntity create(Cookie tokenKey, TicketEntity ticketEntity) {
        Transaction tx = null;
        if (StringUtils.isNullOrEmpty(ticketEntity.content) || ticketEntity.content.equals("<br>"))
            throw new BadRequestException("Nội dung không được để trống!");
        if (StringUtils.isNullOrEmpty(ticketEntity.subject))
            throw new BadRequestException("Tên công việc không được để trống!");
        if (ticketEntity.priority == null) throw new BadRequestException("Chưa chọn mức độ ưu tiên!");
        if (ticketEntity.deadline == null) throw new BadRequestException("Chưa chọn ngày hết hạn!");
        if (ticketEntity.locationId == null) throw new BadRequestException("Chưa chọn bộ phận IT!");
        ticketEntity.status = Lists.newArrayList(TicketStatus.values()).indexOf(TicketStatus.New) + 1;
        TokenEntity tokenEntity = tokenService.getByTokenKey(tokenKey.getValue());
        UserEntity userEntity = userService.get(tokenEntity.userId);
        ticketEntity.createEmployeeId = userEntity.employeeId;
        ticketEntity.createdTime = Timestamp.valueOf(LocalDateTime.now()).toString();
        LocationEntity locationEntity = locationService.get(ticketEntity.locationId);
        if (locationEntity == null) throw new BadRequestException("Bộ phận IT không hợp lệ!");
        ticketEntity.assignedEmployeeId = locationEntity.managerEmloyeeId;
        ticketEntity.ticketRelaterEntities = ticketEntity.ticketRelaterEntities.parallelStream().filter(ticketRelaterEntity -> {
            return employeeService.get(ticketRelaterEntity.employeeId) != null;
        }).collect(Collectors.toList());
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            TicketModel ticketModel = ticketEntity.toModel(ticketEntity.ticketImageEntities);
            Integer.valueOf(String.valueOf(session.save(ticketModel)));
            tx.commit();
            TicketEntity result = new TicketEntity(ticketModel);
            if (ticketEntity.ticketRelaterEntities != null) {
                ticketEntity.ticketRelaterEntities.parallelStream().forEach(ticketRelaterEntity -> {
                    ticketRelaterEntity.ticketId = result.id;
                    ticketRelaterService.create(ticketRelaterEntity);
                    TicketReadEntity ticketReadEntity = new TicketReadEntity();
                    ticketReadEntity.employeeId = ticketRelaterEntity.employeeId;
                    ticketReadEntity.ticketId = result.id;
                    ticketReadEntity.status = EnumValue.Parse(ReadStatus.UnRead);
                    ticketReadService.create(ticketReadEntity);
                });
            }
            if (ticketEntity.ticketImageEntities != null) {
                ticketEntity.ticketImageEntities.parallelStream().forEach(ticketImageEntity -> {
                    ticketImageEntity.ticketId = result.id;
                    ticketImageService.create(ticketImageEntity);
                });
            }
            TicketReadEntity ticketReadEntity = new TicketReadEntity();
            ticketReadEntity.employeeId = ticketEntity.createEmployeeId;
            ticketReadEntity.ticketId = result.id;
            ticketReadEntity.status = EnumValue.Parse(ReadStatus.UnRead);
            ticketReadService.create(ticketReadEntity);
            ticketReadEntity.employeeId = ticketEntity.assignedEmployeeId;
            ticketReadService.create(ticketReadEntity);
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return null;
    }

    public TicketThreadEntity addThread(Cookie tokenKey, Integer ticketId, TicketThreadEntity ticketThreadEntity) {
        Transaction tx = null;
        if (StringUtils.isNullOrEmpty(ticketThreadEntity.content) || ticketThreadEntity.content.equals("<br>"))
            throw new BadRequestException("Nội dung không được để trống!");
        TokenEntity tokenEntity = tokenService.getByTokenKey(tokenKey.getValue());
        UserEntity userEntity = userService.get(tokenEntity.userId);
        try (Session session = factory.openSession()) {
            ticketThreadEntity.ticketId = ticketId;
            ticketThreadEntity.employeeId = userEntity.employeeId;
            ticketThreadEntity.createTime = Timestamp.valueOf(LocalDateTime.now()).toString();
            ticketThreadEntity.updateTime = Timestamp.valueOf(LocalDateTime.now()).toString();
            tx = session.beginTransaction();
            TicketthreadModel ticketthreadModel = ticketThreadEntity.toModel();
            session.save(ticketthreadModel);
            tx.commit();
            TicketThreadEntity ticketThreadEntity1 = new TicketThreadEntity(ticketthreadModel);
            ticketThreadEntity1.employeeEntity = employeeService.get(userEntity.employeeId);
            return ticketThreadEntity1;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return null;
    }

    public TicketEntity update(Cookie tokenKey, int ticketId, TicketEntity ticketEntity) {
        TicketEntity ticketEntityUp = get(ticketId);
        ticketEntity.createEmployeeId = ticketEntityUp.createEmployeeId;
        if (ticketEntity.priority == null) throw new BadRequestException("Chưa chọn mức độ ưu tiên!");
        if (ticketEntity.deadline == null) throw new BadRequestException("Chưa chọn ngày hết hạn!");
        if (ticketEntity.locationId == null) throw new BadRequestException("Chưa chọn bộ phận IT!");
        if (ticketEntity.assignedEmployeeId == null) throw new BadRequestException("Chưa chọn người thực hiện!");
        TokenEntity tokenEntity = tokenService.getByTokenKey(tokenKey.getValue());
        UserEntity userEntity = userService.get(tokenEntity.userId);
        ticketEntity.createEmployeeId = userEntity.employeeId;
        ticketEntity.updatedTime = Timestamp.valueOf(LocalDateTime.now()).toString();
        LocationEntity locationEntity = locationService.get(ticketEntity.locationId);
        if (locationEntity == null) throw new BadRequestException("Bộ phận IT không hợp lệ!");
        Transaction tx = null;
        ticketEntity.ticketRelaterEntities = ticketEntity.ticketRelaterEntities.parallelStream().filter(ticketRelaterEntity -> {
            return employeeService.get(ticketRelaterEntity.employeeId) != null;
        }).collect(Collectors.toList());
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            TicketModel ticketModel = ticketEntity.toModel();
            ticketModel.setContent(ticketEntityUp.content);
            ticketModel.setSubject(ticketEntityUp.subject);
            session.update(ticketModel);
            TicketEntity result = get(ticketId);
            if (ticketEntity.ticketRelaterEntities != null) {
                if (result.ticketRelaterEntities == null) {
                    ticketEntity.ticketRelaterEntities.forEach(ticketRelaterEntity -> {
                        ticketRelaterEntity.ticketId = result.id;
                        ticketRelaterService.create(ticketRelaterEntity);
                        TicketReadEntity ticketReadEntity = new TicketReadEntity();
                        ticketReadEntity.employeeId = ticketRelaterEntity.employeeId;
                        ticketReadEntity.ticketId = result.id;
                        ticketReadEntity.status = EnumValue.Parse(ReadStatus.UnRead);
                        ticketReadService.create(ticketReadEntity);
                    });
                } else {
                    List<TicketRelaterEntity> addList = ticketEntity.ticketRelaterEntities.parallelStream().filter(ticketRelaterEntity -> {
                        return result.ticketRelaterEntities.parallelStream().noneMatch(ticketthreadModel -> {
                            return ticketthreadModel.employeeId == ticketRelaterEntity.employeeId;
                        });
                    }).distinct().collect(Collectors.toList());
                    List<TicketRelaterEntity> removeList = result.ticketRelaterEntities.parallelStream().filter(ticketthreadModel -> {
                        return ticketEntity.ticketRelaterEntities.parallelStream().noneMatch(ticketRelaterEntity -> {
                            return ticketthreadModel.employeeId == ticketRelaterEntity.employeeId;
                        });
                    }).collect(Collectors.toList());
                    removeList.forEach(ticketRelaterEntity -> {
                        ticketRelaterEntity.ticketId = (result.id);
                        session.delete(ticketRelaterEntity.toModel());
                        TicketReadEntity ticketReadEntity = ticketReadService.get(result.id,ticketRelaterEntity.employeeId);
                        session.delete(ticketReadEntity.toModel());
                    });
                    tx.commit();
                    addList.forEach(ticketRelaterEntity -> {
                        ticketRelaterEntity.ticketId = result.id;
                        ticketRelaterService.create(ticketRelaterEntity);
                    });
                    addList.forEach(ticketRelaterEntity -> {
                        TicketReadEntity ticketReadEntity = new TicketReadEntity();
                        ticketReadEntity.employeeId = ticketRelaterEntity.employeeId;
                        ticketReadEntity.ticketId = result.id;
                        ticketReadEntity.status = EnumValue.Parse(ReadStatus.UnRead);
                        ticketReadService.create(ticketReadEntity);
                    });
                }
            }
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return null;
    }

    public boolean delete(int id) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            TicketModel ticketModel = new TicketModel();
            ticketModel.setId(id);
            session.delete(ticketModel);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return false;
    }


    public List<TicketEntity> get(SearchTicketEntity searchTicketEntity) {
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<TicketModel> criteria = builder.createQuery(TicketModel.class);
        Root<TicketModel> TicketModels = criteria.from(TicketModel.class);
        try {
            List<TicketModel> ticketList = session.createQuery(criteria).getResultList();
            return ticketList.stream()
                    .map(s -> new TicketEntity(s)).collect(Collectors.toList());
        } catch (NoResultException e) {
            return null;
        } finally {
            session.close();
        }
    }
}
