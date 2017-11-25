package Module.TicketRelater;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class TicketrelaterModelPK implements Serializable {
    private int employeeId;
    private int ticketId;

    @Column(name = "employeeId", nullable = false)
    @Id
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    @Column(name = "ticketId", nullable = false)
    @Id
    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TicketrelaterModelPK that = (TicketrelaterModelPK) o;

        if (employeeId != that.employeeId) return false;
        if (ticketId != that.ticketId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = employeeId;
        result = 31 * result + ticketId;
        return result;
    }
}
