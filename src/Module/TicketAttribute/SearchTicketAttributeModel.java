package Module.TicketAttribute;

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

public class SearchTicketAttributeModel extends FilterEntity {
    @QueryParam("id")
    public Integer id;
    @QueryParam("status")
    public String status;
    @QueryParam("priority")
    public String priority;
    @QueryParam("rating")
    public String rating;
    @QueryParam("reopened")
    public String reopened;
    @QueryParam("ticketId")
    public Integer ticketId;

    public List<Predicate> applyTo(CriteriaBuilder builder, List<Predicate> predicates, Root<TicketattributeModel> root) {
        if (id != null) {
            predicates.add(builder.equal(root.get(TicketattributeModel_.id), id));
        }
        if (status != null && !status.isEmpty()) {
            predicates.add(builder.like(builder.lower(root.get(TicketattributeModel_.status)), "%" + status.toLowerCase() + "%"));
        }
        if (priority != null && !priority.isEmpty()) {
            predicates.add(builder.like(builder.lower(root.get(TicketattributeModel_.priority)), "%" + priority.toLowerCase() + "%"));
        }
        if (rating != null && !rating.isEmpty()) {
            predicates.add(builder.like(builder.lower(root.get(TicketattributeModel_.rating)), "%" + rating.toLowerCase() + "%"));
        }
        if (reopened != null && !reopened.isEmpty()) {
            predicates.add(builder.like(builder.lower(root.get(TicketattributeModel_.reopened)), "%" + reopened.toLowerCase() + "%"));
        }
        if (ticketId != null) {
            predicates.add(builder.equal(root.get(TicketattributeModel_.ticketId), ticketId));
        }
        return predicates;
    }

}
