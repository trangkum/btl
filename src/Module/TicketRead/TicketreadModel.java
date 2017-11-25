package Module.TicketRead;

import Module.Employee.EmployeeModel;
import Module.Ticket.TicketModel;
import Module.TicketThread.TicketreadModelPK;

import javax.persistence.*;

@Entity
@Table(name = "ticketread", schema = "btl", catalog = "")
@IdClass(TicketreadModelPK.class)
public class TicketreadModel {
    private int ticketId;
    private int employeeId;
    private byte status;
    private TicketModel ticketByTicketId;
    private EmployeeModel employeeByEmployeeId;

    @Id
    @Column(name = "ticketId", nullable = false)
    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    @Id
    @Column(name = "employeeId", nullable = false)
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    @Basic
    @Column(name = "status", nullable = false)
    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TicketreadModel that = (TicketreadModel) o;

        if (ticketId != that.ticketId) return false;
        if (employeeId != that.employeeId) return false;
        if (status != that.status) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ticketId;
        result = 31 * result + employeeId;
        result = 31 * result + (int) status;
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
    @JoinColumn(name = "employeeId", referencedColumnName = "id", nullable = false)
    public EmployeeModel getEmployeeByEmployeeId() {
        return employeeByEmployeeId;
    }

    public void setEmployeeByEmployeeId(EmployeeModel employeeByEmployeeId) {
        this.employeeByEmployeeId = employeeByEmployeeId;
    }
}
