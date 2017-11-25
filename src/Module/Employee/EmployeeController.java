ackage Module.Employee;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/edges")
public class EmployeeController {
    @Inject
    private EmployeeService employeeService;

    public EmployeeController() {

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<FileEntity> get(@BeanParam SearchFileModel searchFileModel) {
        return employeeService.get(searchFileModel);
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/Count")
    public int count(@BeanParam SearchFileModel searchFileModel) {
        return 100;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{edgeId}")
    public FileEntity getId(@PathParam("edgeId") int edgeId) {
        return employeeService.get(edgeId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public FileEntity create(FileEntity fileEntity) {
        return employeeService.create(fileEntity);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{edgeId}")
    public FileEntity update(@PathParam("edgeId") int edgeId, FileEntity fileEntity) {
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