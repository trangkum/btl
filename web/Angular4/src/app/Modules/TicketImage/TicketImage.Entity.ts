import {TicketEntity} from "../Ticket/Ticket.Entity";
import {FileEntity} from "../File/File.Entity";

export class TicketImageEntity {

    ticketId: number;
    fielId: number;
    ticketEntity: TicketEntity;
    fileEntity: FileEntity;


    IsEdit: boolean;
    IsActive: boolean = false;
    IsSelected: boolean = false;

    constructor(data: any = null) {
        if (data == null) {
            this.ticketId = null;
            this.fielId = null;
            this.ticketEntity = new TicketEntity();
            this.fileEntity = new FileEntity();
        } else {
            this.ticketId = data.ticketId;
            this.fielId = data.fielId;
            this.ticketEntity = data.ticketEntity;
            this.fileEntity = data.fileEntity;
            if (this.ticketEntity == null) this.ticketEntity = new TicketEntity();
            if (this.fileEntity == null) this.fileEntity = new FileEntity();
        }
        this.IsEdit = false;
    }
}