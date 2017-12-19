package Module.TicketRead;

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

public class SearchTicketReadEntity extends FilterEntity {
    @QueryParam("ticketId")
    public Integer ticketId;
    @QueryParam("employeeId")
    public Integer employeeId;
    @QueryParam("status")
//    public byte status;

    public List<Predicate> applyTo(CriteriaBuilder builder, List<Predicate> predicates, Root<TicketreadModel> root) {
        if (ticketId != null) {
            predicates.add(builder.equal(root.get(TicketreadModel_.ticketId), ticketId));
        }
        if (employeeId != null) {
            predicates.add(builder.equal(root.get(TicketreadModel_.employeeId), employeeId));
        }
//        if (status != null) {
//            predicates.add(builder.equal(root.get(TicketreadModel_.status), status));
//        }

        return predicates;
    }

}
