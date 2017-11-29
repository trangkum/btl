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


    public EmployeeEntity get(int id) {
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<EmployeeModel> criteria = builder.createQuery(EmployeeModel.class);
        Root<EmployeeModel> EmployeeModels = criteria.from(EmployeeModel.class);
        criteria.where(builder.equal(EmployeeModels.get(EmployeeModel_.id), id));
        try {
            EmployeeModel employeeModel = session.createQuery(criteria).getSingleResult();
            return new EmployeeEntity(employeeModel);
        } catch (NoResultException e) {
            return null;
        } finally {
            session.close();
        }
    }

//    public EmployeeEntity create(int employeeId, Double startX, Double startY, Double endX, Double endY, Integer shapeId) {
//        Transaction tx = null;
//        try (Session session = factory.openSession()) {
//            tx = session.beginTransaction();
//            EmployeeEntity employeeEntity = new EmployeeEntity(employeeId, startX, startY, endX, endY, shapeId);
//            EmployeeModel employeeModel = employeeEntity.toModel();
//            Integer.valueOf(String.valueOf(session.save(employeeModel)));
//            tx.commit();
//            EmployeeEntity result = new EmployeeEntity(employeeModel);
//            return result;
//        } catch (HibernateException e) {
//            if (tx != null) tx.rollback();
//            e.printStackTrace();
//        }
//        return null;
//    }

    public EmployeeEntity create(EmployeeEntity employeeEntity) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            EmployeeModel employeeModel = employeeEntity.toModel();
            Integer.valueOf(String.valueOf(session.save(employeeModel)));
            tx.commit();
            EmployeeEntity result = new EmployeeEntity(employeeModel);
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return null;
    }

//    public EmployeeEntity update(int employeeId, Double startX, Double startY, Double endX, Double endY, Integer shapeId) {
//        Transaction tx = null;
//        try (Session session = factory.openSession()) {
//            tx = session.beginTransaction();
//            EmployeeEntity employeeEntity = new EmployeeEntity(employeeId, startX, startY, endX, endY, shapeId);
//            session.update(employeeEntity.toModel());
//            tx.commit();
//            EmployeeEntity result = get(employeeId);
//            return result;
//        } catch (HibernateException e) {
//            if (tx != null) tx.rollback();
//            e.printStackTrace();
//        }
//        return null;
//    }

    public EmployeeEntity update(int employeeId, EmployeeEntity employeeEntity) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            session.update(employeeEntity.toModel());
            tx.commit();
            EmployeeEntity result = get(employeeId);
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
            EmployeeModel employeeModel = new EmployeeModel();
            employeeModel.setId(id);
            session.delete(employeeModel);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    public List<EmployeeEntity> get(SearchEmployeeEntity searchEmployeeEntity) {
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<EmployeeModel> criteria = builder.createQuery(EmployeeModel.class);
        Root<EmployeeModel> EmployeeModels = criteria.from(EmployeeModel.class);
        try {
            CriteriaQuery criteriaQuery = searchEmployeeEntity.applyTo(builder,criteria,EmployeeModels);
            List<EmployeeModel> employeeModels = searchEmployeeEntity.skipAndTake(session.createQuery(searchEmployeeEntity.order(builder,criteriaQuery,EmployeeModels))).getResultList();
            return employeeModels.stream()
                    .map(s -> new EmployeeEntity(s)).collect(Collectors.toList());
        } catch (NoResultException e) {
            return null;
        } finally {
            session.close();
        }
    }
}
