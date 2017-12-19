package Module.TicketAttribute;


import Module.Ticket.TicketModel;

import javax.persistence.*;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Entity
@Table(name = "ticketattribute", schema = "btl", catalog = "")
public class TicketattributeModel {
    private Integer id;
    private String status;
    private String priority;
    private String rating;
    private String reopened;
    private Integer ticketId;
    private TicketModel ticketByTicketId;

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
    @Column(name = "status", nullable = true, length = 255)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "priority", nullable = true, length = 255)
    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @Basic
    @Column(name = "rating", nullable = true, length = 255)
    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Basic
    @Column(name = "reopened", nullable = true, length = 255)
    public String getReopened() {
        return reopened;
    }

    public void setReopened(String reopened) {
        this.reopened = reopened;
    }

    @Basic
    @Column(name = "ticketId", nullable = true)
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

        TicketattributeModel that = (TicketattributeModel) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (priority != null ? !priority.equals(that.priority) : that.priority != null) return false;
        if (rating != null ? !rating.equals(that.rating) : that.rating != null) return false;
        if (reopened != null ? !reopened.equals(that.reopened) : that.reopened != null) return false;
        if (ticketId != null ? !ticketId.equals(that.ticketId) : that.ticketId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (priority != null ? priority.hashCode() : 0);
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        result = 31 * result + (reopened != null ? reopened.hashCode() : 0);
        result = 31 * result + (ticketId != null ? ticketId.hashCode() : 0);
        return result;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticketId", referencedColumnName = "id", insertable = false, updatable = false)
    public TicketModel getTicketByTicketId() {
        return ticketByTicketId;
    }

    public void setTicketByTicketId(TicketModel ticketByTicketId) {
        this.ticketByTicketId = ticketByTicketId;
    }
}

@StaticMetamodel(TicketattributeModel.class)
class TicketattributeModel_ {
    public static volatile SingularAttribute<TicketattributeModel, Integer> id;
    public static volatile SingularAttribute<TicketattributeModel, String> status;
    public static volatile SingularAttribute<TicketattributeModel, String> priority;
    public static volatile SingularAttribute<TicketattributeModel, String> rating;
    public static volatile SingularAttribute<TicketattributeModel, String> reopened;
    public static volatile SingularAttribute<TicketattributeModel, Integer> ticketId;
}