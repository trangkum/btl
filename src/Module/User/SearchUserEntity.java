package Module.User;

import Module.FilterEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.QueryParam;

public class SearchUserEntity extends FilterEntity {
    @QueryParam("userName")
    private String userName;
    @QueryParam("passWord")
    private String passWord;
    @QueryParam("employeeId")
    private Integer employeeId;


    public CriteriaQuery<UserModel> applyTo(CriteriaBuilder builder, CriteriaQuery<UserModel> criteria, Root<UserModel> root) {
//        if (employeeId != null) {
//            criteria.where(builder.equal(root.get(UserModel_.employeeId), employeeId));
//        }
//        if (userName != null && !userName.isEmpty()) {
//            criteria.where(builder.like(builder.lower(root.get(UserModel_.userName)), "%" + userName.toLowerCase() + "%"));
//        }
//        if (passWord != null && !passWord.isEmpty()) {
//            criteria.where(builder.like(builder.lower(root.get(UserModel_.passWord)), "%" + passWord.toLowerCase() + "%"));
//        }
        return criteria;
    }
}
