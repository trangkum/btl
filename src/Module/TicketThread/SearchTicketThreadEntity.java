package Module.TicketThread;

import Module.FilterEntity;
import Module.User.UserModel;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.QueryParam;

public class SearchTicketThreadEntity extends FilterEntity {
    @QueryParam("id")
    public Integer id;
    @QueryParam("ticketId")
    public Integer ticketId;
    @QueryParam("employeeId")
    public Integer employeeId;
    @QueryParam("content")
    public String content;
    @QueryParam("type")
    public Byte type;

    public CriteriaQuery<TicketthreadModel> applyTo(CriteriaBuilder builder, CriteriaQuery<TicketthreadModel> criteria, Root<TicketthreadModel> root) {
        if (id != null) {
            criteria.where(builder.equal(root.get(TicketthreadModel_.id), id));
        }
        if (ticketId != null) {
            criteria.where(builder.equal(root.get(TicketthreadModel_.ticketId), ticketId));
        }
        if (employeeId != null) {
            criteria.where(builder.equal(root.get(TicketthreadModel_.employeeId), employeeId));
        }
        if (content != null && !content.isEmpty()) {
            criteria.where(builder.like(builder.lower(root.get(TicketthreadModel_.content)), "%" + content.toLowerCase() + "%"));
        }
        if (type != null) {
            criteria.where(builder.equal(root.get(TicketthreadModel_.type), type));
        }
        return criteria;
    }

}
