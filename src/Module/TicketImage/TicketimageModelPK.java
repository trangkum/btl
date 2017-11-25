package Module.TicketImage;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class TicketimageModelPK implements Serializable {
    private int ticketId;
    private int fielId;

    @Column(name = "ticketId", nullable = false)
    @Id
    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    @Column(name = "fielId", nullable = false)
    @Id
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

        TicketimageModelPK that = (TicketimageModelPK) o;

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
}
