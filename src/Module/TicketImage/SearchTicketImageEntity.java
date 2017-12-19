package Module.TicketImage;

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

public class SearchTicketImageEntity extends FilterEntity {
    @QueryParam("ticketId")
    public Integer ticketId;
    @QueryParam("fileId")
    public Integer fileId;

    public List<Predicate> applyTo(CriteriaBuilder builder, List<Predicate> predicates, Root<TicketimageModel> root) {
        if (ticketId != null) {
            predicates.add(builder.equal(root.get(TicketimageModel_.ticketId), ticketId));
        }
        if (fileId != null) {
            predicates.add(builder.equal(root.get(TicketimageModel_.fileId), fileId));
        }
        return predicates;
    }

}
