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
    private Integer fileId;
    private TicketModel ticketByTicketId;
    private FileModel fileByFileId;

    @Id
    @Column(name = "ticketId", nullable = false)
    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    @Id
    @Column(name = "fileId", nullable = false)
    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TicketimageModel that = (TicketimageModel) o;

        if (ticketId != null ? !ticketId.equals(that.ticketId) : that.ticketId != null) return false;
        if (fileId != null ? !fileId.equals(that.fileId) : that.fileId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ticketId != null ? ticketId.hashCode() : 0;
        result = 31 * result + (fileId != null ? fileId.hashCode() : 0);
        return result;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticketId", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public TicketModel getTicketByTicketId() {
        return ticketByTicketId;
    }

    public void setTicketByTicketId(TicketModel ticketByTicketId) {
        this.ticketByTicketId = ticketByTicketId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fileId", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public FileModel getFileByFileId() {
        return fileByFileId;
    }

    public void setFileByFileId(FileModel fileByFileId) {
        this.fileByFileId = fileByFileId;
    }
}

@StaticMetamodel(TicketimageModel.class)
class TicketimageModel_ {
    public static volatile SingularAttribute<TicketimageModel, Integer> ticketId;
    public static volatile SingularAttribute<TicketimageModel, Integer> fileId;
}