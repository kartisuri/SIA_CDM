package sg.edu.nus.iss.siacdm.user;

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
import sg.edu.nus.iss.siacdm.model.User;

@Path("/")
public class UserResource {
  private UserDao dao;

  public UserResource() {
    dao = DaoFactory.getUserDao();
  }

  @Path("users")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<User> findAll() {
    List<User> users;
    users = dao.findAll();
    return users;
  }

  @Path("user/{id}")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public User findOne(@PathParam("id") String userid) {
    User user;
    User example = new User();
    example.setUserId(userid);
    user = dao.findOne(example);
    return user;
  }

  @Path("user")
  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public User create(User user) {
    User created = dao.createUser(user);
    return created;
  }

  @Path("user")
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public User update(User user) {
    User updated = dao.updateUser(user);
    return updated;
  }

  @Path("user/{id}")
  @DELETE
  @Consumes(MediaType.APPLICATION_JSON)
  public void delete(@PathParam("id") String userid) {
    User example = new User();
    example.setUserId(userid);
    dao.deleteUser(example);
  }

  @Path("user/assigneduser/{id}")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public User findAssignedUser(@PathParam("id") String userid) {
    User example = new User();
    example.setUserId(userid);
    User user = dao.findAssignedUser(example);
    return user;
  }
}
