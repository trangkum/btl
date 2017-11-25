package Module.Role;

import Module.Permission.PermissionEntity;
import Module.Permission.PermissionModel;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class RoleEntity implements Serializable {
    public int id;
    public String name;
    public String description;
    public List<PermissionEntity> permissionEntities;

    public RoleEntity() {
    }

    public RoleEntity(RoleModel RoleModel, Object... objects) {
        this.id = RoleModel.getId();
        this.name = RoleModel.getName();
        this.description = RoleModel.getDescription();
        for (Object object : objects) {
            if (object instanceof Collection) {
                for (Object o : (Collection<Object>) object) {
                    if (o instanceof PermissionModel) {
                        this.permissionEntities = ((Collection<PermissionModel>) object).parallelStream().map(PermissionEntity::new).collect(Collectors.toList());
                        break;
                    }
                }
            }
        }
    }

    public RoleModel toModel() {
        RoleModel RoleModel = new RoleModel();
        RoleModel.setId(id);
        RoleModel.setDescription(description);
        RoleModel.setName(name);
        return RoleModel;
    }
}
