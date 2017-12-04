import {TicketEntity} from "../Ticket/Ticket.Entity";
import {EmployeeEntity} from "../Employee/Employee.Entity";

export class TicketReadEntity {

    ticketId: number;
    employeeId: number;
    status : number;
    ticketEntity : TicketEntity;
    employeeEntity: EmployeeEntity;


    IsEdit: boolean;
    IsActive: boolean = false;
    IsSelected: boolean = false;
    constructor(data: any = null) {
        if (data == null) {
            this.ticketId = null;
            this.employeeId = null;
            this.status = null;
            this.ticketEntity = new TicketEntity();
            this.employeeEntity = new EmployeeEntity();
        } else {
            this.ticketId = data.ticketId;
            this.employeeId = data.employeeId;
            this.status = data.status;
            this.ticketEntity = data.ticketEntity;
            this.employeeEntity = data.employeeEntity;
            if (this.ticketEntity == null) this.ticketEntity = new TicketEntity();
            if (this.employeeEntity == null) this.employeeEntity = new EmployeeEntity();
        }
        this.IsEdit = false;
    }
}