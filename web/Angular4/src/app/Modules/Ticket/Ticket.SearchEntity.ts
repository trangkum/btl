import {FilterEntity} from "../../Shared/Filter.Entity";

export class SearchTicketEntity extends FilterEntity {
    content: string;
    subject: string;
    createEmployeeId: number;
    status: number;
    priority: number;
    deadline: string;
    assignedEmployeeId: number;
    rating: number;
    locationId: number;
    resolvedTime: string;
    closedTime: string;
    createdTime: string;
    updatedTime: string;
    deletedTime: string;
    createEmployeeName: string;
    assignedEmployeeName: string;

    constructor(ticket: any = null) {
        super(ticket);
        this.content = ticket == null ? null : ticket.content;
        this.subject = ticket == null ? null : ticket.subject;
        this.createEmployeeId = ticket == null ? null : ticket.createEmployeeId;
        this.status = ticket == null ? null : ticket.status;
        this.priority = ticket == null ? null : ticket.priority;
        this.deadline = ticket == null ? null : ticket.deadline;
        this.assignedEmployeeId = ticket == null ? null : ticket.assignedEmployeeId;
        this.rating = ticket == null ? null : ticket.rating;
        this.locationId = ticket == null ? null : ticket.locationId;
        this.resolvedTime = ticket == null ? null : ticket.resolvedTime;
        this.closedTime = ticket == null ? null : ticket.closedTime;
        this.createdTime = ticket == null ? null : ticket.createdTime;
        this.updatedTime = ticket == null ? null : ticket.updatedTime;
        this.deletedTime = ticket == null ? null : ticket.deletedTime;
        this.createEmployeeName = ticket == null ? null : ticket.createEmployeeName;
        this.assignedEmployeeName = ticket == null ? null : ticket.assignedEmployeeName;
    }
}