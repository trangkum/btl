package Module.TicketRead;

import Module.Employee.EmployeeEntity;
import Module.Employee.EmployeeModel;
import Module.Ticket.TicketEntity;
import Module.Ticket.TicketModel;

import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class TicketReadEntity implements Serializable {
    public Integer ticketId;
    public Integer employeeId;
    public Integer status;
    public TicketEntity ticketEntity;
    public EmployeeEntity employeeEntity;


    public TicketReadEntity() {
    }

    public TicketReadEntity(TicketreadModel TicketreadModel, Object... objects) {
        this.ticketId = TicketreadModel.getTicketId();
        this.employeeId = TicketreadModel.getEmployeeId();
        this.status = TicketreadModel.getStatus();
        for (Object object : objects) {
            if (object instanceof TicketModel) {
                this.ticketEntity = new TicketEntity((TicketModel) object);
            }else  if (object instanceof EmployeeModel) {
                this.employeeEntity = new EmployeeEntity((EmployeeModel) object);
            }
        }
    }

    public TicketreadModel toModel() {
        TicketreadModel TicketreadModel = new TicketreadModel();
        TicketreadModel.setEmployeeId(employeeId);
        TicketreadModel.setStatus(status);
        TicketreadModel.setTicketId(ticketId);
        return TicketreadModel;
    }
}
