package Module.Route;

import Module.FilterEntity;
import Module.User.UserModel;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.QueryParam;

public class SearchRouteEntity extends FilterEntity {
    @QueryParam("id")
    public Integer id;
    @QueryParam("name")
    public String name;
    @QueryParam("method")
    public String method;
    @QueryParam("route")
    public String route;

    public CriteriaQuery<RouteModel> applyTo(CriteriaBuilder builder, CriteriaQuery<RouteModel> criteria, Root<RouteModel> root) {
        if (id != null) {
            criteria.where(builder.equal(root.get(RouteModel_.id), id));
        }
        if (name != null && !name.isEmpty()) {
            criteria.where(builder.like(builder.lower(root.get(RouteModel_.name)), "%" + name.toLowerCase() + "%"));
        }
        if (method != null && !method.isEmpty()) {
            criteria.where(builder.like(builder.lower(root.get(RouteModel_.method)), "%" + method.toLowerCase() + "%"));
        }
        if (route != null && !route.isEmpty()) {
            criteria.where(builder.like(builder.lower(root.get(RouteModel_.route)), "%" + route.toLowerCase() + "%"));
        }
        return criteria;
    }
}
