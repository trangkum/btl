package Module.TicketAttribute;

import Module.Ticket.TicketEntity;
import Module.Ticket.TicketModel;

import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class TicketAttributeEntity implements Serializable {
    public int id;
    public String status;
    public String priority;
    public String rating;
    public String reopened;
    public Integer ticketId;
    public TicketEntity ticketEntity;


    public TicketAttributeEntity() {
    }

    public TicketAttributeEntity(TicketattributeModel TicketattributeModel, Object... objects) {
        this.id = TicketattributeModel.getId();
        this.status = TicketattributeModel.getStatus();
        this.priority = TicketattributeModel.getPriority();
        this.rating = TicketattributeModel.getRating();
        this.reopened = TicketattributeModel.getReopened();
        this.ticketId = TicketattributeModel.getTicketId();
        for (Object object : objects) {
            if (object instanceof TicketModel) {
                this.ticketEntity = new TicketEntity((TicketModel) object);
            }
        }
    }

    public TicketattributeModel toModel() {
        TicketattributeModel ticketattributeModel = new TicketattributeModel();
        ticketattributeModel.setId(id);
        ticketattributeModel.setStatus(status);
        ticketattributeModel.setPriority(priority);
        ticketattributeModel.setRating(rating);
        ticketattributeModel.setReopened(reopened);
        ticketattributeModel.setTicketId(ticketId);
        return ticketattributeModel;
    }
}
