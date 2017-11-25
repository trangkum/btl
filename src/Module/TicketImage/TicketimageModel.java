package Module.TicketImage;

import Module.File.FileModel;
import Module.Ticket.TicketModel;

import javax.persistence.*;

@Entity
@Table(name = "ticketimage", schema = "btl", catalog = "")
@IdClass(TicketimageModelPK.class)
public class TicketimageModel {
    private int ticketId;
    private int fielId;
    private TicketModel ticketByTicketId;
    private FileModel fileByFielId;

    @Id
    @Column(name = "ticketId", nullable = false)
    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    @Id
    @Column(name = "fielId", nullable = false)
    public int getFielId() {
        return fielId;
    }

    public void setFielId(int fielId) {
        this.fielId = fielId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TicketimageModel that = (TicketimageModel) o;

        if (ticketId != that.ticketId) return false;
        if (fielId != that.fielId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ticketId;
        result = 31 * result + fielId;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "ticketId", referencedColumnName = "id", nullable = false)
    public TicketModel getTicketByTicketId() {
        return ticketByTicketId;
    }

    public void setTicketByTicketId(TicketModel ticketByTicketId) {
        this.ticketByTicketId = ticketByTicketId;
    }

    @ManyToOne
    @JoinColumn(name = "fielId", referencedColumnName = "id", nullable = false)
    public FileModel getFileByFielId() {
        return fileByFielId;
    }

    public void setFileByFielId(FileModel fileByFielId) {
        this.fileByFielId = fileByFielId;
    }
}
