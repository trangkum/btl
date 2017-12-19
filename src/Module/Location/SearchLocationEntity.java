package Module.Location;

import Module.FilterEntity;
import Module.User.UserModel;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.QueryParam;

public class SearchLocationEntity extends FilterEntity {
    @QueryParam("id")
    public Integer id;
    @QueryParam("name")
    public String name;
    @QueryParam("managerEmployeeId")
    public Integer managerEmployeeId;

    public CriteriaQuery<LocationModel> applyTo(CriteriaBuilder builder, CriteriaQuery<LocationModel> criteria, Root<LocationModel> root) {
        return criteria;
    }

}
