import {PermissionEntity} from "../Permission/Permission.Entity";

export class RoleEntity {

    id: number;
    name: string;
    description : string;
    permissionEntities : PermissionEntity[];


    IsEdit: boolean;
    IsActive: boolean = false;
    IsSelected: boolean = false;

    constructor(data: any = null) {
        if (data == null) {
            this.id = null;
            this.name = null;
            this.description = null;
            this.permissionEntities = [];
        } else {
            this.id = data.id;
            this.name = data.name;
            this.description = data.description;
            if (data.permissionEntities != null) {
                this.permissionEntities = [];
                for (let item of data.permissionEntities) {
                    this.permissionEntities.push(new PermissionEntity(item));
                }
            }
            if (this.permissionEntities == null) this.permissionEntities = [];
        }
        this.IsEdit = false;
    }
}