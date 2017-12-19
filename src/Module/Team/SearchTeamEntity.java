package Module.Team;

import Module.FilterEntity;
import Module.User.UserModel;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.QueryParam;
import java.util.List;

public class SearchTeamEntity extends FilterEntity{
    @QueryParam("id")public Integer id;
    @QueryParam("name") public String name;
    @QueryParam("locationId") public Integer locationId;
    @QueryParam("leaderEmployeeId")public Integer leaderEmployeeId;

    public List<Predicate> applyTo(CriteriaBuilder builder, List<Predicate> predicates, Root<TeamModel> root){
        if (id != null) {
            predicates.add(builder.equal(root.get(TeamModel_.id), id));
        }
        if (name != null && !name.isEmpty()) {
            predicates.add(builder.like(builder.lower(root.get(TeamModel_.name)), "%" + name.toLowerCase() + "%"));
        }
        if (locationId != null) {
            predicates.add(builder.equal(root.get(TeamModel_.locationId), locationId));
        }
        if (leaderEmployeeId != null) {
            predicates.add(builder.equal(root.get(TeamModel_.leaderEmployeeId), leaderEmployeeId));
        }
        return predicates;
    }

}
