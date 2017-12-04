import {EmployeeEntity} from "../Employee/Employee.Entity";
import {TicketImageEntity} from "../TicketImage/TicketImage.Entity";

export class FileEntity {

    id: number;
    data: string;
    length: number;
    name: string;
    employeeId: number;
    createTime: number;
    extension: string;

    employeeEntity: EmployeeEntity;
    ticketImageEntities: TicketImageEntity[];
    IsEdit: boolean;
    IsActive: boolean = false;
    IsSelected: boolean = false;

    constructor(file: any = null) {
        if (file == null) {
            this.id = null;
            this.data = null;
            this.length = null;
            this.name = null;
            this.employeeId = null;
            this.createTime = null;
            this.extension = null;

            this.employeeEntity = new EmployeeEntity();
            this.ticketImageEntities = [];
        } else {
            this.id = file.id;
            this.data = file.data;
            this.length = file.length;
            this.name = file.name;
            this.employeeId = file.employeeId;
            this.createTime = file.createTime;
            this.extension = file.extension;
            if (file.ticketImageEntities != null) {
                this.ticketImageEntities = [];
                for (let item of file.edgeEntities) {
                    this.ticketImageEntities.push(new TicketImageEntity(item));
                }
            }
            if (this.employeeEntity == null) this.employeeEntity = new EmployeeEntity();
        }
        this.IsEdit = false;
    }
}