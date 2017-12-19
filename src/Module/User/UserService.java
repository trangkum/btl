package Module.User;

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
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class UserService {
    private static SessionFactory factory;
    private static int currentActive;

    public UserService(SessionFactory factory) {
        this.factory = factory;
    }

    public UserService() {
        if (factory == null || currentActive != DatabaseEntity.Active) {
            IDatabaseService databaseService = new DatabaseService();
            IDatabaseControllService databaseControllService = new DatabaseControllService();
            factory = databaseControllService.createConfiguration(databaseService.get(DatabaseEntity.Active)).buildSessionFactory();
            currentActive = DatabaseEntity.Active;
        }
    }

    public static void setFactory(SessionFactory factory) {
        UserService.factory = factory;
    }


    public UserEntity getByUserName(String userName) {
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserModel> criteria = builder.createQuery(UserModel.class);
        Root<UserModel> UserModels = criteria.from(UserModel.class);
        criteria.where(builder.equal(UserModels.get(UserModel_.userName), userName));
        try {
            UserModel userModel = session.createQuery(criteria).getSingleResult();
            return new UserEntity(userModel);
        } catch (NoResultException e) {
            return null;
        } finally {
            session.close();
        }
    }

    public UserEntity get(Integer userId) {
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserModel> criteria = builder.createQuery(UserModel.class);
        Root<UserModel> UserModels = criteria.from(UserModel.class);
        criteria.where(builder.equal(UserModels.get(UserModel_.id), userId));
        try {
            UserModel userModel = session.createQuery(criteria).getSingleResult();
            return new UserEntity(userModel);
        } catch (NoResultException e) {
            return null;
        } finally {
            session.close();
        }
    }

//    public UserEntity create(int userId, Double startX, Double startY, Double endX, Double endY, Integer shapeId) {
//        Transaction tx = null;
//        try (Session session = factory.openSession()) {
//            tx = session.beginTransaction();
//            UserEntity userEntity = new UserEntity(userId, startX, startY, endX, endY, shapeId);
//            UserModel userModel = userEntity.toModel();
//            Integer.valueOf(String.valueOf(session.save(userModel)));
//            tx.commit();
//            UserEntity result = new UserEntity(userModel);
//            return result;
//        } catch (HibernateException e) {
//            if (tx != null) tx.rollback();
//            e.printStackTrace();
//        }
//        return null;
//    }

    public UserEntity create(UserEntity userEntity) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            UserModel userModel = userEntity.toModel();
            Integer.valueOf(String.valueOf(session.save(userModel)));
            tx.commit();
            UserEntity result = new UserEntity(userModel);
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return null;
    }

//    public UserEntity update(int userId, Double startX, Double startY, Double endX, Double endY, Integer shapeId) {
//        Transaction tx = null;
//        try (Session session = factory.openSession()) {
//            tx = session.beginTransaction();
//            UserEntity userEntity = new UserEntity(userId, startX, startY, endX, endY, shapeId);
//            session.update(userEntity.toModel());
//            tx.commit();
//            UserEntity result = getByUserName(userId);
//            return result;
//        } catch (HibernateException e) {
//            if (tx != null) tx.rollback();
//            e.printStackTrace();
//        }
//        return null;
//    }

    public UserEntity update(String userName, UserEntity userEntity) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            session.update(userEntity.toModel());
            tx.commit();
            UserEntity result = getByUserName(userName);
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
//            UserModel userModel = new UserModel();
//            userModel.set(id);
//            session.delete(userModel);
//            tx.commit();
//            return true;
//        } catch (HibernateException e) {
//            if (tx != null) tx.rollback();
//            e.printStackTrace();
//        }
//        return false;
//    }

    public List<UserEntity> get(SearchUserEntity searchUserEntity) {
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserModel> criteria = builder.createQuery(UserModel.class);
        Root<UserModel> UserModels = criteria.from(UserModel.class);
        try {
            List<Predicate> predicates = searchUserEntity.applyTo(builder, new ArrayList<>(), UserModels);
            List<UserModel> userEntities = session.createQuery(criteria.where(predicates.toArray(new Predicate[]{}))).getResultList();
//            List<UserModel> userEntities = searchUserEntity.skipAndTake(session.createQuery(searchUserEntity.order(builder, criteriaQuery, UserModels))).getResultList();
            return userEntities.stream()
                    .map(s -> new UserEntity(s)).collect(Collectors.toList());
        } catch (NoResultException e) {
            return null;
        } finally {
            session.close();
        }
    }
}
