package Module.Permission;

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

public class PermissionService {
    private static SessionFactory factory;
    private static int currentActive;

    public PermissionService(SessionFactory factory) {
        this.factory = factory;
    }

    public PermissionService() {
        if (factory == null || currentActive != DatabaseEntity.Active) {
            IDatabaseService databaseService = new DatabaseService();
            IDatabaseControllService databaseControllService = new DatabaseControllService();
            factory = databaseControllService.createConfiguration(databaseService.get(DatabaseEntity.Active)).buildSessionFactory();
            currentActive = DatabaseEntity.Active;
        }
    }

    public static void setFactory(SessionFactory factory) {
        PermissionService.factory = factory;
    }


    public PermissionEntity get(int id) {
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<PermissionModel> criteria = builder.createQuery(PermissionModel.class);
        Root<PermissionModel> PermissionModels = criteria.from(PermissionModel.class);
        criteria.where(builder.equal(PermissionModels.get("id"), id));
        try {
            PermissionModel permissionModel = session.createQuery(criteria).getSingleResult();
            return new PermissionEntity(permissionModel);
        } catch (NoResultException e) {
            return null;
        } finally {
            session.close();
        }
    }

//    public PermissionEntity create(int permissionId, Double startX, Double startY, Double endX, Double endY, Integer shapeId) {
//        Transaction tx = null;
//        try (Session session = factory.openSession()) {
//            tx = session.beginTransaction();
//            PermissionEntity permissionEntity = new PermissionEntity(permissionId, startX, startY, endX, endY, shapeId);
//            PermissionModel permissionModel = permissionEntity.toModel();
//            Integer.valueOf(String.valueOf(session.save(permissionModel)));
//            tx.commit();
//            PermissionEntity result = new PermissionEntity(permissionModel);
//            return result;
//        } catch (HibernateException e) {
//            if (tx != null) tx.rollback();
//            e.printStackTrace();
//        }
//        return null;
//    }

    public PermissionEntity create(PermissionEntity permissionEntity) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            PermissionModel permissionModel = permissionEntity.toModel();
            Integer.valueOf(String.valueOf(session.save(permissionModel)));
            tx.commit();
            PermissionEntity result = new PermissionEntity(permissionModel);
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return null;
    }

//    public PermissionEntity update(int permissionId, Double startX, Double startY, Double endX, Double endY, Integer shapeId) {
//        Transaction tx = null;
//        try (Session session = factory.openSession()) {
//            tx = session.beginTransaction();
//            PermissionEntity permissionEntity = new PermissionEntity(permissionId, startX, startY, endX, endY, shapeId);
//            session.update(permissionEntity.toModel());
//            tx.commit();
//            PermissionEntity result = get(permissionId);
//            return result;
//        } catch (HibernateException e) {
//            if (tx != null) tx.rollback();
//            e.printStackTrace();
//        }
//        return null;
//    }

    public PermissionEntity update(int permissionId, PermissionEntity permissionEntity) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            session.update(permissionEntity.toModel());
            tx.commit();
            PermissionEntity result = get(permissionId);
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
            PermissionModel permissionModel = new PermissionModel();
            permissionModel.setId(id);
            session.delete(permissionModel);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    public List<PermissionEntity> get(SearchPermissionEntity searchPermissionEntity) {
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<PermissionModel> criteria = builder.createQuery(PermissionModel.class);
        Root<PermissionModel> PermissionModels = criteria.from(PermissionModel.class);
        try {
            List<PermissionModel> permissionList = session.createQuery(criteria).getResultList();
            return permissionList.stream()
                    .map(s -> new PermissionEntity(s)).collect(Collectors.toList());
        } catch (NoResultException e) {
            return null;
        } finally {
            session.close();
        }
    }
}
