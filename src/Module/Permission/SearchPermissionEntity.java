package Module.Permission;

import Module.FilterEntity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.QueryParam;

public class SearchPermissionEntity extends FilterEntity {
    @QueryParam("id")
    public Integer id;
    @QueryParam("roleId")
    public Integer roleId;
    @QueryParam("routeId")
    public Integer routeId;

    public CriteriaQuery<PermissionModel> applyTo(CriteriaBuilder builder, CriteriaQuery<PermissionModel> criteria, Root<PermissionModel> root) {
        if (id != null) {
            criteria.where(builder.equal(root.get(PermissionModel_.id), id));
        }
        if (roleId != null) {
            criteria.where(builder.equal(root.get(PermissionModel_.roleId), roleId));
        }
        if (routeId != null) {
            criteria.where(builder.equal(root.get(PermissionModel_.id), routeId));
        }
        return criteria;
    }
}
