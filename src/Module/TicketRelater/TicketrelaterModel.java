package Module.TicketRelater;


import Module.Employee.EmployeeModel;
import Module.Ticket.TicketModel;

import javax.persistence.*;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Entity
@Table(name = "ticketrelater", schema = "btl", catalog = "")
@IdClass(TicketrelaterModelPK.class)
public class TicketrelaterModel {
    private Integer employeeId;
    private Integer ticketId;
    private EmployeeModel employeeByEmployeeId;
    private TicketModel ticketByTicketId;

    @Id
    @Column(name = "employeeId", nullable = false)
    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    @Id
    @Column(name = "ticketId", nullable = false)
    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TicketrelaterModel that = (TicketrelaterModel) o;

        if (employeeId != null ? !employeeId.equals(that.employeeId) : that.employeeId != null) return false;
        if (ticketId != null ? !ticketId.equals(that.ticketId) : that.ticketId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = employeeId != null ? employeeId.hashCode() : 0;
        result = 31 * result + (ticketId != null ? ticketId.hashCode() : 0);
        return result;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employeeId", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public EmployeeModel getEmployeeByEmployeeId() {
        return employeeByEmployeeId;
    }

    public void setEmployeeByEmployeeId(EmployeeModel employeeByEmployeeId) {
        this.employeeByEmployeeId = employeeByEmployeeId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticketId", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public TicketModel getTicketByTicketId() {
        return ticketByTicketId;
    }

    public void setTicketByTicketId(TicketModel ticketByTicketId) {
        this.ticketByTicketId = ticketByTicketId;
    }
}

@StaticMetamodel(TicketrelaterModel.class)
class TicketrelaterModel_ {
    public static volatile SingularAttribute<TicketrelaterModel, Integer> employeeId;
    public static volatile SingularAttribute<TicketrelaterModel, Integer> ticketId;
}