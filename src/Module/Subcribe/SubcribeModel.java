package Module.Subcribe;


import Module.User.TokenModel;

import javax.persistence.*;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.sql.Timestamp;

@Entity
@Table(name = "subcribe", schema = "btl", catalog = "")
public class SubcribeModel {
    private int id;
    private String endpoint;
    private String tokenKey;
    private Integer tokenId;
    private TokenModel tokenByTokenId;

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
    @Column(name = "endpoint", nullable = true, length = -1)
    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
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
    @Column(name = "tokenId", nullable = true)
    public Integer getTokenId() {
        return tokenId;
    }

    public void setTokenId(Integer tokenId) {
        this.tokenId = tokenId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubcribeModel that = (SubcribeModel) o;

        if (id != that.id) return false;
        if (endpoint != null ? !endpoint.equals(that.endpoint) : that.endpoint != null) return false;
        if (tokenKey != null ? !tokenKey.equals(that.tokenKey) : that.tokenKey != null) return false;
        if (tokenId != null ? !tokenId.equals(that.tokenId) : that.tokenId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (endpoint != null ? endpoint.hashCode() : 0);
        result = 31 * result + (tokenKey != null ? tokenKey.hashCode() : 0);
        result = 31 * result + (tokenId != null ? tokenId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "tokenId", referencedColumnName = "id", insertable = false, updatable = false)
    public TokenModel getTokenByTokenId() {
        return tokenByTokenId;
    }

    public void setTokenByTokenId(TokenModel tokenByTokenId) {
        this.tokenByTokenId = tokenByTokenId;
    }
}

@StaticMetamodel(SubcribeModel.class)
class SubcribeModel_ {
    public static volatile SingularAttribute<SubcribeModel, Integer> id;
    public static volatile SingularAttribute<SubcribeModel, String> endpoint;
    public static volatile SingularAttribute<SubcribeModel, String> tokenKey;
    public static volatile SingularAttribute<SubcribeModel, Integer> tokenId;
}


