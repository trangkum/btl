package Module.TicketRelater;

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
public class TicketRelaterService {
    private static SessionFactory factory;
    private static int currentActive;

    public TicketRelaterService(SessionFactory factory) {
        this.factory = factory;
    }

    public TicketRelaterService() {
        if (factory == null || currentActive != DatabaseEntity.Active) {
            IDatabaseService databaseService = new DatabaseService();
            IDatabaseControllService databaseControllService = new DatabaseControllService();
            factory = databaseControllService.createConfiguration(databaseService.get(DatabaseEntity.Active)).buildSessionFactory();
            currentActive = DatabaseEntity.Active;
        }
    }

    public static void setFactory(SessionFactory factory) {
        TicketRelaterService.factory = factory;
    }


    public TicketRelaterEntity get(int id) {
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<TicketattributeModel> criteria = builder.createQuery(TicketattributeModel.class);
        Root<TicketattributeModel> EdgeEntities = criteria.from(TicketattributeModel.class);
        criteria.where(builder.equal(EdgeEntities.get("edgeId"), id));
        try {
            TicketattributeModel ticketattributeModel = session.createQuery(criteria).getSingleResult();
            return new TicketRelaterEntity(ticketattributeModel);
        } catch (NoResultException e) {
            return null;
        } finally {
            session.close();
        }
    }

    public TicketRelaterEntity create(int edgeId, Double startX, Double startY, Double endX, Double endY, Integer shapeId) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            TicketRelaterEntity ticketRelaterEntity = new TicketRelaterEntity(edgeId, startX, startY, endX, endY, shapeId);
            TicketattributeModel ticketattributeModel = ticketRelaterEntity.toModel();
            Integer.valueOf(String.valueOf(session.save(ticketattributeModel)));
            tx.commit();
            TicketRelaterEntity result = new TicketRelaterEntity(ticketattributeModel);
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return null;
    }

    public TicketRelaterEntity create(TicketRelaterEntity ticketRelaterEntity) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            TicketattributeModel ticketattributeModel = ticketRelaterEntity.toModel();
            Integer.valueOf(String.valueOf(session.save(ticketattributeModel)));
            tx.commit();
            TicketRelaterEntity result = new TicketRelaterEntity(ticketattributeModel);
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return null;
    }

    public TicketRelaterEntity update(int edgeId, Double startX, Double startY, Double endX, Double endY, Integer shapeId) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            TicketRelaterEntity ticketRelaterEntity = new TicketRelaterEntity(edgeId, startX, startY, endX, endY, shapeId);
            session.update(ticketRelaterEntity.toModel());
            tx.commit();
            TicketRelaterEntity result = get(edgeId);
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return null;
    }

    public TicketRelaterEntity update(int edgeId, TicketRelaterEntity ticketRelaterEntity) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            session.update(ticketRelaterEntity.toModel());
            tx.commit();
            TicketRelaterEntity result = get(edgeId);
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

    public List<TicketRelaterEntity> get(SearchTicketRelaterModel searchTicketRelaterModel) {
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<TicketattributeModel> criteria = builder.createQuery(TicketattributeModel.class);
        Root<TicketattributeModel> EdgeEntities = criteria.from(TicketattributeModel.class);
        try {
            List<TicketattributeModel> edgeList = session.createQuery(criteria).getResultList();
            return edgeList.stream()
                    .map(s -> new TicketRelaterEntity(s)).collect(Collectors.toList());
        } catch (NoResultException e) {
            return null;
        } finally {
            session.close();
        }
    }
}
