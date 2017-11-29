package Module.TicketImage;


import Module.File.FileModel;
import Module.Ticket.TicketModel;

import javax.persistence.*;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Entity
@Table(name = "ticketimage", schema = "btl", catalog = "")
@IdClass(TicketimageModelPK.class)
public class TicketimageModel {
    private Integer ticketId;
    private Integer fielId;
    private TicketModel ticketByTicketId;
    private FileModel fileByFielId;

    @Id
    @Column(name = "ticketId", nullable = false)
    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    @Id
    @Column(name = "fielId", nullable = false)
    public Integer getFielId() {
        return fielId;
    }

    public void setFielId(Integer fielId) {
        this.fielId = fielId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TicketimageModel that = (TicketimageModel) o;

        if (ticketId != null ? !ticketId.equals(that.ticketId) : that.ticketId != null) return false;
        if (fielId != null ? !fielId.equals(that.fielId) : that.fielId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ticketId != null ? ticketId.hashCode() : 0;
        result = 31 * result + (fielId != null ? fielId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "ticketId", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public TicketModel getTicketByTicketId() {
        return ticketByTicketId;
    }

    public void setTicketByTicketId(TicketModel ticketByTicketId) {
        this.ticketByTicketId = ticketByTicketId;
    }

    @ManyToOne
    @JoinColumn(name = "fielId", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public FileModel getFileByFielId() {
        return fileByFielId;
    }

    public void setFileByFielId(FileModel fileByFielId) {
        this.fileByFielId = fileByFielId;
    }
}

@StaticMetamodel(TicketimageModel.class)
class TicketimageModel_ {
    public static volatile SingularAttribute<TicketimageModel, Integer> ticketId;
    public static volatile SingularAttribute<TicketimageModel, Integer> fielId;
}