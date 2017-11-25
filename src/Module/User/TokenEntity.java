package Module.User;

import java.io.Serializable;
import java.sql.Timestamp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class TokenEntity implements Serializable {
    public int id;
    public String userName;
    public String key;
    public Timestamp expriedTime;
    public UserEntity userEntity;


    public TokenEntity() {
    }

    public TokenEntity(TokenModel TokenModel, Object... objects) {
        this.userName = TokenModel.getUserName();
        this.id = TokenModel.getId();
        this.key = TokenModel.getKey();
        this.expriedTime = TokenModel.getExpriedTime();
        for (Object object : objects) {
            if (object instanceof UserModel) {
                this.userEntity = new UserEntity((UserModel) object);
            }
        }

    }

    public TokenModel toModel() {
        TokenModel TokenModel = new TokenModel();
        TokenModel.setId(id);
        TokenModel.setExpriedTime(expriedTime);
        TokenModel.setKey(key);
//        if (shapeEntityA != null) UserModel.setShapeByShapeId(shapeEntityA.toModel());
        return TokenModel;
    }
}
