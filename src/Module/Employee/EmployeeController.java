package Module.Employee;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.handler.MessageContext;
import java.util.List;

@Path("/employees")
public class EmployeeController {
    @Inject
    private EmployeeService employeeService;

    public EmployeeController() {

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<EmployeeEntity> get(@BeanParam SearchEmployeeEntity searchEmployeeEntity) {
        return employeeService.get(searchEmployeeEntity);
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/Count")
    public int count(@BeanParam SearchEmployeeEntity searchEmployeeEntity) {
        return 100;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{edgeId}")
    public EmployeeEntity getId(@PathParam("edgeId") int edgeId) {
        return employeeService.get(edgeId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public EmployeeEntity create( EmployeeEntity fileEntity) {
        return employeeService.create(fileEntity);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{edgeId}")
    public EmployeeEntity update(@PathParam("edgeId") int edgeId, EmployeeEntity fileEntity) {
        return employeeService.update(edgeId, fileEntity);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{edgeId}")
    public void delete(@PathParam("edgeId") int edgeId) {
        employeeService.delete(edgeId);
    }

}