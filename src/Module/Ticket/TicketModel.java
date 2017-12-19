package Module.Ticket;

import Module.Employee.EmployeeModel;
import Module.Location.LocationModel;
import Module.TicketAttribute.TicketattributeModel;
import Module.TicketImage.TicketimageModel;
import Module.TicketRead.TicketreadModel;
import Module.TicketRelater.TicketrelaterModel;
import Module.TicketThread.TicketthreadModel;

import javax.persistence.*;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "ticket", schema = "btl", catalog = "")
public class TicketModel {
    private Integer id;
    private String content;
    private String subject;
    private Integer createEmployeeId;
    private Integer status;
    private Integer priority;
    private Timestamp deadline;
    private Integer assignedEmployeeId;
    private Byte rating;
    private Integer locationId;
    private Timestamp resolvedTime;
    private Timestamp closedTime;
    private Timestamp createdTime;
    private Timestamp updatedTime;
    private Timestamp deletedTime;
    private EmployeeModel employeeByCreateEmployeeId;
    private EmployeeModel employeeByAssignedEmployeeId;
    private LocationModel locationByLocationId;
    private Collection<TicketattributeModel> ticketattributesById;
    private Collection<TicketimageModel> ticketimagesById;
    private Collection<TicketreadModel> ticketreadsById;
    private Collection<TicketrelaterModel> ticketrelatersById;
    private Collection<TicketthreadModel> ticketthreadsById;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
    @Column(name = "subject", nullable = false, length = 255)
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Basic
    @Column(name = "createEmployeeId", nullable = false)
    public Integer getCreateEmployeeId() {
        return createEmployeeId;
    }

    public void setCreateEmployeeId(Integer createEmployeeId) {
        this.createEmployeeId = createEmployeeId;
    }

