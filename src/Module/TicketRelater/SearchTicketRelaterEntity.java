package Module.TicketRelater;

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

public class SearchTicketRelaterEntity extends FilterEntity {
    @QueryParam("employeeId")
    public Integer employeeId;
    @QueryParam("ticketId")
    public Integer ticketId;

    public  List<Predicate>  applyTo(CriteriaBuilder builder, List<Predicate> predicates, Root<TicketrelaterModel> root) {
        if (ticketId != null) {
            predicates.add(builder.equal(root.get(TicketrelaterModel_.ticketId), ticketId));
        }
        if (employeeId != null) {
            predicates.add(builder.equal(root.get(TicketrelaterModel_.employeeId), employeeId));
        }
        return predicates;
    }
}
