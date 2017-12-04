import {PermissionEntity} from "../Permission/Permission.Entity";

export class RouteEntity {

    id: number;
    route: string;
    name: string;
    groupId : number;
    method : string;
    permissionEntities : PermissionEntity[];


    IsEdit: boolean;
    IsActive: boolean = false;
    IsSelected: boolean = false;
    constructor(data: any = null) {
        if (data == null) {
            this.id = null;
            this.route = null;
            this.name = null;
            this.groupId = null;
            this.method = null;
            this.permissionEntities = [];
        } else {
            this.id = data.id;
            this.route = data.route;
            this.name = data.name;
            this.groupId = data.groupId;
            this.method = data.method;
            if (data.permissionEntities != null) {
                this.permissionEntities = [];
                for (let item of data.permissionEntities) {
                    this.permissionEntities.push(new PermissionEntity(item));
                }
            } else this.permissionEntities = [];
            if (this.permissionEntities == null) this.permissionEntities = [];
        }
        this.IsEdit = false;
    }
}