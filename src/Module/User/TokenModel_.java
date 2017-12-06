package Module.User;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.sql.Timestamp;

@StaticMetamodel(TokenModel.class)
public class TokenModel_ {
    public static volatile SingularAttribute<TokenModel, String> tokenKey;
    public static volatile SingularAttribute<TokenModel, String> userName;
    public static volatile SingularAttribute<TokenModel, Integer> id;
    public static volatile SingularAttribute<TokenModel, Timestamp> expriedTime;
}