import {EmployeeEntity} from "../Employee/Employee.Entity";
import {TeamEntity} from "../Team/Team.Entity";
import {TicketEntity} from "../Ticket/Ticket.Entity";

export class GroupEntity {

    id: number;
    name: string;
    managerEmloyeeId: number;
    managerEmployeeEntity : EmployeeEntity;
    employeeEntities: EmployeeEntity[];
    teamEntities: TeamEntity[];
    ticketEntities: TicketEntity[];

    IsEdit: boolean;
    IsActive: boolean = false;
    IsSelected: boolean = false;
    
    constructor(data: any = null) {
        if (data == null) {
            this.id = null;
            this.name = null;
            this.managerEmloyeeId = null;
            this.managerEmployeeEntity = new EmployeeEntity();
            this.employeeEntities = [];
            this.teamEntities = [];
            this.ticketEntities = [];
        } else {
            this.id = data.id;
            this.name = data.name;
            this.managerEmloyeeId = data.managerEmloyeeId;
            if (data.employeeEntities != null) {
                this.employeeEntities = [];
                for (let item of data.employeeEntities) {
                    this.employeeEntities.push(new EmployeeEntity(item));
                }
            }
            if (data.teamEntities != null) {
                this.teamEntities = [];
                for (let item of data.teamEntities) {
                    this.teamEntities.push(new TeamEntity(item));
                }
            }
            if (data.ticketEntities != null) {
                this.ticketEntities = [];
                for (let item of data.ticketEntities) {
                    this.ticketEntities.push(new TicketEntity(item));
                }
            }
            if (this.managerEmployeeEntity == null) this.managerEmployeeEntity = new EmployeeEntity();
            if (this.teamEntities == null) this.teamEntities = [];
            if (this.employeeEntities == null) this.employeeEntities = [];
            if (this.ticketEntities == null) this.ticketEntities = [];
        }
        this.IsEdit = false;
    }
}