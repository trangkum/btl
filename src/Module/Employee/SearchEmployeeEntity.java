package Module.Employee;

import Module.FilterEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.QueryParam;
import java.util.List;

public class SearchEmployeeEntity extends FilterEntity {
    @QueryParam("id") public Integer id;
    @QueryParam("email") public String email;
    @QueryParam("name") public String name;
    @QueryParam("teamId")public Integer teamId;
    @QueryParam("locationId")public Integer locationId;
    @QueryParam("briefName")public String briefName;
    public List<Predicate> applyTo(CriteriaBuilder builder, List<Predicate> predicates, Root<EmployeeModel> root) {
        if (id != null) {
            predicates.add(builder.equal(root.get(EmployeeModel_.id), id));
        }
        if (email != null && !email.isEmpty()) {
            predicates.add(builder.like(builder.lower(root.get(EmployeeModel_.email)), "%" + email.toLowerCase() + "%"));
        }
        if (name != null && !name.isEmpty()) {
            predicates.add(builder.like(builder.lower(root.get(EmployeeModel_.name)), "%" + name.toLowerCase() + "%"));
        }
        if (teamId != null) {
            predicates.add(builder.equal(root.get(EmployeeModel_.teamId), teamId));
        }
        if (locationId != null) {
            predicates.add(builder.equal(root.get(EmployeeModel_.locationId), locationId));
        }
        if (briefName != null && !briefName.isEmpty()) {
            predicates.add(builder.like(builder.lower(root.get(EmployeeModel_.briefName)), "%" + briefName.toLowerCase() + "%"));
        }
        return predicates;
    }
}
