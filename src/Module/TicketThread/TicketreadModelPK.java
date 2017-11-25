package Module.TicketThread;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class TicketreadModelPK implements Serializable {
    private int ticketId;
    private int employeeId;

    @Column(name = "ticketId", nullable = false)
    @Id
    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    @Column(name = "employeeId", nullable = false)
    @Id
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TicketreadModelPK that = (TicketreadModelPK) o;

        if (ticketId != that.ticketId) return false;
        if (employeeId != that.employeeId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ticketId;
        result = 31 * result + employeeId;
        return result;
    }
}
