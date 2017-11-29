package Module.TicketRead;

import Module.FilterEntity;
import Module.User.UserModel;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.QueryParam;

public class SearchTicketReadEntity extends FilterEntity {
    @QueryParam("ticketId")
    public Integer ticketId;
    @QueryParam("employeeId")
    public Integer employeeId;
    @QueryParam("status")
//    public byte status;

    public CriteriaQuery<TicketreadModel> applyTo(CriteriaBuilder builder, CriteriaQuery<TicketreadModel> criteria, Root<TicketreadModel> root) {
        if (ticketId != null) {
            criteria.where(builder.equal(root.get(TicketreadModel_.ticketId), ticketId));
        }
        if (employeeId != null) {
            criteria.where(builder.equal(root.get(TicketreadModel_.employeeId), employeeId));
        }
//        if (status != null) {
//            criteria.where(builder.equal(root.get(TicketreadModel_.status), status));
//        }

        return criteria;
    }

}
