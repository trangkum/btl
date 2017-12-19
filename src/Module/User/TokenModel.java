package Module.User;

import Module.Subcribe.SubcribeModel;

import javax.persistence.*;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "token", schema = "btl", catalog = "")
public class TokenModel {
    private int id;
    private Integer userId;
    private String tokenKey;
    private Timestamp expriedTime;
    private Collection<SubcribeModel> subcribesById;
    private UserModel userByUserId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "userId", nullable = true)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "tokenKey", nullable = true, length = -1)
    public String getTokenKey() {
        return tokenKey;
    }

    public void setTokenKey(String tokenKey) {
        this.tokenKey = tokenKey;
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
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (tokenKey != null ? !tokenKey.equals(that.tokenKey) : that.tokenKey != null) return false;
        if (expriedTime != null ? !expriedTime.equals(that.expriedTime) : that.expriedTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (tokenKey != null ? tokenKey.hashCode() : 0);
        result = 31 * result + (expriedTime != null ? expriedTime.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "tokenByTokenId",fetch = FetchType.LAZY)
    public Collection<SubcribeModel> getSubcribesById() {
        return subcribesById;
    }

    public void setSubcribesById(Collection<SubcribeModel> subcribesById) {
        this.subcribesById = subcribesById;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public UserModel getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UserModel userByUserId) {
        this.userByUserId = userByUserId;
    }
}
