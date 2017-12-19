import {EmployeeEntity} from "../Employee/Employee.Entity";
import {LocationEntity} from "../Location/Location.Entity";
import {TicketAttributeEntity} from "../TicketAttribute/TicketAttribute.Entity";
import {TicketImageEntity} from "../TicketImage/TicketImage.Entity";
import {TicketReadEntity} from "../TicketRead/TicketRead.Entity";
import {TicketRelaterEntity} from "../TicketRelater/TicketRelater.Entity";
import {TicketThreadEntity} from "../TicketThread/TicketThread.Entity";

export class TicketEntity {
    id: number;
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
    createEmployeeEntity: EmployeeEntity;
    assignedEmployeeEntity: EmployeeEntity;
    locationEntity: LocationEntity;
    ticketAttributeEntities: TicketAttributeEntity[];
    ticketImageEntities: TicketImageEntity[];
    ticketReadEntities: TicketReadEntity[];
    ticketRelaterEntities: TicketRelaterEntity[];
    ticketThreadEntities: TicketThreadEntity[];
    IsEdit: boolean;
    IsActive: boolean = false;
    IsSelected: boolean = false;

    constructor(data: any = null) {
        if (data == null) {
            this.id = null;
            this.content = null;
            this.subject = null;
            this.createEmployeeId = null;
            this.status = null;
            this.priority = null;
            this.deadline = null;
            this.assignedEmployeeId = null;
            this.rating = null;
            this.locationId = null;
            this.resolvedTime = null;
            this.closedTime = null;
            this.createdTime = null;
            this.updatedTime = null;
            this.deletedTime = null;
            this.createEmployeeEntity = new EmployeeEntity();
            this.assignedEmployeeEntity = new EmployeeEntity();
            this.ticketAttributeEntities = [];
            this.ticketImageEntities = [];
            this.ticketReadEntities = [];
            this.ticketRelaterEntities = [];
            this.ticketThreadEntities = [];
        } else {
            this.id = data.id;
            this.content = data.content;
            this.subject = data.subject;
            this.createEmployeeId = data.createEmployeeId;
            this.status = data.status;
            this.priority = data.priority;
            this.deadline = data.deadline;
            this.assignedEmployeeId = data.assignedEmployeeId;
            this.rating = data.rating;
            this.locationId = data.locationId;
            this.resolvedTime = data.resolvedTime;
            this.closedTime = data.closedTime;
            this.createdTime = data.createdTime;
            this.updatedTime = data.updatedTime;
            this.deletedTime = data.deletedTime;
            this.createEmployeeEntity = data.createEmployeeEntity == null ? new EmployeeEntity() : data.createEmployeeEntity;
            this.assignedEmployeeEntity = data.assignedEmployeeEntity == null ? new EmployeeEntity() : data.assignedEmployeeEntity;
            this.locationEntity = data.locationEntity;
            ;
            if (data.ticketAttributeEntities != null) {
                this.ticketAttributeEntities = [];
                for (let item of data.ticketAttributeEntities) {
                    this.ticketAttributeEntities.push(new TicketAttributeEntity(item));
                }
            } else data.ticketAttributeEntities = [];
            if (data.ticketImageEntities != null) {
                this.ticketImageEntities = [];
                for (let item of data.ticketImageEntities) {
                    this.ticketImageEntities.push(new TicketImageEntity(item));
                }
            } else data.ticketImageEntities = [];
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
        }
        this.IsEdit = false;
    }
}