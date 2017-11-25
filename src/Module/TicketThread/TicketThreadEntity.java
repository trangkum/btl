package Module.TicketThread;

import Module.Employee.EmployeeEntity;
import Module.Employee.EmployeeModel;
import Module.Ticket.TicketEntity;
import Module.Ticket.TicketModel;

import java.io.Serializable;
import java.sql.Timestamp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class TicketThreadEntity implements Serializable {
    public int id;
    public int ticketId;
    public int employeeId;
    public String content;
    public Byte type;
    public Timestamp createTime;
    public Timestamp updateTime;
    public TicketEntity ticketEntity;
    public EmployeeEntity employeeEntity;



    public TicketThreadEntity() {
    }

    public TicketThreadEntity(TicketthreadModel TicketattributeModel, Object... objects) {
        this.id = TicketattributeModel.getId();
        this.ticketId = TicketattributeModel.getTicketId();
        this.employeeId = TicketattributeModel.getEmployeeId();
        this.content = TicketattributeModel.getContent();
        this.type = TicketattributeModel.getType();
        this.createTime = TicketattributeModel.getCreateTime();
        this.updateTime = TicketattributeModel.getUpdateTime();
        for (Object object : objects) {
            if (object instanceof TicketModel) {
                this.ticketEntity = new TicketEntity((TicketModel) object);
            }else if (object instanceof EmployeeModel) {
                this.employeeEntity = new EmployeeEntity((EmployeeModel) object);
            }
        }
    }

    public TicketthreadModel toModel() {
        TicketthreadModel ticketthreadModel = new TicketthreadModel();
        ticketthreadModel.setId(id);
        ticketthreadModel.setTicketId(ticketId);
        ticketthreadModel.setEmployeeId(employeeId);
        ticketthreadModel.setContent(content);
        ticketthreadModel.setType(type);
        ticketthreadModel.setCreateTime(createTime);
        ticketthreadModel.setUpdateTime(updateTime);
        return ticketthreadModel;
    }
}
