package Module.TicketImage;

import Module.FilterEntity;
import Module.User.UserModel;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.QueryParam;

public class SearchTicketImageEntity extends FilterEntity {
    @QueryParam("ticketId")
    public Integer ticketId;
    @QueryParam("fielId")
    public Integer fielId;

    public CriteriaQuery<TicketimageModel> applyTo(CriteriaBuilder builder, CriteriaQuery<TicketimageModel> criteria, Root<TicketimageModel> root) {
        if (ticketId != null) {
            criteria.where(builder.equal(root.get(TicketimageModel_.ticketId), ticketId));
        }
        if (fielId != null) {
            criteria.where(builder.equal(root.get(TicketimageModel_.fielId), fielId));
        }
        return criteria;
    }

}
