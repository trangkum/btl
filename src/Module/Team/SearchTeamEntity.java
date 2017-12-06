package Module.Team;

import Module.FilterEntity;
import Module.User.UserModel;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.QueryParam;

public class SearchTeamEntity extends FilterEntity{
    @QueryParam("id")public Integer id;
    @QueryParam("name") public String name;
    @QueryParam("locationId") public Integer locationId;
    @QueryParam("leaderEmployeeId")public Integer leaderEmployeeId;

    public CriteriaQuery<TeamModel> applyTo(CriteriaBuilder builder, CriteriaQuery<TeamModel> criteria, Root<TeamModel> root){
        if (id != null) {
            criteria.where(builder.equal(root.get(TeamModel_.id), id));
        }
        if (name != null && !name.isEmpty()) {
            criteria.where(builder.like(builder.lower(root.get(TeamModel_.name)), "%" + name.toLowerCase() + "%"));
        }
        if (locationId != null) {
            criteria.where(builder.equal(root.get(TeamModel_.locationId), locationId));
        }
        if (leaderEmployeeId != null) {
            criteria.where(builder.equal(root.get(TeamModel_.leaderEmployeeId), leaderEmployeeId));
        }
        return criteria;
    }

}
