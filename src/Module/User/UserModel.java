package Module.User;


import Module.Employee.EmployeeModel;

import javax.persistence.*;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Collection;

@Entity
@Table(name = "user", schema = "btl", catalog = "")
public class UserModel {
    private String userName;
    private String passWord;
    private Integer employeeId;
    private int id;
    private Collection<TokenModel> tokensById;
    private EmployeeModel employeeByEmployeeId;

    @Basic
    @Column(name = "userName", nullable = false, length = 30)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "passWord", nullable = true, length = 1000)
    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Basic
    @Column(name = "employeeId", nullable = true)
    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserModel userModel = (UserModel) o;

        if (id != userModel.id) return false;
        if (userName != null ? !userName.equals(userModel.userName) : userModel.userName != null) return false;
        if (passWord != null ? !passWord.equals(userModel.passWord) : userModel.passWord != null) return false;
        if (employeeId != null ? !employeeId.equals(userModel.employeeId) : userModel.employeeId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userName != null ? userName.hashCode() : 0;
        result = 31 * result + (passWord != null ? passWord.hashCode() : 0);
        result = 31 * result + (employeeId != null ? employeeId.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }

    @OneToMany(mappedBy = "userByUserId", fetch = FetchType.LAZY)
    public Collection<TokenModel> getTokensById() {
        return tokensById;
    }

    public void setTokensById(Collection<TokenModel> tokensById) {
        this.tokensById = tokensById;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employeeId", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public EmployeeModel getEmployeeByEmployeeId() {
        return employeeByEmployeeId;
    }

    public void setEmployeeByEmployeeId(EmployeeModel employeeByEmployeeId) {
        this.employeeByEmployeeId = employeeByEmployeeId;
    }
}

@StaticMetamodel(UserModel.class)
class UserModel_ {
    public static volatile SingularAttribute<UserModel, Integer> id;
    public static volatile SingularAttribute<UserModel, String> userName;
    public static volatile SingularAttribute<UserModel, String> passWord;
    public static volatile SingularAttribute<UserModel, Integer> employeeId;
}
