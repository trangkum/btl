package Module.Ticket;

import AppStart.RESTTimestamp;
import Module.Employee.EmployeeEntity;
import Module.Employee.EmployeeModel;
import Module.Location.LocationEntity;
import Module.Location.LocationModel;
import Module.TicketImage.TicketImageEntity;
import Module.TicketImage.TicketimageModel;
import Module.TicketRead.TicketReadEntity;
import Module.TicketRead.TicketreadModel;
import Module.TicketRelater.TicketRelaterEntity;
import Module.TicketRelater.TicketrelaterModel;
import Module.TicketThread.TicketThreadEntity;
import Module.TicketThread.TicketthreadModel;
import Module.TicketAttribute.TicketAttributeEntity;
import Module.TicketAttribute.TicketattributeModel;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class TicketEntity implements Serializable {
    public int id;
    public String content;
    public String subject;
    public Integer createEmployeeId;
    public Integer status;
    public Integer priority;
    public String deadline;
    public Integer assignedEmployeeId;
    public Byte rating;
    public Integer locationId;
    public String resolvedTime;
    public String closedTime;
    public String createdTime;
    public String updatedTime;
    public String deletedTime;
    public EmployeeEntity createEmployeeEntity;
    public EmployeeEntity assignedEmployeeEntity;
    public LocationEntity locationEntity;
    public List<TicketAttributeEntity> ticketAttributeEntities;
    public List<TicketImageEntity> ticketImageEntities;
    public List<TicketReadEntity> ticketReadEntities;
    public List<TicketRelaterEntity> ticketRelaterEntities;
    public List<TicketThreadEntity> ticketThreadEntities;

    public TicketEntity() {
    }

    public TicketEntity(TicketModel ticketModel, Object... objects) {
        this.id = ticketModel.getId();
        this.content = ticketModel.getContent();
        this.subject = ticketModel.getSubject();
        this.createEmployeeId = ticketModel.getCreateEmployeeId();
        this.status = ticketModel.getStatus();
        this.priority = ticketModel.getPriority();
        this.deadline = ticketModel.getDeadline().toString();
        this.assignedEmployeeId = ticketModel.getAssignedEmployeeId();
        this.rating = ticketModel.getRating();
        this.locationId = ticketModel.getLocationId();
        this.resolvedTime =  RESTTimestamp.toString(ticketModel.getResolvedTime());
        this.closedTime =RESTTimestamp.toString(ticketModel.getClosedTime());
        this.createdTime =  RESTTimestamp.toString(ticketModel.getCreatedTime());
        this.updatedTime =  RESTTimestamp.toString(ticketModel.getUpdatedTime());
        this.deletedTime = RESTTimestamp.toString(ticketModel.getDeletedTime());
        for (Object object : objects) {
            if (object instanceof EmployeeModel) {
                this.createEmployeeEntity = new EmployeeEntity((EmployeeModel) object);
            } else if (object instanceof LocationModel) {
                this.locationEntity = new LocationEntity((LocationModel) object);
            } else if (object instanceof Collection) {
                for (Object o : (Collection<Object>) object) {
                    if (o instanceof TicketattributeModel) {
                        this.ticketAttributeEntities = ((Collection<TicketattributeModel>) object).parallelStream().map(TicketAttributeEntity::new).collect(Collectors.toList());
                        break;
                    } else if (o instanceof TicketimageModel) {
                        this.ticketImageEntities = ((Collection<TicketimageModel>) object).parallelStream().map(TicketImageEntity::new).collect(Collectors.toList());
                        break;
                    } else if (o instanceof TicketreadModel) {
                        this.ticketReadEntities = ((Collection<TicketreadModel>) object).parallelStream().map(TicketReadEntity::new).collect(Collectors.toList());
                        break;
                    } else if (o instanceof TicketrelaterModel) {
                        this.ticketRelaterEntities = ((Collection<TicketrelaterModel>) object).parallelStream().map(TicketRelaterEntity::new).collect(Collectors.toList());
                        break;
                    } else if (o instanceof TicketthreadModel) {
                        this.ticketThreadEntities = ((Collection<TicketthreadModel>) object).parallelStream().map(TicketThreadEntity::new).collect(Collectors.toList());
                        break;
                    }
                }
            }
        }
    }

    public TicketModel toModel() {
        TicketModel ticketModel = new TicketModel();
        ticketModel.setId(id);
        try {
            ticketModel.setSubject(new String(subject.getBytes(), "UTF-8"));
            ticketModel.setContent(new String(content.getBytes(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ticketModel.setCreateEmployeeId(createEmployeeId);
        ticketModel.setStatus(status);
        ticketModel.setPriority(priority);
        ticketModel.setDeadline(RESTTimestamp.Parse(deadline));
        ticketModel.setAssignedEmployeeId(assignedEmployeeId);
        ticketModel.setRating(rating);
        ticketModel.setLocationId(locationId);
        ticketModel.setResolvedTime(RESTTimestamp.Parse(resolvedTime));
        ticketModel.setClosedTime(RESTTimestamp.Parse(closedTime));
        ticketModel.setCreatedTime(RESTTimestamp.Parse(createdTime));
        ticketModel.setUpdatedTime(RESTTimestamp.Parse(updatedTime));
        ticketModel.setDeletedTime(RESTTimestamp.Parse(deletedTime));
        return ticketModel;
    }

    public TicketModel toModel(List<TicketImageEntity> ticketImageEntities) {
        TicketModel ticketModel = toModel();
        if(ticketImageEntities != null){
            Collection<TicketimageModel> ticketimageModels = ticketImageEntities.parallelStream().map(TicketImageEntity::toModel).collect(Collectors.toList());
            ticketModel.setTicketimagesById(ticketimageModels);
        }
        return ticketModel;
    }
}
