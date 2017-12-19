package Module.User;

import Module.FilterEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.QueryParam;
import java.util.List;

public class SearchUserEntity extends FilterEntity {
    @QueryParam("userName")
    private String userName;
    @QueryParam("passWord")
    private String passWord;
    @QueryParam("employeeId")
    private Integer employeeId;


    public List<Predicate> applyTo(CriteriaBuilder builder, List<Predicate> predicates, Root<UserModel> root) {
//        if (employeeId != null) {
//            predicates.add(builder.equal(root.get(UserModel_.employeeId), employeeId));
//        }
//        if (userName != null && !userName.isEmpty()) {
//            predicates.add(builder.like(builder.lower(root.get(UserModel_.userName)), "%" + userName.toLowerCase() + "%"));
//        }
//        if (passWord != null && !passWord.isEmpty()) {
//            predicates.add(builder.like(builder.lower(root.get(UserModel_.passWord)), "%" + passWord.toLowerCase() + "%"));
//        }
        return predicates;
    }
}
