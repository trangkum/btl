package Module.TicketImage;

import Module.File.FileEntity;
import Module.File.FileModel;
import Module.Ticket.TicketEntity;
import Module.Ticket.TicketModel;

import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class TicketImageEntity implements Serializable {
    public int ticketId;
    public int fileId;
    public TicketEntity ticketEntity;
    public FileEntity fileEntity;

    public TicketImageEntity() {
    }

    public TicketImageEntity(TicketimageModel TicketattributeModel, Object... objects) {
        this.ticketId = TicketattributeModel.getTicketId();
        this.fileId = TicketattributeModel.getFileId();
        for (Object object : objects) {
            if (object instanceof TicketModel) {
                this.ticketEntity = new TicketEntity((TicketModel) object);
            } else  if (object instanceof FileModel) {
                this.fileEntity = new FileEntity((FileModel) object);
            }
        }
    }

    public TicketimageModel toModel() {
        TicketimageModel TicketimageModel = new TicketimageModel();
        TicketimageModel.setFileId(fileId);
        TicketimageModel.setTicketId(ticketId);
        return TicketimageModel;
    }
}
