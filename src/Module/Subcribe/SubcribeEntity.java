package Module.Subcribe;

import Module.Permission.PermissionEntity;
import Module.Permission.PermissionModel;
import Module.Subcribe.SubcribeModel;
import Module.User.TokenEntity;
import Module.User.TokenModel;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class SubcribeEntity implements Serializable {
    public Integer id;
    public String endpoint;
    public String tokenKey;
    public Integer tokenId;
    public TokenEntity tokenEntity;


    public SubcribeEntity() {
    }

    public SubcribeEntity(SubcribeModel SubcribeModel, Object... objects) {
        this.id = SubcribeModel.getId();
        this.endpoint = SubcribeModel.getEndpoint();
        this.tokenKey = SubcribeModel.getTokenKey();
        this.tokenId = SubcribeModel.getTokenId();
        for (Object object : objects) {
            if (object instanceof TokenModel) {
                this.tokenEntity = new TokenEntity((TokenModel) object);
            }
        }
    }

    public SubcribeModel toModel() {
        SubcribeModel SubcribeModel = new SubcribeModel();
        SubcribeModel.setId(id);
        SubcribeModel.setEndpoint(endpoint);
        SubcribeModel.setTokenKey(tokenKey);
        SubcribeModel.setTokenId(tokenId);
        return SubcribeModel;
    }
}
