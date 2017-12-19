import {EmployeeEntity} from "../Employee/Employee.Entity";
import {LocationEntity} from "../Location/Location.Entity";

export class TeamEntity {

    id: number;
    name: string;
    locationId: number;
    leaderEmployeeId : number;
    employeeEntities: EmployeeEntity[];
    locationEntity: LocationEntity;
    leaderEmployeeEntity: EmployeeEntity;

    IsEdit: boolean;
    IsActive: boolean = false;
    IsSelected: boolean = false;
    constructor(data: any = null) {
        if (data == null) {
            this.id = null;
            this.name = null;
            this.locationId = null;
            this.leaderEmployeeId = null;
            this.leaderEmployeeEntity = new EmployeeEntity();
            this.locationEntity = new LocationEntity();
            this.employeeEntities = [];
        } else {
            this.id = data.id;
            this.name = data.name;
            this.locationId = data.locationId;
            this.leaderEmployeeId = data.leaderEmployeeId;
            if (data.employeeEntities != null) {
                this.employeeEntities = [];
                for (let item of data.employeeEntities) {
                    this.employeeEntities.push(new EmployeeEntity(item));
                }
            } else this.employeeEntities = [];

            if (this.locationEntity == null) this.locationEntity = new LocationEntity();
            if (this.leaderEmployeeEntity == null) this.leaderEmployeeEntity = new EmployeeEntity();
            if (this.employeeEntities == null) this.employeeEntities = [];
        }
        this.IsEdit = false;
    }
}