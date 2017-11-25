package Module.TicketRelater;

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
public class TicketRelaterEntity implements Serializable {
    public int employeeId;
    public int ticketId;
    public EmployeeEntity employeeEntity;
    public TicketEntity ticketEntity;

    public TicketRelaterEntity() {
    }

    public TicketRelaterEntity(TicketrelaterModel TicketattributeModel, Object... objects) {
        this.employeeId = TicketattributeModel.getEmployeeId();
        this.ticketId = TicketattributeModel.getTicketId();
        for (Object object : objects) {
            if (object instanceof EmployeeModel) {
                this.employeeEntity = new EmployeeEntity((EmployeeModel) object);
            } else if (object instanceof TicketModel) {
                this.ticketEntity = new TicketEntity((TicketModel) object);
            }
        }
    }

    public TicketrelaterModel toModel() {
        TicketrelaterModel TicketrelaterModel = new TicketrelaterModel();
        TicketrelaterModel.setEmployeeId(employeeId);
        TicketrelaterModel.setTicketId(ticketId);
        return TicketrelaterModel;
    }
}
