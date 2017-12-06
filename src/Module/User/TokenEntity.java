package Module.User;

import Module.Subcribe.SubcribeEntity;
import Module.Subcribe.SubcribeModel;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class TokenEntity implements Serializable {
    public int id;
    public Integer userId;
    public String tokenKey;
    public Timestamp expriedTime;
    public UserEntity userEntity;
    public List<SubcribeEntity> subcribeEntities;

    public TokenEntity() {
    }

    public TokenEntity(TokenModel TokenModel, Object... objects) {
        this.userId = TokenModel.getUserId();
        this.id = TokenModel.getId();
        this.tokenKey = TokenModel.getTokenKey();
        this.expriedTime = TokenModel.getExpriedTime();
        for (Object object : objects) {
            if (object instanceof UserModel) {
                this.userEntity = new UserEntity((UserModel) object);
            } else if (object instanceof Collection) {
                for (Object o : (Collection<Object>) object) {
                    if (o instanceof SubcribeModel) {
                        this.subcribeEntities = ((Collection<SubcribeModel>) object).parallelStream().map(SubcribeEntity::new).collect(Collectors.toList());
                        break;
                    }
                }
            }
        }

    }

    public TokenModel toModel() {
        TokenModel TokenModel = new TokenModel();
        TokenModel.setId(id);
        TokenModel.setExpriedTime(expriedTime);
        TokenModel.setTokenKey(tokenKey);
        TokenModel.setUserId(userId);
//        if (shapeEntityA != null) UserModel.setShapeByShapeId(shapeEntityA.toModel());
        return TokenModel;
    }
}
