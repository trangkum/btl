package Module.User;

import Module.Employee.EmployeeEntity;
import Module.Employee.EmployeeModel;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class UserEntity implements Serializable {
    public Integer id;
    public String userName;
    public String passWord;
    public Integer employeeId;
    public List<TokenEntity> tokenEntities;
    public EmployeeEntity employeeEntity;


    public UserEntity() {
    }

    public UserEntity(Integer id, String userName, String passWord, Integer employeeId) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.employeeId = employeeId;
    }

    public UserEntity(UserModel UserModel, Object... objects) {
        this.userName = UserModel.getUserName();
        this.passWord = UserModel.getPassWord();
        this.employeeId = UserModel.getEmployeeId();
        this.id = UserModel.getId();
        for (Object object : objects) {
            if (object instanceof EmployeeModel) {
                this.employeeEntity = new EmployeeEntity((EmployeeModel) object);
            } else if (object instanceof Collection) {
                for (Object o : (Collection<Object>) object) {
                    if (o instanceof TokenModel) {
                        this.tokenEntities = ((Collection<TokenModel>) object).parallelStream().map(TokenEntity::new).collect(Collectors.toList());
                        break;
                    }
                }
            }
        }
    }

    public UserModel toModel() {
        UserModel UserModel = new UserModel();
        UserModel.setId(id);
        UserModel.setUserName(userName);
        UserModel.setPassWord(passWord);
        UserModel.setEmployeeId(employeeId);
//        if (shapeEntityA != null) UserModel.setShapeByShapeId(shapeEntityA.toModel());
        return UserModel;
    }
}
