package Module.Location;

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

public class LocationService {
    private static SessionFactory factory;
    private static int currentActive;

    public LocationService(SessionFactory factory) {
        this.factory = factory;
    }

    public LocationService() {
        if (factory == null || currentActive != DatabaseEntity.Active) {
            IDatabaseService databaseService = new DatabaseService();
            IDatabaseControllService databaseControllService = new DatabaseControllService();
            factory = databaseControllService.createConfiguration(databaseService.get(DatabaseEntity.Active)).buildSessionFactory();
            currentActive = DatabaseEntity.Active;
        }
    }

    public static void setFactory(SessionFactory factory) {
        Module.Location.LocationService.factory = factory;
    }


    public LocationEntity get(int id) {
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<LocationModel> criteria = builder.createQuery(LocationModel.class);
        Root<LocationModel> LocationModels = criteria.from(LocationModel.class);
        criteria.where(builder.equal(LocationModels.get("id"), id));
        try {
            LocationModel locationModel = session.createQuery(criteria).getSingleResult();
            return new LocationEntity(locationModel);
        } catch (NoResultException e) {
            return null;
        } finally {
            session.close();
        }
    }

//    public LocationEntity create(int locationId, Double startX, Double startY, Double endX, Double endY, Integer shapeId) {
//        Transaction tx = null;
//        try (Session session = factory.openSession()) {
//            tx = session.beginTransaction();
//            LocationEntity locationEntity = new LocationEntity(locationId, startX, startY, endX, endY, shapeId);
//            LocationModel locationModel = locationEntity.toModel();
//            Integer.valueOf(String.valueOf(session.save(locationModel)));
//            tx.commit();
//            LocationEntity result = new LocationEntity(locationModel);
//            return result;
//        } catch (HibernateException e) {
//            if (tx != null) tx.rollback();
//            e.printStackTrace();
//        }
//        return null;
//    }

    public LocationEntity create(LocationEntity locationEntity) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            LocationModel locationModel = locationEntity.toModel();
            Integer.valueOf(String.valueOf(session.save(locationModel)));
            tx.commit();
            LocationEntity result = new LocationEntity(locationModel);
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return null;
    }

//    public LocationEntity update(int locationId, Double startX, Double startY, Double endX, Double endY, Integer shapeId) {
//        Transaction tx = null;
//        try (Session session = factory.openSession()) {
//            tx = session.beginTransaction();
//            LocationEntity locationEntity = new LocationEntity(locationId, startX, startY, endX, endY, shapeId);
//            session.update(locationEntity.toModel());
//            tx.commit();
//            LocationEntity result = get(locationId);
//            return result;
//        } catch (HibernateException e) {
//            if (tx != null) tx.rollback();
//            e.printStackTrace();
//        }
//        return null;
//    }

    public LocationEntity update(int locationId, LocationEntity locationEntity) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            session.update(locationEntity.toModel());
            tx.commit();
            LocationEntity result = get(locationId);
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
            LocationModel locationModel = new LocationModel();
            locationModel.setId(id);
            session.delete(locationModel);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    public List<LocationEntity> get(SearchLocationEntity searchLocationEntity) {
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<LocationModel> criteria = builder.createQuery(LocationModel.class);
        Root<LocationModel> LocationModels = criteria.from(LocationModel.class);
        try {
            List<LocationModel> locationList = session.createQuery(criteria).getResultList();
            return locationList.stream()
                    .map(s -> new LocationEntity(s)).collect(Collectors.toList());
        } catch (NoResultException e) {
            return null;
        } finally {
            session.close();
        }
    }
}
