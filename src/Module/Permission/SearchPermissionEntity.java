package Module.Permission;

import Module.FilterEntity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.QueryParam;
import java.util.List;

public class SearchPermissionEntity extends FilterEntity {
    @QueryParam("id")
    public Integer id;
    @QueryParam("roleId")
    public Integer roleId;
    @QueryParam("routeId")
    public Integer routeId;

    public List<Predicate> applyTo(CriteriaBuilder builder, List<Predicate> predicates, Root<PermissionModel> root) {
        if (id != null) {
            predicates.add(builder.equal(root.get(PermissionModel_.id), id));
        }
        if (roleId != null) {
            predicates.add(builder.equal(root.get(PermissionModel_.roleId), roleId));
        }
        if (routeId != null) {
            predicates.add(builder.equal(root.get(PermissionModel_.id), routeId));
        }
        return predicates;
    }
}
