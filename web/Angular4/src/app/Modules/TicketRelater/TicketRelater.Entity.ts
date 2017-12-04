import {EmployeeEntity} from "../Employee/Employee.Entity";
import {TicketEntity} from "../Ticket/Ticket.Entity";

export class TicketRelaterEntity {

    employeeId: number;
    ticketId: number;
    employeeEntity : EmployeeEntity;
    ticketEntity: TicketEntity;

    IsEdit: boolean;
    IsActive: boolean = false;
    IsSelected: boolean = false;

    constructor(data: any = null) {
        if (data == null) {
            this.employeeId = null;
            this.ticketId = null;
            this.employeeEntity = new EmployeeEntity();
            this.ticketEntity = new TicketEntity();
        } else {
            this.employeeId = data.employeeId;
            this.ticketId = data.ticketId;
            this.employeeEntity = data.employeeEntity;
            this.ticketEntity = data.ticketEntity;
            if (this.employeeEntity == null) this.employeeEntity = new EmployeeEntity();
            if (this.ticketEntity == null) this.ticketEntity = new TicketEntity();
        }
        this.IsEdit = false;
    }
}