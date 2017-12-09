package Module.TicketImage;

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


public class TicketImageService {
    private static SessionFactory factory;
    private static int currentActive;

    public TicketImageService(SessionFactory factory) {
        this.factory = factory;
    }

    public TicketImageService() {
        if (factory == null || currentActive != DatabaseEntity.Active) {
            IDatabaseService databaseService = new DatabaseService();
            IDatabaseControllService databaseControllService = new DatabaseControllService();
            factory = databaseControllService.createConfiguration(databaseService.get(DatabaseEntity.Active)).buildSessionFactory();
            currentActive = DatabaseEntity.Active;
        }
    }

    public static void setFactory(SessionFactory factory) {
        TicketImageService.factory = factory;
    }


    public TicketImageEntity get(int id) {
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<TicketimageModel> criteria = builder.createQuery(TicketimageModel.class);
        Root<TicketimageModel> TicketImageModels = criteria.from(TicketimageModel.class);
        criteria.where(builder.equal(TicketImageModels.get("id"), id));
        try {
            TicketimageModel ticketattributeModel = session.createQuery(criteria).getSingleResult();
            return new TicketImageEntity(ticketattributeModel);
        } catch (NoResultException e) {
            return null;
        } finally {
            session.close();
        }
    }

//    public TicketImageEntity create(int ticketImageId, Double startX, Double startY, Double endX, Double endY, Integer shapeId) {
//        Transaction tx = null;
//        try (Session session = factory.openSession()) {
//            tx = session.beginTransaction();
//            TicketImageEntity ticketImageEntity = new TicketImageEntity(ticketImageId, startX, startY, endX, endY, shapeId);
//            TicketattributeModel ticketattributeModel = ticketImageEntity.toModel();
//            Integer.valueOf(String.valueOf(session.save(ticketattributeModel)));
//            tx.commit();
//            TicketImageEntity result = new TicketImageEntity(ticketattributeModel);
//            return result;
//        } catch (HibernateException e) {
//            if (tx != null) tx.rollback();
//            e.printStackTrace();
//        }
//        return null;
//    }

    public TicketImageEntity create(TicketImageEntity ticketImageEntity) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            TicketimageModel ticketattributeModel = ticketImageEntity.toModel();
            Integer.valueOf(String.valueOf(session.save(ticketattributeModel)));
            tx.commit();
            TicketImageEntity result = new TicketImageEntity(ticketattributeModel);
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return null;
    }

//    public TicketImageEntity update(int ticketImageId, Double startX, Double startY, Double endX, Double endY, Integer shapeId) {
//        Transaction tx = null;
//        try (Session session = factory.openSession()) {
//            tx = session.beginTransaction();
//            TicketImageEntity ticketImageEntity = new TicketImageEntity(ticketImageId, startX, startY, endX, endY, shapeId);
//            session.update(ticketImageEntity.toModel());
//            tx.commit();
//            TicketImageEntity result = get(ticketImageId);
//            return result;
//        } catch (HibernateException e) {
//            if (tx != null) tx.rollback();
//            e.printStackTrace();
//        }
//        return null;
//    }

    public TicketImageEntity update(int ticketImageId, TicketImageEntity ticketImageEntity) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            session.update(ticketImageEntity.toModel());
            tx.commit();
            TicketImageEntity result = get(ticketImageId);
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

    public List<TicketImageEntity> get(SearchTicketImageEntity searchTicketImageEntity) {
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<TicketimageModel> criteria = builder.createQuery(TicketimageModel.class);
        Root<TicketimageModel> TicketImageModels = criteria.from(TicketimageModel.class);
        try {
            List<TicketimageModel> ticketImageList = session.createQuery(criteria).getResultList();
            return ticketImageList.stream()
                    .map(s -> new TicketImageEntity(s)).collect(Collectors.toList());
        } catch (NoResultException e) {
            return null;
        } finally {
            session.close();
        }
    }
}
