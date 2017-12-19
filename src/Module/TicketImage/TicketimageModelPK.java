package Module.TicketImage;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class TicketimageModelPK implements Serializable {
    private int ticketId;
    private int fileId;

    @Column(name = "ticketId", nullable = false)
    @Id
    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    @Column(name = "fileId", nullable = false)
    @Id
    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TicketimageModelPK that = (TicketimageModelPK) o;

        if (ticketId != that.ticketId) return false;
        if (fileId != that.fileId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ticketId;
        result = 31 * result + fileId;
        return result;
    }
}
