package Module.Team;

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
public class TeamService {
    private static SessionFactory factory;
    private static int currentActive;

    public TeamService(SessionFactory factory) {
        this.factory = factory;
    }

    public TeamService() {
        if (factory == null || currentActive != DatabaseEntity.Active) {
            IDatabaseService databaseService = new DatabaseService();
            IDatabaseControllService databaseControllService = new DatabaseControllService();
            factory = databaseControllService.createConfiguration(databaseService.get(DatabaseEntity.Active)).buildSessionFactory();
            currentActive = DatabaseEntity.Active;
        }
    }

    public static void setFactory(SessionFactory factory) {
        TeamService.factory = factory;
    }


    public TeamEntity get(int id) {
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<TeamModel> criteria = builder.createQuery(TeamModel.class);
        Root<TeamModel> TeamModels = criteria.from(TeamModel.class);
        criteria.where(builder.equal(TeamModels.get("id"), id));
        try {
            TeamModel teamModel = session.createQuery(criteria).getSingleResult();
            return new TeamEntity(teamModel);
        } catch (NoResultException e) {
            return null;
        } finally {
            session.close();
        }
    }

//    public TeamEntity create(int teamId, Double startX, Double startY, Double endX, Double endY, Integer shapeId) {
//        Transaction tx = null;
//        try (Session session = factory.openSession()) {
//            tx = session.beginTransaction();
//            TeamEntity teamEntity = new TeamEntity(teamId, startX, startY, endX, endY, shapeId);
//            TeamModel teamModel = teamEntity.toModel();
//            Integer.valueOf(String.valueOf(session.save(teamModel)));
//            tx.commit();
//            TeamEntity result = new TeamEntity(teamModel);
//            return result;
//        } catch (HibernateException e) {
//            if (tx != null) tx.rollback();
//            e.printStackTrace();
//        }
//        return null;
//    }

    public TeamEntity create(TeamEntity teamEntity) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            TeamModel teamModel = teamEntity.toModel();
            Integer.valueOf(String.valueOf(session.save(teamModel)));
            tx.commit();
            TeamEntity result = new TeamEntity(teamModel);
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return null;
    }

//    public TeamEntity update(int teamId, Double startX, Double startY, Double endX, Double endY, Integer shapeId) {
//        Transaction tx = null;
//        try (Session session = factory.openSession()) {
//            tx = session.beginTransaction();
//            TeamEntity teamEntity = new TeamEntity(teamId, startX, startY, endX, endY, shapeId);
//            session.update(teamEntity.toModel());
//            tx.commit();
//            TeamEntity result = get(teamId);
//            return result;
//        } catch (HibernateException e) {
//            if (tx != null) tx.rollback();
//            e.printStackTrace();
//        }
//        return null;
//    }

    public TeamEntity update(int teamId, TeamEntity teamEntity) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            session.update(teamEntity.toModel());
            tx.commit();
            TeamEntity result = get(teamId);
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
            TeamModel teamModel = new TeamModel();
            teamModel.setId(id);
            session.delete(teamModel);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    public List<TeamEntity> get(SearchTeamEntity searchTeamEntity) {
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<TeamModel> criteria = builder.createQuery(TeamModel.class);
        Root<TeamModel> TeamModels = criteria.from(TeamModel.class);
        try {
            List<TeamModel> teamList = session.createQuery(criteria).getResultList();
            return teamList.stream()
                    .map(s -> new TeamEntity(s)).collect(Collectors.toList());
        } catch (NoResultException e) {
            return null;
        } finally {
            session.close();
        }
    }
}
