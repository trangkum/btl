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
    private Integer id;
    @QueryParam("name")
    private String name;
    @QueryParam("managerEmployeeId")
    private Integer managerEmployeeId;

    public CriteriaQuery<LocationModel> applyTo(CriteriaBuilder builder, CriteriaQuery<LocationModel> criteria, Root<LocationModel> root) {
        return criteria;
    }

}
