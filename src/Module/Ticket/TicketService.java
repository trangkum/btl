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

/**
 * Created by Son on 6/15/2017.
 */
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
        CriteriaQuery<TicketattributeModel> criteria = builder.createQuery(TicketattributeModel.class);
        Root<TicketattributeModel> EdgeEntities = criteria.from(TicketattributeModel.class);
        criteria.where(builder.equal(EdgeEntities.get("edgeId"), id));
        try {
            TicketattributeModel ticketattributeModel = session.createQuery(criteria).getSingleResult();
            return new TicketEntity(ticketattributeModel);
        } catch (NoResultException e) {
            return null;
        } finally {
            session.close();
        }
    }

    public TicketEntity create(int edgeId, Double startX, Double startY, Double endX, Double endY, Integer shapeId) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            TicketEntity ticketEntity = new TicketEntity(edgeId, startX, startY, endX, endY, shapeId);
            TicketattributeModel ticketattributeModel = ticketEntity.toModel();
            Integer.valueOf(String.valueOf(session.save(ticketattributeModel)));
            tx.commit();
            TicketEntity result = new TicketEntity(ticketattributeModel);
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return null;
    }

    public TicketEntity create(TicketEntity ticketEntity) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            TicketattributeModel ticketattributeModel = ticketEntity.toModel();
            Integer.valueOf(String.valueOf(session.save(ticketattributeModel)));
            tx.commit();
            TicketEntity result = new TicketEntity(ticketattributeModel);
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return null;
    }

    public TicketEntity update(int edgeId, Double startX, Double startY, Double endX, Double endY, Integer shapeId) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            TicketEntity ticketEntity = new TicketEntity(edgeId, startX, startY, endX, endY, shapeId);
            session.update(ticketEntity.toModel());
            tx.commit();
            TicketEntity result = get(edgeId);
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return null;
    }

    public TicketEntity update(int edgeId, TicketEntity ticketEntity) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            session.update(ticketEntity.toModel());
            tx.commit();
            TicketEntity result = get(edgeId);
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
            TicketattributeModel ticketattributeModel = new TicketattributeModel();
            ticketattributeModel.setEdgeId(id);
            session.delete(ticketattributeModel);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    public List<TicketEntity> get(SearchTicketModel searchTicketModel) {
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<TicketattributeModel> criteria = builder.createQuery(TicketattributeModel.class);
        Root<TicketattributeModel> EdgeEntities = criteria.from(TicketattributeModel.class);
        try {
            List<TicketattributeModel> edgeList = session.createQuery(criteria).getResultList();
            return edgeList.stream()
                    .map(s -> new TicketEntity(s)).collect(Collectors.toList());
        } catch (NoResultException e) {
            return null;
        } finally {
            session.close();
        }
    }
}