    @Basic
    @Column(name = "status", nullable = false)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "priority", nullable = false)
    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @Basic
    @Column(name = "deadline", nullable = false)
    public Timestamp getDeadline() {
        return deadline;
    }

    public void setDeadline(Timestamp deadline) {
        this.deadline = deadline;
    }

    @Basic
    @Column(name = "assignedEmployeeId", nullable = true)
    public Integer getAssignedEmployeeId() {
        return assignedEmployeeId;
    }

    public void setAssignedEmployeeId(Integer assignedEmployeeId) {
        this.assignedEmployeeId = assignedEmployeeId;
    }

    @Basic
    @Column(name = "rating", nullable = true)
    public Byte getRating() {
        return rating;
    }

    public void setRating(Byte rating) {
        this.rating = rating;
    }

    @Basic
    @Column(name = "locationId", nullable = false)
    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    @Basic
    @Column(name = "resolvedTime", nullable = true)
    public Timestamp getResolvedTime() {
        return resolvedTime;
    }

    public void setResolvedTime(Timestamp resolvedTime) {
        this.resolvedTime = resolvedTime;
    }

    @Basic
    @Column(name = "closedTime", nullable = true)
    public Timestamp getClosedTime() {
        return closedTime;
    }

    public void setClosedTime(Timestamp closedTime) {
        this.closedTime = closedTime;
    }

    @Basic
    @Column(name = "createdTime", nullable = false)
    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    @Basic
    @Column(name = "updatedTime", nullable = true)
    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }

    @Basic
    @Column(name = "deletedTime", nullable = true)
    public Timestamp getDeletedTime() {
        return deletedTime;
    }

    public void setDeletedTime(Timestamp deletedTime) {
        this.deletedTime = deletedTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TicketModel that = (TicketModel) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (subject != null ? !subject.equals(that.subject) : that.subject != null) return false;
        if (createEmployeeId != null ? !createEmployeeId.equals(that.createEmployeeId) : that.createEmployeeId != null)
            return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (priority != null ? !priority.equals(that.priority) : that.priority != null) return false;
        if (deadline != null ? !deadline.equals(that.deadline) : that.deadline != null) return false;
        if (assignedEmployeeId != null ? !assignedEmployeeId.equals(that.assignedEmployeeId) : that.assignedEmployeeId != null)
            return false;
        if (rating != null ? !rating.equals(that.rating) : that.rating != null) return false;
        if (locationId != null ? !locationId.equals(that.locationId) : that.locationId != null) return false;
        if (resolvedTime != null ? !resolvedTime.equals(that.resolvedTime) : that.resolvedTime != null) return false;
        if (closedTime != null ? !closedTime.equals(that.closedTime) : that.closedTime != null) return false;
        if (createdTime != null ? !createdTime.equals(that.createdTime) : that.createdTime != null) return false;
        if (updatedTime != null ? !updatedTime.equals(that.updatedTime) : that.updatedTime != null) return false;
        if (deletedTime != null ? !deletedTime.equals(that.deletedTime) : that.deletedTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (createEmployeeId != null ? createEmployeeId.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (priority != null ? priority.hashCode() : 0);
        result = 31 * result + (deadline != null ? deadline.hashCode() : 0);
        result = 31 * result + (assignedEmployeeId != null ? assignedEmployeeId.hashCode() : 0);
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        result = 31 * result + (locationId != null ? locationId.hashCode() : 0);
        result = 31 * result + (resolvedTime != null ? resolvedTime.hashCode() : 0);
        result = 31 * result + (closedTime != null ? closedTime.hashCode() : 0);
        result = 31 * result + (createdTime != null ? createdTime.hashCode() : 0);
        result = 31 * result + (updatedTime != null ? updatedTime.hashCode() : 0);
        result = 31 * result + (deletedTime != null ? deletedTime.hashCode() : 0);
        return result;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "createEmployeeId", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public EmployeeModel getEmployeeByCreateEmployeeId() {
        return employeeByCreateEmployeeId;
    }

    public void setEmployeeByCreateEmployeeId(EmployeeModel employeeByCreateEmployeeId) {
        this.employeeByCreateEmployeeId = employeeByCreateEmployeeId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignedEmployeeId", referencedColumnName = "id", insertable = false, updatable = false)
    public EmployeeModel getEmployeeByAssignedEmployeeId() {
        return employeeByAssignedEmployeeId;
    }

    public void setEmployeeByAssignedEmployeeId(EmployeeModel employeeByAssignedEmployeeId) {
        this.employeeByAssignedEmployeeId = employeeByAssignedEmployeeId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "locationId", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public LocationModel getLocationByLocationId() {
        return locationByLocationId;
    }

    public void setLocationByLocationId(LocationModel locationByLocationId) {
        this.locationByLocationId = locationByLocationId;
    }

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "ticketByTicketId")
    public Collection<TicketattributeModel> getTicketattributesById() {
        return ticketattributesById;
    }

    public void setTicketattributesById(Collection<TicketattributeModel> ticketattributesById) {
        this.ticketattributesById = ticketattributesById;
    }

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "ticketByTicketId")
    public Collection<TicketimageModel> getTicketimagesById() {
        return ticketimagesById;
    }

    public void setTicketimagesById(Collection<TicketimageModel> ticketimagesById) {
        this.ticketimagesById = ticketimagesById;
    }

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "ticketByTicketId")
    public Collection<TicketreadModel> getTicketreadsById() {
        return ticketreadsById;
    }

    public void setTicketreadsById(Collection<TicketreadModel> ticketreadsById) {
        this.ticketreadsById = ticketreadsById;
    }

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "ticketByTicketId")
    public Collection<TicketrelaterModel> getTicketrelatersById() {
        return ticketrelatersById;
    }

    public void setTicketrelatersById(Collection<TicketrelaterModel> ticketrelatersById) {
        this.ticketrelatersById = ticketrelatersById;
    }

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "ticketByTicketId")
    public Collection<TicketthreadModel> getTicketthreadsById() {
        return ticketthreadsById;
    }

    public void setTicketthreadsById(Collection<TicketthreadModel> ticketthreadsById) {
        this.ticketthreadsById = ticketthreadsById;
    }
}

