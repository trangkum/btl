import {TeamEntity} from "../Team/Team.Entity";
import {LocationEntity} from "../Location/Location.Entity";
import {FileEntity} from "../File/File.Entity";
import {TicketEntity} from "../Ticket/Ticket.Entity";
import {TicketReadEntity} from "../TicketRead/TicketRead.Entity";
import {TicketRelaterEntity} from "../TicketRelater/TicketRelater.Entity";
import {TicketThreadEntity} from "../TicketThread/TicketThread.Entity";
import {UserEntity} from "../User/User.Entity";

export class EmployeeEntity {

    id: number;
    email: string;
    name: string;
    teamId: number;
    locationId : number;
    briefName : string;
    teamEntity : TeamEntity;
    locationEntity : LocationEntity;
    fileEntities : FileEntity[];
    locationEntities : LocationEntity[];
    teamEntities : TeamEntity[];
    createTicketEntities : TicketEntity[];
    assignTicketEntities : TicketEntity[];
    ticketReadEntities : TicketReadEntity[];
    ticketRelaterEntities : TicketRelaterEntity[];
    ticketThreadEntities : TicketThreadEntity[];
    userEntities : UserEntity[];
    IsEdit: boolean;
    IsActive: boolean = false;
    IsSelected: boolean = false;

    constructor(data: any = null) {
        if (data == null) {
            this.id = null;
            this.email = null;
            this.name = null;
            this.teamId = null;
            this.locationId = null;
            this.briefName = null;
            this.teamEntity = null;
            this.locationEntity = null;
            this.fileEntities = [];
            this.locationEntities = [];
            this.teamEntities = [];
            this.createTicketEntities = [];
            this.assignTicketEntities = [];
            this.ticketReadEntities = [];
            this.ticketRelaterEntities = [];
            this.ticketThreadEntities = [];
            this.userEntities = [];
        } else {
            this.id = data.id;
            this.email = data.email;
            this.name = data.name;
            this.teamId = data.teamId;
            this.locationId = data.locationId;
            this.briefName = data.briefName;
            this.teamEntity = data.teamEntity;
            this.locationEntity = data.locationEntity;
            if (data.fileEntities != null) {
                this.fileEntities = [];
                for (let item of data.fileEntities) {
                    this.fileEntities.push(new FileEntity(item));
                }
            } else data.fileEntities = [];
            if (data.locationEntities != null) {
                this.locationEntities = [];
                for (let item of data.locationEntities) {
                    this.locationEntities.push(new LocationEntity(item));
                }
            } else data.locationEntities = [];
            if (data.teamEntities != null) {
                this.teamEntities = [];
                for (let item of data.teamEntities) {
                    this.teamEntities.push(new TeamEntity(item));
                }
            } else data.teamEntities = [];
            if (data.createTicketEntities != null) {
                this.createTicketEntities = [];
                for (let item of data.createTicketEntities) {
                    this.createTicketEntities.push(new TicketEntity(item));
                }
            } else data.createTicketEntities = [];
            if (data.assignTicketEntities != null) {
                this.assignTicketEntities = [];
                for (let item of data.assignTicketEntities) {
                    this.assignTicketEntities.push(new TicketEntity(item));
                }
            } else data.assignTicketEntities = [];
            if (data.ticketReadEntities != null) {
                this.ticketReadEntities = [];
                for (let item of data.ticketReadEntities) {
                    this.ticketReadEntities.push(new TicketReadEntity(item));
                }
            } else data.ticketReadEntities = [];
            if (data.ticketRelaterEntities != null) {
                this.ticketRelaterEntities = [];
                for (let item of data.ticketRelaterEntities) {
                    this.ticketRelaterEntities.push(new TicketRelaterEntity(item));
                }
            } else data.ticketRelaterEntities = [];
            if (data.ticketThreadEntities != null) {
                this.ticketThreadEntities = [];
                for (let item of data.ticketThreadEntities) {
                    this.ticketThreadEntities.push(new TicketThreadEntity(item));
                }
            } else data.ticketThreadEntities = [];
            if (data.userEntities != null) {
                this.userEntities = [];
                for (let item of data.userEntities) {
                    this.userEntities.push(new UserEntity(item));
                }
            } else data.userEntities = [];
            if (this.teamEntity == null) this.teamEntity = new TeamEntity();
            if (this.locationEntity == null) this.locationEntity = new LocationEntity();
        }
        this.IsEdit = false;
    }
}