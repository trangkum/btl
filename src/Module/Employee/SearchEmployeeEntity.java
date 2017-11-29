package Module.Employee;

import Module.FilterEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.QueryParam;

public class SearchEmployeeEntity extends FilterEntity {
    @QueryParam("id") public Integer id;
    @QueryParam("email") public String email;
    @QueryParam("name") public String name;
    @QueryParam("teamId")public Integer teamId;
    @QueryParam("groupId")public Integer groupId;
    @QueryParam("briefName")public String briefName;
    public CriteriaQuery<EmployeeModel> applyTo(CriteriaBuilder builder, CriteriaQuery<EmployeeModel> criteria, Root<EmployeeModel> root) {
        if (id != null) {
            criteria.where(builder.equal(root.get(EmployeeModel_.id), id));
        }
        if (email != null && !email.isEmpty()) {
            criteria.where(builder.like(builder.lower(root.get(EmployeeModel_.email)), "%" + email.toLowerCase() + "%"));
        }
        if (name != null && !name.isEmpty()) {
            criteria.where(builder.like(builder.lower(root.get(EmployeeModel_.name)), "%" + name.toLowerCase() + "%"));
        }
        if (teamId != null) {
            criteria.where(builder.equal(root.get(EmployeeModel_.teamId), teamId));
        }
        if (groupId != null) {
            criteria.where(builder.equal(root.get(EmployeeModel_.groupId), groupId));
        }
        if (briefName != null && !briefName.isEmpty()) {
            criteria.where(builder.like(builder.lower(root.get(EmployeeModel_.briefName)), "%" + briefName.toLowerCase() + "%"));
        }
        return criteria;
    }
}
