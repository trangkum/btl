package Module.View;

import AppStart.RESTTimestamp;
import Manager.Entity.DatabaseEntity;
import Manager.Interface.IDatabaseControllService;
import Manager.Interface.IDatabaseService;
import Manager.Service.DatabaseControllService;
import Manager.Service.DatabaseService;
import Module.Employee.EmployeeEntity;
import Module.Employee.EmployeeService;
import Module.Enum.EnumValue;
import Module.Enum.ReadStatus;
import Module.Enum.TicketStatus;
import Module.Location.LocationService;
import Module.Ticket.SearchTicketEntity;
import Module.Ticket.TicketEntity;
import Module.Ticket.TicketModel;
import Module.Ticket.TicketModel_;
import Module.TicketRead.SearchTicketReadEntity;
import Module.TicketRead.TicketReadEntity;
import Module.TicketRead.TicketReadService;
import Module.TicketRelater.TicketrelaterModel;
import Module.TicketRelater.TicketrelaterModel_;
import Module.User.TokenEntity;
import Module.User.TokenService;
import Module.User.UserEntity;
import Module.User.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.NoResultException;
import javax.persistence.criteria.*;
import javax.ws.rs.core.Cookie;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class ViewService {
    private static SessionFactory factory;
    private static int currentActive;
    LocationService locationService = new LocationService();
    TokenService tokenService = new TokenService();
    UserService userService = new UserService();
    EmployeeService employeeService = new EmployeeService();
    TicketReadService ticketReadService = new TicketReadService();

    public ViewService(SessionFactory factory) {
        this.factory = factory;
    }

    public ViewService() {
        if (factory == null || currentActive != DatabaseEntity.Active) {
            IDatabaseService databaseService = new DatabaseService();
            IDatabaseControllService databaseControllService = new DatabaseControllService();
            factory = databaseControllService.createConfiguration(databaseService.get(DatabaseEntity.Active)).buildSessionFactory();
            currentActive = DatabaseEntity.Active;
        }
    }

    public static void setFactory(SessionFactory factory) {
        ViewService.factory = factory;
    }


    public Long count(String viewType, Cookie tokenKey, SearchTicketEntity searchTicketEntity) {
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<TicketModel> criteria = builder.createQuery(TicketModel.class);
        Root<TicketModel> TicketModels = criteria.from(TicketModel.class);
        TokenEntity tokenEntity = tokenService.getByTokenKey(tokenKey.getValue());
        UserEntity userEntity = userService.get(tokenEntity.userId);
        try {
            List<Predicate> predicates = searchTicketEntity.applyTo(builder, new ArrayList<>(), TicketModels);
            switch (viewType) {
                case "myRequests":
                    predicates.add(builder.equal(TicketModels.get(TicketModel_.createEmployeeId), userEntity.employeeId));
                    break;
                case "relateds":
                    Join<TicketModel, TicketrelaterModel> related = TicketModels.join(TicketModel_.ticketrelatersById);
                    predicates.add(builder.equal(related.get(TicketrelaterModel_.employeeId), userEntity.employeeId));
                    break;
                case "myWorks":
                    predicates.add(builder.equal(TicketModels.get(TicketModel_.assignedEmployeeId), userEntity.employeeId));
                    break;
                case "teams":
                    EmployeeEntity employeeEntity = employeeService.get(userEntity.employeeId);
                    predicates.add(builder.equal(TicketModels.get(TicketModel_.locationId), employeeEntity.locationId));
                    break;
                case "iTs":
                    break;
                default:
                    break;
            }
            criteria.where(predicates.toArray(new Predicate[]{}));
            return (Long) session.createQuery(searchTicketEntity.order(builder, criteria, TicketModels).select(builder.count(TicketModels))).getSingleResult();

        } catch (NoResultException e) {
            return null;
        } finally {
            session.close();
        }
    }

    public List<TicketEntity> get(String viewType, Cookie tokenKey, SearchTicketEntity searchTicketEntity) {
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<TicketModel> criteria = builder.createQuery(TicketModel.class);
        Root<TicketModel> TicketModels = criteria.from(TicketModel.class);
        TokenEntity tokenEntity = tokenService.getByTokenKey(tokenKey.getValue());
        UserEntity userEntity = userService.get(tokenEntity.userId);

        try {
            List<Predicate> predicates = searchTicketEntity.applyTo(builder, new ArrayList<>(), TicketModels);
            switch (viewType) {
                case "myRequests":
                    predicates.add(builder.equal(TicketModels.get(TicketModel_.createEmployeeId), userEntity.employeeId));
                    break;
                case "relateds":
                    Join<TicketModel, TicketrelaterModel> related = TicketModels.join(TicketModel_.ticketrelatersById);
                    predicates.add(builder.equal(related.get(TicketrelaterModel_.employeeId), userEntity.employeeId));
                    break;
                case "myWorks":
                    predicates.add(builder.equal(TicketModels.get(TicketModel_.assignedEmployeeId), userEntity.employeeId));
                    break;
                case "teams":
                    EmployeeEntity employeeEntity = employeeService.get(userEntity.employeeId);
                    predicates.add(builder.equal(TicketModels.get(TicketModel_.locationId), employeeEntity.locationId));
                    break;
                case "iTs":
                    break;
                default:
                    break;
            }
            criteria.where(predicates.toArray(new Predicate[]{}));
            List<TicketModel> ticketList = searchTicketEntity.skipAndTake(session.createQuery(searchTicketEntity.order(builder, criteria, TicketModels))).getResultList();
            List<TicketEntity> ticketEntities = ticketList.stream()
                    .map(s -> new TicketEntity(s, s.getEmployeeByCreateEmployeeId(),s.getLocationByLocationId())).collect(Collectors.toList());
            ticketEntities.forEach(ticketEntity -> {
                ticketEntity.assignedEmployeeEntity = employeeService.get(ticketEntity.assignedEmployeeId);
                SearchTicketReadEntity searchTicketReadEntity = new SearchTicketReadEntity();
                searchTicketReadEntity.employeeId = userEntity.employeeId;
                searchTicketReadEntity.ticketId = ticketEntity.id;
                List<TicketReadEntity> ticketReadEntities = ticketReadService.get(searchTicketReadEntity);
                ticketEntity.ticketReadEntities = ticketReadEntities;
            });
            return ticketEntities;
        } catch (NoResultException e) {
            return null;
        } finally {
            session.close();
        }
    }


    public CountUnread countUnread(String viewType, Cookie tokenKey, SearchTicketEntity searchTicketEntity) {
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<TicketModel> criteria = builder.createQuery(TicketModel.class);
        Root<TicketModel> TicketModels = criteria.from(TicketModel.class);
        TokenEntity tokenEntity = tokenService.getByTokenKey(tokenKey.getValue());
        UserEntity userEntity = userService.get(tokenEntity.userId);
        try {
            List<Predicate> predicates = searchTicketEntity.applyTo(builder, new ArrayList<>(), TicketModels);
            switch (viewType) {
                case "myRequests":
                    predicates.add(builder.equal(TicketModels.get(TicketModel_.createEmployeeId), userEntity.employeeId));
                    break;
                case "relateds":
                    Join<TicketModel, TicketrelaterModel> related = TicketModels.join(TicketModel_.ticketrelatersById);
                    predicates.add(builder.equal(related.get(TicketrelaterModel_.employeeId), userEntity.employeeId));
                    break;
                case "myWorks":
                    predicates.add(builder.equal(TicketModels.get(TicketModel_.assignedEmployeeId), userEntity.employeeId));
                    break;
                case "teams":
                    EmployeeEntity employeeEntity = employeeService.get(userEntity.employeeId);
                    predicates.add(builder.equal(TicketModels.get(TicketModel_.locationId), employeeEntity.locationId));
                    break;
                case "iTs":
                    break;
                default:
                    break;
            }
            criteria.where(predicates.toArray(new Predicate[]{}));
            List<TicketModel> ticketList = session.createQuery(criteria).getResultList();
            List<TicketEntity> ticketEntities = ticketList.stream()
                    .map(s -> new TicketEntity(s, s.getEmployeeByCreateEmployeeId())).collect(Collectors.toList());
            CountUnread countUnread = new CountUnread();
            ticketEntities.forEach(ticketEntity -> {
                SearchTicketReadEntity searchTicketReadEntity = new SearchTicketReadEntity();
                searchTicketReadEntity.employeeId = userEntity.employeeId;
                searchTicketReadEntity.ticketId = ticketEntity.id;
                List<TicketReadEntity> ticketReadEntities = ticketReadService.get(searchTicketReadEntity);
                if (ticketReadEntities.size() > 0) {
                    if (Objects.equals(ticketReadEntities.get(0).status, EnumValue.Parse(ReadStatus.UnRead))) {
                        switch (TicketStatus.values()[ticketEntity.status - 1]) {
                            case New:
                                countUnread.New++;
                                break;
                            case Cancelled:
                                countUnread.Cancelled++;
                                break;
                            case Resolved:
                                countUnread.Resolved++;
                                break;
                            case InProgress:
                                countUnread.InProgress++;
                                break;
                            case Feedback:
                                countUnread.FeedBack++;
                                break;
                            case Closed:
                                countUnread.Closed++;
                                break;
                        }
                        if(RESTTimestamp.Parse(ticketEntity.deadline) == null){
                            System.out.println(111);
                        }
                        if (Timestamp.valueOf(LocalDateTime.now()).after(RESTTimestamp.Parse(ticketEntity.deadline)))
                            countUnread.OutOfDate++;
                        countUnread.All++;
                    }
                }
            });
            return countUnread;
        } catch (NoResultException e) {
            return null;
        } finally {
            session.close();
        }
    }

}
