package Module.TicketThread;

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
public class TicketThreadService {
    private static SessionFactory factory;
    private static int currentActive;

    public TicketThreadService(SessionFactory factory) {
        this.factory = factory;
    }

    public TicketThreadService() {
        if (factory == null || currentActive != DatabaseEntity.Active) {
            IDatabaseService databaseService = new DatabaseService();
            IDatabaseControllService databaseControllService = new DatabaseControllService();
            factory = databaseControllService.createConfiguration(databaseService.get(DatabaseEntity.Active)).buildSessionFactory();
            currentActive = DatabaseEntity.Active;
        }
    }

    public static void setFactory(SessionFactory factory) {
        TicketThreadService.factory = factory;
    }


    public TicketThreadEntity get(int id) {
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<TicketthreadModel> criteria = builder.createQuery(TicketthreadModel.class);
        Root<TicketthreadModel> TicketThreadModels = criteria.from(TicketthreadModel.class);
        criteria.where(builder.equal(TicketThreadModels.get("id"), id));
        try {
            TicketthreadModel ticketattributeModel = session.createQuery(criteria).getSingleResult();
            return new TicketThreadEntity(ticketattributeModel);
        } catch (NoResultException e) {
            return null;
        } finally {
            session.close();
        }
    }

//    public TicketThreadEntity create(int ticketThreadId, Double startX, Double startY, Double endX, Double endY, Integer shapeId) {
//        Transaction tx = null;
//        try (Session session = factory.openSession()) {
//            tx = session.beginTransaction();
//            TicketThreadEntity ticketThreadEntity = new TicketThreadEntity(ticketThreadId, startX, startY, endX, endY, shapeId);
//            TicketattributeModel ticketattributeModel = ticketThreadEntity.toModel();
//            Integer.valueOf(String.valueOf(session.save(ticketattributeModel)));
//            tx.commit();
//            TicketThreadEntity result = new TicketThreadEntity(ticketattributeModel);
//            return result;
//        } catch (HibernateException e) {
//            if (tx != null) tx.rollback();
//            e.printStackTrace();
//        }
//        return null;
//    }

    public TicketThreadEntity create(TicketThreadEntity ticketThreadEntity) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            TicketthreadModel ticketattributeModel = ticketThreadEntity.toModel();
            Integer.valueOf(String.valueOf(session.save(ticketattributeModel)));
            tx.commit();
            TicketThreadEntity result = new TicketThreadEntity(ticketattributeModel);
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return null;
    }

//    public TicketThreadEntity update(int ticketThreadId, Double startX, Double startY, Double endX, Double endY, Integer shapeId) {
//        Transaction tx = null;
//        try (Session session = factory.openSession()) {
//            tx = session.beginTransaction();
//            TicketThreadEntity ticketThreadEntity = new TicketThreadEntity(ticketThreadId, startX, startY, endX, endY, shapeId);
//            session.update(ticketThreadEntity.toModel());
//            tx.commit();
//            TicketThreadEntity result = get(ticketThreadId);
//            return result;
//        } catch (HibernateException e) {
//            if (tx != null) tx.rollback();
//            e.printStackTrace();
//        }
//        return null;
//    }

    public TicketThreadEntity update(int ticketThreadId, TicketThreadEntity ticketThreadEntity) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            session.update(ticketThreadEntity.toModel());
            tx.commit();
            TicketThreadEntity result = get(ticketThreadId);
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

    public List<TicketThreadEntity> get(SearchTicketThreadEntity searchTicketThreadEntity) {
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<TicketthreadModel> criteria = builder.createQuery(TicketthreadModel.class);
        Root<TicketthreadModel> TicketThreadModels = criteria.from(TicketthreadModel.class);
        try {
            List<TicketthreadModel> ticketThreadList = session.createQuery(criteria).getResultList();
            return ticketThreadList.stream()
                    .map(s -> new TicketThreadEntity(s)).collect(Collectors.toList());
        } catch (NoResultException e) {
            return null;
        } finally {
            session.close();
        }
    }
}
