package Module.Route;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.ws.rs.QueryParam;

public class SearchRouteModel {
    @QueryParam("edgeId")private int edgeId;
    @QueryParam("startX") private Double startX;
    @QueryParam("startY") private Double startY;
    @QueryParam("endX")private Double endX;
    @QueryParam("endY")private Double endY;

}
