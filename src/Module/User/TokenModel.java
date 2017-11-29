package Module.User;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "token", schema = "btl", catalog = "")
public class TokenModel {
    private int id;
    private String userName;
    private String key;
    private Timestamp expriedTime;
    private UserModel userByUserName;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "userName", nullable = true, length = 30)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "key", nullable = true, length = -1)
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Basic
    @Column(name = "expriedTime", nullable = true)
    public Timestamp getExpriedTime() {
        return expriedTime;
    }

    public void setExpriedTime(Timestamp expriedTime) {
        this.expriedTime = expriedTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TokenModel that = (TokenModel) o;

        if (id != that.id) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        if (key != null ? !key.equals(that.key) : that.key != null) return false;
        if (expriedTime != null ? !expriedTime.equals(that.expriedTime) : that.expriedTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (key != null ? key.hashCode() : 0);
        result = 31 * result + (expriedTime != null ? expriedTime.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "userName", referencedColumnName = "userName", insertable = false, updatable = false)
    public UserModel getUserByUserName() {
        return userByUserName;
    }

    public void setUserByUserName(UserModel userByUserName) {
        this.userByUserName = userByUserName;
    }
}
