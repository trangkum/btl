package Module.TicketThread;

import Module.FilterEntity;
import Module.User.UserModel;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.QueryParam;
import java.util.List;

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

    public List<Predicate> applyTo(CriteriaBuilder builder, List<Predicate> predicates, Root<TicketthreadModel> root) {
        if (id != null) {
            predicates.add(builder.equal(root.get(TicketthreadModel_.id), id));
        }
        if (ticketId != null) {
            predicates.add(builder.equal(root.get(TicketthreadModel_.ticketId), ticketId));
        }
        if (employeeId != null) {
            predicates.add(builder.equal(root.get(TicketthreadModel_.employeeId), employeeId));
        }
        if (content != null && !content.isEmpty()) {
            predicates.add(builder.like(builder.lower(root.get(TicketthreadModel_.content)), "%" + content.toLowerCase() + "%"));
        }
        if (type != null) {
            predicates.add(builder.equal(root.get(TicketthreadModel_.type), type));
        }
        return predicates;
    }

}
