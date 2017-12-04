import {RoleEntity} from "../Role/Role.Entity";
import {RouteEntity} from "../Route/Route.Entity";

export class PermissionEntity {

    id: number;
    roleId: number;
    routeId: number;
    roleEntity: RoleEntity;
    routeEntity : RouteEntity;
    IsEdit: boolean;
    IsActive: boolean = false;
    IsSelected: boolean = false;
    
    constructor(data: any = null) {
        if (data == null) {
            this.id = null;
            this.roleId = null;
            this.routeId = null;
            this.roleEntity = new RoleEntity();
            this.routeEntity = new RouteEntity();
        } else {
            this.id = data.id;
            this.roleId = data.roleId;
            this.routeId = data.routeId;
            if (this.roleEntity == null) this.roleEntity = new RoleEntity();
            if (this.routeEntity == null) this.routeEntity = new RouteEntity();
        }
        this.IsEdit = false;
    }
}