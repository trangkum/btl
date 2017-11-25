package Module.Employee;

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
public class EmployeeService {
    private static SessionFactory factory;
    private static int currentActive;

    public EmployeeService(SessionFactory factory) {
        this.factory = factory;
    }

    public EmployeeService() {
        if (factory == null || currentActive != DatabaseEntity.Active) {
            IDatabaseService databaseService = new DatabaseService();
            IDatabaseControllService databaseControllService = new DatabaseControllService();
            factory = databaseControllService.createConfiguration(databaseService.get(DatabaseEntity.Active)).buildSessionFactory();
            currentActive = DatabaseEntity.Active;
        }
    }

    public static void setFactory(SessionFactory factory) {
        EmployeeService.factory = factory;
    }


    public FileEntity get(int id) {
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<FileModel> criteria = builder.createQuery(FileModel.class);
        Root<FileModel> EdgeEntities = criteria.from(FileModel.class);
        criteria.where(builder.equal(EdgeEntities.get("edgeId"), id));
        try {
            FileModel fileModel = session.createQuery(criteria).getSingleResult();
            return new FileEntity(fileModel);
        } catch (NoResultException e) {
            return null;
        } finally {
            session.close();
        }
    }

    public FileEntity create(int edgeId, Double startX, Double startY, Double endX, Double endY, Integer shapeId) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            FileEntity fileEntity = new FileEntity(edgeId, startX, startY, endX, endY, shapeId);
            FileModel fileModel = fileEntity.toModel();
            Integer.valueOf(String.valueOf(session.save(fileModel)));
            tx.commit();
            FileEntity result = new FileEntity(fileModel);
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return null;
    }

    public FileEntity create(FileEntity fileEntity) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            FileModel fileModel = fileEntity.toModel();
            Integer.valueOf(String.valueOf(session.save(fileModel)));
            tx.commit();
            FileEntity result = new FileEntity(fileModel);
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return null;
    }

    public FileEntity update(int edgeId, Double startX, Double startY, Double endX, Double endY, Integer shapeId) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            FileEntity fileEntity = new FileEntity(edgeId, startX, startY, endX, endY, shapeId);
            session.update(fileEntity.toModel());
            tx.commit();
            FileEntity result = get(edgeId);
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return null;
    }

    public FileEntity update(int edgeId, FileEntity fileEntity) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            session.update(fileEntity.toModel());
            tx.commit();
            FileEntity result = get(edgeId);
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
            FileModel fileModel = new FileModel();
            fileModel.setEdgeId(id);
            session.delete(fileModel);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    public List<FileEntity> get(SearchEmployeeModel searchEmployeeModel) {
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<FileModel> criteria = builder.createQuery(FileModel.class);
        Root<FileModel> EdgeEntities = criteria.from(FileModel.class);
        try {
            List<FileModel> edgeList = session.createQuery(criteria).getResultList();
            return edgeList.stream()
                    .map(s -> new FileEntity(s)).collect(Collectors.toList());
        } catch (NoResultException e) {
            return null;
        } finally {
            session.close();
        }
    }
}
