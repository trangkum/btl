package Module.Ticket;

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


public class TicketService {
    private static SessionFactory factory;
    private static int currentActive;

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
            return new TicketEntity(ticketModel);
        } catch (NoResultException e) {
            return null;
        } finally {
            session.close();
        }
    }

//    public TicketEntity create(int ticketId, Double startX, Double startY, Double endX, Double endY, Integer shapeId) {
//        Transaction tx = null;
//        try (Session session = factory.openSession()) {
//            tx = session.beginTransaction();
//            TicketEntity ticketEntity = new TicketEntity(ticketId, startX, startY, endX, endY, shapeId);
//            TicketModel ticketModel = ticketEntity.toModel();
//            Integer.valueOf(String.valueOf(session.save(ticketModel)));
//            tx.commit();
//            TicketEntity result = new TicketEntity(ticketModel);
//            return result;
//        } catch (HibernateException e) {
//            if (tx != null) tx.rollback();
//            e.printStackTrace();
//        }
//        return null;
//    }

    public TicketEntity create(TicketEntity ticketEntity) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            TicketModel ticketModel = ticketEntity.toModel();
            Integer.valueOf(String.valueOf(session.save(ticketModel)));
            tx.commit();
            TicketEntity result = new TicketEntity(ticketModel);
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return null;
    }

//    public TicketEntity update(int ticketId, Double startX, Double startY, Double endX, Double endY, Integer shapeId) {
//        Transaction tx = null;
//        try (Session session = factory.openSession()) {
//            tx = session.beginTransaction();
//            TicketEntity ticketEntity = new TicketEntity(ticketId, startX, startY, endX, endY, shapeId);
//            session.update(ticketEntity.toModel());
//            tx.commit();
//            TicketEntity result = get(ticketId);
//            return result;
//        } catch (HibernateException e) {
//            if (tx != null) tx.rollback();
//            e.printStackTrace();
//        }
//        return null;
//    }

    public TicketEntity update(int ticketId, TicketEntity ticketEntity) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            session.update(ticketEntity.toModel());
            tx.commit();
            TicketEntity result = get(ticketId);
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
