package Module.TicketRead;


import Module.Employee.EmployeeModel;
import Module.Ticket.TicketModel;
import Module.TicketThread.TicketreadModelPK;

import javax.persistence.*;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Entity
@Table(name = "ticketread", schema = "btl", catalog = "")
@IdClass(TicketreadModelPK.class)
public class TicketreadModel {
    private Integer ticketId;
    private Integer employeeId;
    private Byte status;
    private TicketModel ticketByTicketId;
    private EmployeeModel employeeByEmployeeId;

    @Id
    @Column(name = "ticketId", nullable = false)
    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    @Id
    @Column(name = "employeeId", nullable = false)
    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    @Basic
    @Column(name = "status", nullable = false)
    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TicketreadModel that = (TicketreadModel) o;

        if (ticketId != null ? !ticketId.equals(that.ticketId) : that.ticketId != null) return false;
        if (employeeId != null ? !employeeId.equals(that.employeeId) : that.employeeId != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ticketId != null ? ticketId.hashCode() : 0;
        result = 31 * result + (employeeId != null ? employeeId.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
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
    @JoinColumn(name = "employeeId", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public EmployeeModel getEmployeeByEmployeeId() {
        return employeeByEmployeeId;
    }

    public void setEmployeeByEmployeeId(EmployeeModel employeeByEmployeeId) {
        this.employeeByEmployeeId = employeeByEmployeeId;
    }
}

@StaticMetamodel(TicketreadModel.class)
class TicketreadModel_ {
    public static volatile SingularAttribute<TicketreadModel, Integer> ticketId;
    public static volatile SingularAttribute<TicketreadModel, Integer> employeeId;
    public static volatile SingularAttribute<TicketreadModel, Byte> status;
}