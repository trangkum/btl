package Module.TicketAttribute;

import Module.FilterEntity;
import Module.User.UserModel;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.QueryParam;

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

    public CriteriaQuery<TicketattributeModel> applyTo(CriteriaBuilder builder, CriteriaQuery<TicketattributeModel> criteria, Root<TicketattributeModel> root) {
        if (id != null) {
            criteria.where(builder.equal(root.get(TicketattributeModel_.id), id));
        }
        if (status != null && !status.isEmpty()) {
            criteria.where(builder.like(builder.lower(root.get(TicketattributeModel_.status)), "%" + status.toLowerCase() + "%"));
        }
        if (priority != null && !priority.isEmpty()) {
            criteria.where(builder.like(builder.lower(root.get(TicketattributeModel_.priority)), "%" + priority.toLowerCase() + "%"));
        }
        if (rating != null && !rating.isEmpty()) {
            criteria.where(builder.like(builder.lower(root.get(TicketattributeModel_.rating)), "%" + rating.toLowerCase() + "%"));
        }
        if (reopened != null && !reopened.isEmpty()) {
            criteria.where(builder.like(builder.lower(root.get(TicketattributeModel_.reopened)), "%" + reopened.toLowerCase() + "%"));
        }
        if (ticketId != null) {
            criteria.where(builder.equal(root.get(TicketattributeModel_.ticketId), ticketId));
        }
        return criteria;
    }

}
