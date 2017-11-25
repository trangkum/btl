package Module.Ticket;

import Module.Employee.EmployeeEntity;
import Module.Employee.EmployeeModel;
import Module.Group.GroupEntity;
import Module.Group.GroupModel;
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
import java.sql.Timestamp;
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
    public int createEmployeeId;
    public int status;
    public int priority;
    public Timestamp deadline;
    public Integer assignedEmployeeId;
    public Byte rating;
    public int groupId;
    public Timestamp resolvedTime;
    public Timestamp closedTime;
    public Timestamp createdTime;
    public Timestamp updatedTime;
    public Timestamp deletedTime;
    public EmployeeEntity createEmployeeEntity;
    public EmployeeEntity assignedEmployeeEntity;
    public GroupEntity groupEntity;
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
        this.deadline = ticketModel.getDeadline();
        this.assignedEmployeeId = ticketModel.getAssignedEmployeeId();
        this.rating = ticketModel.getRating();
        this.groupId = ticketModel.getGroupId();
        this.resolvedTime = ticketModel.getResolvedTime();
        this.closedTime = ticketModel.getClosedTime();
        this.createdTime = ticketModel.getCreatedTime();
        this.updatedTime = ticketModel.getUpdatedTime();
        this.deletedTime = ticketModel.getDeletedTime();
        for (Object object : objects) {
            if (object instanceof EmployeeModel) {
                this.assignedEmployeeEntity = new EmployeeEntity((EmployeeModel) object);
            } else if (object instanceof GroupModel) {
                this.groupEntity = new GroupEntity((GroupModel) object);
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
        ticketModel.setContent(content);
        ticketModel.setSubject(subject);
        ticketModel.setCreateEmployeeId(createEmployeeId);
        ticketModel.setStatus(status);
        ticketModel.setPriority(priority);
        ticketModel.setDeadline(deadline);
        ticketModel.setAssignedEmployeeId(assignedEmployeeId);
        ticketModel.setRating(rating);
        ticketModel.setGroupId(groupId);
        ticketModel.setResolvedTime(resolvedTime);
        ticketModel.setClosedTime(closedTime);
        ticketModel.setCreatedTime(createdTime);
        ticketModel.setUpdatedTime(updatedTime);
        ticketModel.setDeletedTime(deletedTime);
        return ticketModel;
    }
}
