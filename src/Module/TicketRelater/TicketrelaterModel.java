package Module.TicketRelater;

import Module.Employee.EmployeeModel;
import Module.Ticket.TicketModel;

import javax.persistence.*;

@Entity
@Table(name = "ticketrelater", schema = "btl", catalog = "")
@IdClass(TicketrelaterModelPK.class)
public class TicketrelaterModel {
    private int employeeId;
    private int ticketId;
    private EmployeeModel employeeByEmployeeId;
    private TicketModel ticketByTicketId;

    @Id
    @Column(name = "employeeId", nullable = false)
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    @Id
    @Column(name = "ticketId", nullable = false)
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

        TicketrelaterModel that = (TicketrelaterModel) o;

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

    @ManyToOne
    @JoinColumn(name = "employeeId", referencedColumnName = "id", nullable = false)
    public EmployeeModel getEmployeeByEmployeeId() {
        return employeeByEmployeeId;
    }

    public void setEmployeeByEmployeeId(EmployeeModel employeeByEmployeeId) {
        this.employeeByEmployeeId = employeeByEmployeeId;
    }

    @ManyToOne
    @JoinColumn(name = "ticketId", referencedColumnName = "id", nullable = false)
    public TicketModel getTicketByTicketId() {
        return ticketByTicketId;
    }

    public void setTicketByTicketId(TicketModel ticketByTicketId) {
        this.ticketByTicketId = ticketByTicketId;
    }
}
