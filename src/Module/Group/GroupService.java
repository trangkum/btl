package Module.Group;

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
public class GroupService {
    private static SessionFactory factory;
    private static int currentActive;

    public GroupService(SessionFactory factory) {
        this.factory = factory;
    }

    public GroupService() {
        if (factory == null || currentActive != DatabaseEntity.Active) {
            IDatabaseService databaseService = new DatabaseService();
            IDatabaseControllService databaseControllService = new DatabaseControllService();
            factory = databaseControllService.createConfiguration(databaseService.get(DatabaseEntity.Active)).buildSessionFactory();
            currentActive = DatabaseEntity.Active;
        }
    }

    public static void setFactory(SessionFactory factory) {
        GroupService.factory = factory;
    }


    public GroupEntity get(int id) {
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<GroupModel> criteria = builder.createQuery(GroupModel.class);
        Root<GroupModel> EdgeEntities = criteria.from(GroupModel.class);
        criteria.where(builder.equal(EdgeEntities.get("edgeId"), id));
        try {
            GroupModel groupModel = session.createQuery(criteria).getSingleResult();
            return new GroupEntity(groupModel);
        } catch (NoResultException e) {
            return null;
        } finally {
            session.close();
        }
    }

    public GroupEntity create(int edgeId, Double startX, Double startY, Double endX, Double endY, Integer shapeId) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            GroupEntity groupEntity = new GroupEntity(edgeId, startX, startY, endX, endY, shapeId);
            GroupModel groupModel = groupEntity.toModel();
            Integer.valueOf(String.valueOf(session.save(groupModel)));
            tx.commit();
            GroupEntity result = new GroupEntity(groupModel);
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return null;
    }

    public GroupEntity create(GroupEntity groupEntity) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            GroupModel groupModel = groupEntity.toModel();
            Integer.valueOf(String.valueOf(session.save(groupModel)));
            tx.commit();
            GroupEntity result = new GroupEntity(groupModel);
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return null;
    }

    public GroupEntity update(int edgeId, Double startX, Double startY, Double endX, Double endY, Integer shapeId) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            GroupEntity groupEntity = new GroupEntity(edgeId, startX, startY, endX, endY, shapeId);
            session.update(groupEntity.toModel());
            tx.commit();
            GroupEntity result = get(edgeId);
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return null;
    }

    public GroupEntity update(int edgeId, GroupEntity groupEntity) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            session.update(groupEntity.toModel());
            tx.commit();
            GroupEntity result = get(edgeId);
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
            GroupModel groupModel = new GroupModel();
            groupModel.setEdgeId(id);
            session.delete(groupModel);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    public List<GroupEntity> get(SearchGroupModel searchGroupModel) {
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<GroupModel> criteria = builder.createQuery(GroupModel.class);
        Root<GroupModel> EdgeEntities = criteria.from(GroupModel.class);
        try {
            List<GroupModel> edgeList = session.createQuery(criteria).getResultList();
            return edgeList.stream()
                    .map(s -> new GroupEntity(s)).collect(Collectors.toList());
        } catch (NoResultException e) {
            return null;
        } finally {
            session.close();
        }
    }
}
