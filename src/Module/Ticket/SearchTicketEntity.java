package Module.Ticket;

import Module.FilterEntity;
import Module.User.UserModel;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.QueryParam;
import java.sql.Timestamp;

public class SearchTicketEntity extends FilterEntity {
    @QueryParam("id")
    public Integer id;
    @QueryParam("content")
    public String content;
    @QueryParam("subject")
    public String subject;
    @QueryParam("createEmployeeId")
    public Integer createEmployeeId;
    @QueryParam("status")
    public Integer status;
    @QueryParam("priority")
    public Integer priority;
    @QueryParam("deadline")
    public Timestamp deadline;
    @QueryParam("assignedEmployeeId")
    public Integer assignedEmployeeId;
    @QueryParam("rating")
    public Byte rating;
    @QueryParam("groupId")
    public Integer groupId;
    @QueryParam("resolvedTime")
    public Timestamp resolvedTime;
    @QueryParam("closedTime")
    public Timestamp closedTime;
    @QueryParam("createdTime")
    public Timestamp createdTime;
    @QueryParam("updatedTime")
    public Timestamp updatedTime;
    @QueryParam("deletedTime")
    public Timestamp deletedTime;

    public CriteriaQuery<TicketModel> applyTo(CriteriaBuilder builder, CriteriaQuery<TicketModel> criteria, Root<TicketModel> root) {

        if (id != null) {
            criteria.where(builder.equal(root.get(TicketModel_.id), id));
        }
        if (content != null && !content.isEmpty()) {
            criteria.where(builder.like(builder.lower(root.get(TicketModel_.content)), "%" + content.toLowerCase() + "%"));
        }
        if (subject != null && !subject.isEmpty()) {
            criteria.where(builder.like(builder.lower(root.get(TicketModel_.subject)), "%" + subject.toLowerCase() + "%"));
        }
        if (createEmployeeId != null) {
            criteria.where(builder.equal(root.get(TicketModel_.createEmployeeId), createEmployeeId));
        }
        if (status != null) {
            criteria.where(builder.equal(root.get(TicketModel_.status), status));
        }
        if (priority != null) {
            criteria.where(builder.equal(root.get(TicketModel_.priority), priority));
        }
        if (deadline != null) {
            criteria.where(builder.equal(root.get(TicketModel_.deadline), deadline));
        }
        if (assignedEmployeeId != null) {
            criteria.where(builder.equal(root.get(TicketModel_.assignedEmployeeId), assignedEmployeeId));
        }
        if (rating != null) {
            criteria.where(builder.equal(root.get(TicketModel_.rating), rating));
        }

        if (groupId != null) {
            criteria.where(builder.equal(root.get(TicketModel_.groupId), groupId));
        }
        if (resolvedTime != null) {
            criteria.where(builder.equal(root.get(TicketModel_.resolvedTime), resolvedTime));
        }
        if (closedTime != null) {
            criteria.where(builder.equal(root.get(TicketModel_.closedTime), closedTime));
        }
        if (createdTime != null) {
            criteria.where(builder.equal(root.get(TicketModel_.createdTime), createdTime));
        }
        if (updatedTime != null) {
            criteria.where(builder.equal(root.get(TicketModel_.updatedTime), updatedTime));
        }
        if (deletedTime != null) {
            criteria.where(builder.equal(root.get(TicketModel_.deletedTime), deletedTime));
        }
        return criteria;
    }

}
