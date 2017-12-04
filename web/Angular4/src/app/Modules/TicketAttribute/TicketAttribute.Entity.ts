import {TicketEntity} from "../Ticket/Ticket.Entity";

export class TicketAttributeEntity {

    id: number;
    status: string;
    priority: string;
    rating: string;
    reopened : string;
    ticketId : number;
    ticketEntity : TicketEntity;


    IsEdit: boolean;
    IsActive: boolean = false;
    IsSelected: boolean = false;
    
    constructor(data: any = null) {
        if (data == null) {
            this.id = null;
            this.status = null;
            this.priority = null;
            this.rating = null;
            this.reopened = null;
            this.ticketId = null;
            this.ticketEntity = new TicketEntity();
        } else {
            this.id = data.id;
            this.status = data.status;
            this.priority = data.priority;
            this.rating = data.rating;
            this.reopened = data.reopened;
            this.ticketId = data.ticketId;
            this.ticketEntity = new TicketEntity();

            if (this.ticketEntity == null) this.ticketEntity = new TicketEntity();
        }
        this.IsEdit = false;
    }
}