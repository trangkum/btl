package Module.Role;

import Module.FilterEntity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.QueryParam;
import java.util.List;

public class SearchRoleEntity extends FilterEntity {
    @QueryParam("id")
    public Integer id;
    @QueryParam("name")
    public String name;
    @QueryParam("description")
    public String description;

    public List<Predicate> applyTo(CriteriaBuilder builder, List<Predicate> predicates, Root<RoleModel> root) {
        if (id != null) {
            predicates.add(builder.equal(root.get(RoleModel_.id), id));
        }
        if (name != null && !name.isEmpty()) {
            predicates.add(builder.like(builder.lower(root.get(RoleModel_.name)), "%" + name.toLowerCase() + "%"));
        }
        if (description != null && !description.isEmpty()) {
            predicates.add(builder.like(builder.lower(root.get(RoleModel_.description)), "%" + description.toLowerCase() + "%"));
        }
        return predicates;
    }

}
