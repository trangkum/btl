package Module.Group;

import Module.FilterEntity;
import Module.User.UserModel;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.QueryParam;
import java.security.acl.Group;

public class SearchGroupEntity extends FilterEntity{
    @QueryParam("id")private Integer id;
    @QueryParam("name") private String name;
    @QueryParam("managerEmployeeId") private Integer managerEmployeeId;

    public CriteriaQuery<GroupModel> applyTo(CriteriaBuilder builder, CriteriaQuery<GroupModel> criteria, Root<GroupModel> root){
        return criteria;
    }

}
