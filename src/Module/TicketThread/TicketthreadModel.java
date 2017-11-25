package Module.TicketThread;

import Module.Employee.EmployeeModel;
import Module.Ticket.TicketModel;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ticketthread", schema = "btl", catalog = "")
public class TicketthreadModel {
    private int id;
    private int ticketId;
    private int employeeId;
    private String content;
    private Byte type;
    private Timestamp createTime;
    private Timestamp updateTime;
    private TicketModel ticketByTicketId;
    private EmployeeModel employeeByEmployeeId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ticketId", nullable = false)
    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    @Basic
    @Column(name = "employeeId", nullable = false)
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    @Basic
    @Column(name = "content", nullable = false, length = -1)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "type", nullable = true)
    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    @Basic
    @Column(name = "createTime", nullable = false)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "updateTime", nullable = false)
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TicketthreadModel that = (TicketthreadModel) o;

        if (id != that.id) return false;
        if (ticketId != that.ticketId) return false;
        if (employeeId != that.employeeId) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + ticketId;
        result = 31 * result + employeeId;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
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
