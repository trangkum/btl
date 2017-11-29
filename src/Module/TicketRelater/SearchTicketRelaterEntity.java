package Module.TicketRelater;

import Module.FilterEntity;
import Module.User.UserModel;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.QueryParam;

public class SearchTicketRelaterEntity extends FilterEntity {
    @QueryParam("employeeId")
    public Integer employeeId;
    @QueryParam("ticketId")
    public Integer ticketId;

    public CriteriaQuery<TicketrelaterModel> applyTo(CriteriaBuilder builder, CriteriaQuery<TicketrelaterModel> criteria, Root<TicketrelaterModel> root) {
        if (ticketId != null) {
            criteria.where(builder.equal(root.get(TicketrelaterModel_.ticketId), ticketId));
        }
        if (employeeId != null) {
            criteria.where(builder.equal(root.get(TicketrelaterModel_.employeeId), employeeId));
        }
        return criteria;
    }
}
