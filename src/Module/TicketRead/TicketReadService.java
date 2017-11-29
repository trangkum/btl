package Module.TicketRead;

import Manager.Entity.DatabaseEntity;
import Manager.Interface.IDatabaseControllService;
import Manager.Interface.IDatabaseService;
import Manager.Service.DatabaseControllService;
import Manager.Service.DatabaseService;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Son on 6/15/2017.
 */
public class TicketReadService {
    private static SessionFactory factory;
    private static int currentActive;

    public TicketReadService(SessionFactory factory) {
        this.factory = factory;
    }

    public TicketReadService() {
        if (factory == null || currentActive != DatabaseEntity.Active) {
            IDatabaseService databaseService = new DatabaseService();
            IDatabaseControllService databaseControllService = new DatabaseControllService();
            factory = databaseControllService.createConfiguration(databaseService.get(DatabaseEntity.Active)).buildSessionFactory();
            currentActive = DatabaseEntity.Active;
        }
    }

    public static void setFactory(SessionFactory factory) {
        TicketReadService.factory = factory;
    }


    public TicketReadEntity get(int id) {
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<TicketreadModel> criteria = builder.createQuery(TicketreadModel.class);
        Root<TicketreadModel> TicketReadModels = criteria.from(TicketreadModel.class);
        criteria.where(builder.equal(TicketReadModels.get("id"), id));
        try {
            TicketreadModel ticketattributeModel = session.createQuery(criteria).getSingleResult();
            return new TicketReadEntity(ticketattributeModel);
        } catch (NoResultException e) {
            return null;
        } finally {
            session.close();
        }
    }

//    public TicketReadEntity create(int ticketReadId, Double startX, Double startY, Double endX, Double endY, Integer shapeId) {
//        Transaction tx = null;
//        try (Session session = factory.openSession()) {
//            tx = session.beginTransaction();
//            TicketReadEntity ticketReadEntity = new TicketReadEntity(ticketReadId, startX, startY, endX, endY, shapeId);
//            TicketattributeModel ticketattributeModel = ticketReadEntity.toModel();
//            Integer.valueOf(String.valueOf(session.save(ticketattributeModel)));
//            tx.commit();
//            TicketReadEntity result = new TicketReadEntity(ticketattributeModel);
//            return result;
//        } catch (HibernateException e) {
//            if (tx != null) tx.rollback();
//            e.printStackTrace();
//        }
//        return null;
//    }

    public TicketReadEntity create(TicketReadEntity ticketReadEntity) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            TicketreadModel ticketattributeModel = ticketReadEntity.toModel();
            Integer.valueOf(String.valueOf(session.save(ticketattributeModel)));
            tx.commit();
            TicketReadEntity result = new TicketReadEntity(ticketattributeModel);
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return null;
    }

//    public TicketReadEntity update(int ticketReadId, Double startX, Double startY, Double endX, Double endY, Integer shapeId) {
//        Transaction tx = null;
//        try (Session session = factory.openSession()) {
//            tx = session.beginTransaction();
//            TicketReadEntity ticketReadEntity = new TicketReadEntity(ticketReadId, startX, startY, endX, endY, shapeId);
//            session.update(ticketReadEntity.toModel());
//            tx.commit();
//            TicketReadEntity result = get(ticketReadId);
//            return result;
//        } catch (HibernateException e) {
//            if (tx != null) tx.rollback();
//            e.printStackTrace();
//        }
//        return null;
//    }

    public TicketReadEntity update(int ticketReadId, TicketReadEntity ticketReadEntity) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            session.update(ticketReadEntity.toModel());
            tx.commit();
            TicketReadEntity result = get(ticketReadId);
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return null;
    }

//    public boolean delete(int id) {
//        Transaction tx = null;
//        try (Session session = factory.openSession()) {
//            tx = session.beginTransaction();
//            TicketimageModel ticketattributeModel = new TicketimageModel();
//            ticketattributeModel.setId(id);
//            session.delete(ticketattributeModel);
//            tx.commit();
//            return true;
//        } catch (HibernateException e) {
//            if (tx != null) tx.rollback();
//            e.printStackTrace();
//        }
//        return false;
//    }

    public List<TicketReadEntity> get(SearchTicketReadEntity searchTicketReadEntity) {
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<TicketreadModel> criteria = builder.createQuery(TicketreadModel.class);
        Root<TicketreadModel> TicketReadModels = criteria.from(TicketreadModel.class);
        try {
            List<TicketreadModel> ticketReadList = session.createQuery(criteria).getResultList();
            return ticketReadList.stream()
                    .map(s -> new TicketReadEntity(s)).collect(Collectors.toList());
        } catch (NoResultException e) {
            return null;
        } finally {
            session.close();
        }
    }
}
