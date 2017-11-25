package Module.Route;

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
public class RouteService {
    private static SessionFactory factory;
    private static int currentActive;

    public RouteService(SessionFactory factory) {
        this.factory = factory;
    }

    public RouteService() {
        if (factory == null || currentActive != DatabaseEntity.Active) {
            IDatabaseService databaseService = new DatabaseService();
            IDatabaseControllService databaseControllService = new DatabaseControllService();
            factory = databaseControllService.createConfiguration(databaseService.get(DatabaseEntity.Active)).buildSessionFactory();
            currentActive = DatabaseEntity.Active;
        }
    }

    public static void setFactory(SessionFactory factory) {
        RouteService.factory = factory;
    }


    public RouteEntity get(int id) {
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<RouteModel> criteria = builder.createQuery(RouteModel.class);
        Root<RouteModel> EdgeEntities = criteria.from(RouteModel.class);
        criteria.where(builder.equal(EdgeEntities.get("edgeId"), id));
        try {
            RouteModel routeModel = session.createQuery(criteria).getSingleResult();
            return new RouteEntity(routeModel);
        } catch (NoResultException e) {
            return null;
        } finally {
            session.close();
        }
    }

    public RouteEntity create(int edgeId, Double startX, Double startY, Double endX, Double endY, Integer shapeId) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            RouteEntity routeEntity = new RouteEntity(edgeId, startX, startY, endX, endY, shapeId);
            RouteModel routeModel = routeEntity.toModel();
            Integer.valueOf(String.valueOf(session.save(routeModel)));
            tx.commit();
            RouteEntity result = new RouteEntity(routeModel);
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return null;
    }

    public RouteEntity create(RouteEntity routeEntity) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            RouteModel routeModel = routeEntity.toModel();
            Integer.valueOf(String.valueOf(session.save(routeModel)));
            tx.commit();
            RouteEntity result = new RouteEntity(routeModel);
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return null;
    }

    public RouteEntity update(int edgeId, Double startX, Double startY, Double endX, Double endY, Integer shapeId) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            RouteEntity routeEntity = new RouteEntity(edgeId, startX, startY, endX, endY, shapeId);
            session.update(routeEntity.toModel());
            tx.commit();
            RouteEntity result = get(edgeId);
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return null;
    }

    public RouteEntity update(int edgeId, RouteEntity routeEntity) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            session.update(routeEntity.toModel());
            tx.commit();
            RouteEntity result = get(edgeId);
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
            RouteModel routeModel = new RouteModel();
            routeModel.setEdgeId(id);
            session.delete(routeModel);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    public List<RouteEntity> get(SearchRouteModel searchRouteModel) {
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<RouteModel> criteria = builder.createQuery(RouteModel.class);
        Root<RouteModel> EdgeEntities = criteria.from(RouteModel.class);
        try {
            List<RouteModel> edgeList = session.createQuery(criteria).getResultList();
            return edgeList.stream()
                    .map(s -> new RouteEntity(s)).collect(Collectors.toList());
        } catch (NoResultException e) {
            return null;
        } finally {
            session.close();
        }
    }
}
