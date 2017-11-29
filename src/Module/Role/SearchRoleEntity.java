package Module.Role;

import Module.FilterEntity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.QueryParam;

public class SearchRoleEntity extends FilterEntity {
    @QueryParam("id")
    public Integer id;
    @QueryParam("name")
    public String name;
    @QueryParam("description")
    public String description;

    public CriteriaQuery<RoleModel> applyTo(CriteriaBuilder builder, CriteriaQuery<RoleModel> criteria, Root<RoleModel> root) {
        if (id != null) {
            criteria.where(builder.equal(root.get(RoleModel_.id), id));
        }
        if (name != null && !name.isEmpty()) {
            criteria.where(builder.like(builder.lower(root.get(RoleModel_.name)), "%" + name.toLowerCase() + "%"));
        }
        if (description != null && !description.isEmpty()) {
            criteria.where(builder.like(builder.lower(root.get(RoleModel_.description)), "%" + description.toLowerCase() + "%"));
        }
        return criteria;
    }

}
