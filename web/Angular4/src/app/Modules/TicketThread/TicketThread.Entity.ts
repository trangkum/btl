import {TicketEntity} from "../Ticket/Ticket.Entity";
import {EmployeeEntity} from "../Employee/Employee.Entity";

export class TicketThreadEntity {

    id: number;
    ticketId: number;
    employeeId: number;
    content: string;
    type : number;
    createTime: string;
    updateTime: string;
    ticketEntity : TicketEntity;
    employeeEntity : EmployeeEntity;
    IsEdit: boolean;
    IsActive: boolean = false;
    IsSelected: boolean = false;
    
    constructor(data: any = null) {
        if (data == null) {
            this.id = null;
            this.ticketId = null;
            this.employeeId = null;
            this.content = null;
            this.type = null;
            this.createTime = null;
            this.updateTime = null;
            this.ticketEntity = new TicketEntity();
            this.employeeEntity = new EmployeeEntity();
        } else {
            this.id = data.id;
            this.ticketId = data.ticketId;
            this.employeeId = data.employeeId;
            this.content = data.content;
            this.type = data.type;
            this.createTime = data.createTime;
            this.updateTime = data.updateTime;
            this.ticketEntity = data.ticketEntity;
            this.employeeEntity = data.employeeEntity;
            if (this.ticketEntity == null) this.ticketEntity = new TicketEntity();
            if (this.employeeEntity == null) this.employeeEntity = new EmployeeEntity();
        }
        this.IsEdit = false;
    }
}