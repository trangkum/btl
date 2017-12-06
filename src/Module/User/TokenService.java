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
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Son on 6/15/2017.
 */
public class TokenService {
    private static SessionFactory factory;
    private static int currentActive;

    public TokenService(SessionFactory factory) {
        this.factory = factory;
    }

    public TokenService() {
        if (factory == null || currentActive != DatabaseEntity.Active) {
            IDatabaseService databaseService = new DatabaseService();
            IDatabaseControllService databaseControllService = new DatabaseControllService();
            factory = databaseControllService.createConfiguration(databaseService.get(DatabaseEntity.Active)).buildSessionFactory();
            currentActive = DatabaseEntity.Active;
        }
    }

    public static void setFactory(SessionFactory factory) {
        TokenService.factory = factory;
    }


    public TokenEntity get(Integer id) {
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<TokenModel> criteria = builder.createQuery(TokenModel.class);
        Root<TokenModel> TokenModels = criteria.from(TokenModel.class);
        criteria.where(builder.equal(TokenModels.get("id"), id));
        try {
            TokenModel tokenModel = session.createQuery(criteria).getSingleResult();
            return new TokenEntity(tokenModel);
        } catch (NoResultException e) {
            return null;
        } finally {
            session.close();
        }
    }

    public TokenEntity getByTokenKey(String tokenKey) {
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<TokenModel> criteria = builder.createQuery(TokenModel.class);
        Root<TokenModel> TokenModels = criteria.from(TokenModel.class);
        criteria.where(builder.equal(TokenModels.get("tokenKey"), tokenKey));
        try {
            TokenModel tokenModel = session.createQuery(criteria).getSingleResult();
            return new TokenEntity(tokenModel);
        } catch (NoResultException e) {
            return null;
        } finally {
            session.close();
        }
    }

//    public TokenEntity create(int tokenId, Double startX, Double startY, Double endX, Double endY, Integer shapeId) {
//        Transaction tx = null;
//        try (Session session = factory.openSession()) {
//            tx = session.beginTransaction();
//            TokenEntity tokenEntity = new TokenEntity(tokenId, startX, startY, endX, endY, shapeId);
//            TokenModel tokenModel = tokenEntity.toModel();
//            Integer.valueOf(String.valueOf(session.save(tokenModel)));
//            tx.commit();
//            TokenEntity result = new TokenEntity(tokenModel);
//            return result;
//        } catch (HibernateException e) {
//            if (tx != null) tx.rollback();
//            e.printStackTrace();
//        }
//        return null;
//    }

    public TokenEntity create(TokenEntity tokenEntity) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            TokenModel tokenModel = tokenEntity.toModel();
            Integer.valueOf(String.valueOf(session.save(tokenModel)));
            tx.commit();
            TokenEntity result = new TokenEntity(tokenModel);
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return null;
    }

//    public TokenEntity update(int tokenId, Double startX, Double startY, Double endX, Double endY, Integer shapeId) {
//        Transaction tx = null;
//        try (Session session = factory.openSession()) {
//            tx = session.beginTransaction();
//            TokenEntity tokenEntity = new TokenEntity(tokenId, startX, startY, endX, endY, shapeId);
//            session.update(tokenEntity.toModel());
//            tx.commit();
//            TokenEntity result = get(tokenId);
//            return result;
//        } catch (HibernateException e) {
//            if (tx != null) tx.rollback();
//            e.printStackTrace();
//        }
//        return null;
//    }

    public TokenEntity update(Integer id, TokenEntity tokenEntity) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            session.update(tokenEntity.toModel());
            tx.commit();
            TokenEntity result = get(id);
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return null;
    }

    public boolean delete(Integer id) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            TokenModel tokenModel = new TokenModel();
            tokenModel.setId(id);
            session.delete(tokenModel);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    public List<TokenEntity> getTokensByUser(String userName) {
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<TokenModel> criteria = builder.createQuery(TokenModel.class);
        Root<TokenModel> TokenModels = criteria.from(TokenModel.class);
        try {
            criteria.where(builder.equal(TokenModels.get("userName"), userName));
            List<TokenModel> tokenEntities = session.createQuery(criteria).getResultList();
//            List<TokenModel> tokenEntities = searchTokenEntity.skipAndTake(session.createQuery(searchTokenEntity.order(builder, criteriaQuery, TokenModels))).getResultList();
            return tokenEntities.stream()
                    .map(s -> new TokenEntity(s)).collect(Collectors.toList());
        } catch (NoResultException e) {
            return null;
        } finally {
            session.close();
        }
    }
}
