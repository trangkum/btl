package Module.Role;

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
public class RoleService {
    private static SessionFactory factory;
    private static int currentActive;

    public RoleService(SessionFactory factory) {
        this.factory = factory;
    }

    public RoleService() {
        if (factory == null || currentActive != DatabaseEntity.Active) {
            IDatabaseService databaseService = new DatabaseService();
            IDatabaseControllService databaseControllService = new DatabaseControllService();
            factory = databaseControllService.createConfiguration(databaseService.get(DatabaseEntity.Active)).buildSessionFactory();
            currentActive = DatabaseEntity.Active;
        }
    }

    public static void setFactory(SessionFactory factory) {
        RoleService.factory = factory;
    }


    public RoleEntity get(int id) {
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<RoleModel> criteria = builder.createQuery(RoleModel.class);
        Root<RoleModel> RoleModels = criteria.from(RoleModel.class);
        criteria.where(builder.equal(RoleModels.get(RoleModel_.id), id));
        try {
            RoleModel roleModel = session.createQuery(criteria).getSingleResult();
            return new RoleEntity(roleModel);
        } catch (NoResultException e) {
            return null;
        } finally {
            session.close();
        }
    }

//    public RoleEntity create(int roleId, Double startX, Double startY, Double endX, Double endY, Integer shapeId) {
//        Transaction tx = null;
//        try (Session session = factory.openSession()) {
//            tx = session.beginTransaction();
//            RoleEntity roleEntity = new RoleEntity(roleId, startX, startY, endX, endY, shapeId);
//            RoleModel roleModel = roleEntity.toModel();
//            Integer.valueOf(String.valueOf(session.save(roleModel)));
//            tx.commit();
//            RoleEntity result = new RoleEntity(roleModel);
//            return result;
//        } catch (HibernateException e) {
//            if (tx != null) tx.rollback();
//            e.printStackTrace();
//        }
//        return null;
//    }

    public RoleEntity create(RoleEntity roleEntity) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            RoleModel roleModel = roleEntity.toModel();
            Integer.valueOf(String.valueOf(session.save(roleModel)));
            tx.commit();
            RoleEntity result = new RoleEntity(roleModel);
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return null;
    }

//    public RoleEntity update(int roleId, Double startX, Double startY, Double endX, Double endY, Integer shapeId) {
//        Transaction tx = null;
//        try (Session session = factory.openSession()) {
//            tx = session.beginTransaction();
//            RoleEntity roleEntity = new RoleEntity(roleId, startX, startY, endX, endY, shapeId);
//            session.update(roleEntity.toModel());
//            tx.commit();
//            RoleEntity result = getByUserName(roleId);
//            return result;
//        } catch (HibernateException e) {
//            if (tx != null) tx.rollback();
//            e.printStackTrace();
//        }
//        return null;
//    }

    public RoleEntity update(int roleId, RoleEntity roleEntity) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            session.update(roleEntity.toModel());
            tx.commit();
            RoleEntity result = get(roleId);
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
            RoleModel roleModel = new RoleModel();
            roleModel.setId(id);
            session.delete(roleModel);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    public List<RoleEntity> get(SearchRoleEntity searchRoleEntity) {
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<RoleModel> criteria = builder.createQuery(RoleModel.class);
        Root<RoleModel> RoleModels = criteria.from(RoleModel.class);
        try {
            List<RoleModel> roleList = session.createQuery(criteria).getResultList();
            return roleList.stream()
                    .map(s -> new RoleEntity(s,s.getPermissionsById())).collect(Collectors.toList());
        } catch (NoResultException e) {
            return null;
        } finally {
            session.close();
        }
    }
}
