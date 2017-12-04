import {EmployeeEntity} from "../Employee/Employee.Entity";
import {GroupEntity} from "../Group/Group.Entity";

export class TeamEntity {

    id: number;
    name: string;
    groupId: number;
    leaderEmployeeId : number;
    employeeEntities: EmployeeEntity[];
    groupEntity: GroupEntity;
    leaderEmployeeEntity: EmployeeEntity;

    IsEdit: boolean;
    IsActive: boolean = false;
    IsSelected: boolean = false;
    constructor(data: any = null) {
        if (data == null) {
            this.id = null;
            this.name = null;
            this.groupId = null;
            this.leaderEmployeeId = null;
            this.leaderEmployeeEntity = new EmployeeEntity();
            this.groupEntity = new GroupEntity();
            this.employeeEntities = [];
        } else {
            this.id = data.id;
            this.name = data.name;
            this.groupId = data.groupId;
            this.leaderEmployeeId = data.leaderEmployeeId;
            if (data.employeeEntities != null) {
                this.employeeEntities = [];
                for (let item of data.employeeEntities) {
                    this.employeeEntities.push(new EmployeeEntity(item));
                }
            } else this.employeeEntities = [];

            if (this.groupEntity == null) this.groupEntity = new GroupEntity();
            if (this.leaderEmployeeEntity == null) this.leaderEmployeeEntity = new EmployeeEntity();
            if (this.employeeEntities == null) this.employeeEntities = [];
        }
        this.IsEdit = false;
    }
}