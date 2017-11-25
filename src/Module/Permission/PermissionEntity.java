package Module.Permission;

import Module.Role.RoleEntity;
import Module.Role.RoleModel;
import Module.Route.RouteEntity;
import Module.Route.RouteModel;

import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class PermissionEntity implements Serializable {
    public int id;
    public Integer roleId;
    public Integer routeId;
    public RoleEntity roleEntity;
    public RouteEntity routeEntity;


    public PermissionEntity() {
    }

    public PermissionEntity(PermissionModel PermissionModel, Object... objects) {
        this.id = PermissionModel.getId();
        this.roleId = PermissionModel.getRoleId();
        this.routeId = PermissionModel.getRouteId();
        for (Object object : objects) {
            if (object instanceof RoleModel) {
                this.roleEntity = new RoleEntity((RoleModel) object);
            } else if (object instanceof RouteModel) {
                this.routeEntity = new RouteEntity((RouteModel) object);
            }
        }
    }

    public PermissionModel toModel() {
        PermissionModel PermissionModel = new PermissionModel();
        PermissionModel.setId(id);
        PermissionModel.setRoleId(roleId);
        PermissionModel.setRouteId(routeId);
        return PermissionModel;
    }
}
