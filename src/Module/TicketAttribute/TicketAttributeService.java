package Module.TicketAttribute;

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
public class TicketAttributeService {
    private static SessionFactory factory;
    private static int currentActive;

    public TicketAttributeService(SessionFactory factory) {
        this.factory = factory;
    }

    public TicketAttributeService() {
        if (factory == null || currentActive != DatabaseEntity.Active) {
            IDatabaseService databaseService = new DatabaseService();
            IDatabaseControllService databaseControllService = new DatabaseControllService();
            factory = databaseControllService.createConfiguration(databaseService.get(DatabaseEntity.Active)).buildSessionFactory();
            currentActive = DatabaseEntity.Active;
        }
    }

    public static void setFactory(SessionFactory factory) {
        TicketAttributeService.factory = factory;
    }


    public TicketAttributeEntity get(int id) {
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<TicketattributeModel> criteria = builder.createQuery(TicketattributeModel.class);
        Root<TicketattributeModel> EdgeEntities = criteria.from(TicketattributeModel.class);
        criteria.where(builder.equal(EdgeEntities.get("edgeId"), id));
        try {
            TicketattributeModel ticketattributeModel = session.createQuery(criteria).getSingleResult();
            return new TicketAttributeEntity(ticketattributeModel);
        } catch (NoResultException e) {
            return null;
        } finally {
            session.close();
        }
    }

    public TicketAttributeEntity create(int edgeId, Double startX, Double startY, Double endX, Double endY, Integer shapeId) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            TicketAttributeEntity ticketAttributeEntity = new TicketAttributeEntity(edgeId, startX, startY, endX, endY, shapeId);
            TicketattributeModel ticketattributeModel = ticketAttributeEntity.toModel();
            Integer.valueOf(String.valueOf(session.save(ticketattributeModel)));
            tx.commit();
            TicketAttributeEntity result = new TicketAttributeEntity(ticketattributeModel);
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return null;
    }

    public TicketAttributeEntity create(TicketAttributeEntity ticketAttributeEntity) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            TicketattributeModel ticketattributeModel = ticketAttributeEntity.toModel();
            Integer.valueOf(String.valueOf(session.save(ticketattributeModel)));
            tx.commit();
            TicketAttributeEntity result = new TicketAttributeEntity(ticketattributeModel);
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return null;
    }

    public TicketAttributeEntity update(int edgeId, Double startX, Double startY, Double endX, Double endY, Integer shapeId) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            TicketAttributeEntity ticketAttributeEntity = new TicketAttributeEntity(edgeId, startX, startY, endX, endY, shapeId);
            session.update(ticketAttributeEntity.toModel());
            tx.commit();
            TicketAttributeEntity result = get(edgeId);
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return null;
    }

    public TicketAttributeEntity update(int edgeId, TicketAttributeEntity ticketAttributeEntity) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            session.update(ticketAttributeEntity.toModel());
            tx.commit();
            TicketAttributeEntity result = get(edgeId);
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

    public List<TicketAttributeEntity> get(SearchTicketAttributeModel searchTicketAttributeModel) {
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<TicketattributeModel> criteria = builder.createQuery(TicketattributeModel.class);
        Root<TicketattributeModel> EdgeEntities = criteria.from(TicketattributeModel.class);
        try {
            List<TicketattributeModel> edgeList = session.createQuery(criteria).getResultList();
            return edgeList.stream()
                    .map(s -> new TicketAttributeEntity(s)).collect(Collectors.toList());
        } catch (NoResultException e) {
            return null;
        } finally {
            session.close();
        }
    }
}
