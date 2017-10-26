package sg.edu.nus.iss.siacdm.task;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import sg.edu.nus.iss.siacdm.DaoFactory;
import sg.edu.nus.iss.siacdm.model.Task;

@Path("/")
public class TaskResource {
  private TaskDao dao;

  public TaskResource() {
    dao = DaoFactory.getTaskDao();
  }

  @Path("tasks")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Task> findAll() {
    List<Task> tasks;
    tasks = dao.findAll();
    return tasks;
  }

  @Path("task/{id}")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Task findOne(@PathParam("id") int id) {
    Task task;
    Task example = new Task();
    example.setId(id);
    task = dao.findOne(example);
    return task;
  }

  @Path("task")
  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Task create(Task radioProgram) {
    Task result = dao.create(radioProgram);
    return result;
  }

  @Path("task")
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Task update(Task radioProgram) {
    Task result = dao.update(radioProgram);
    return result;
  }

  @Path("task/{id}")
  @DELETE
  public void delete(@PathParam("id") int id) {
    Task example = new Task();
    example.setId(id);
    dao.delete(example);
  }
}
