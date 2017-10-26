package sg.edu.nus.iss.siacdm.user;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import sg.edu.nus.iss.siacdm.DaoFactory;
import sg.edu.nus.iss.siacdm.model.RoleMember;

@Path("/")
public class RoleMemberResource {
  private RoleMemberDao dao;
  
  public RoleMemberResource() {
    dao = DaoFactory.getRoleMemberDao();
  }
  
  @Path("technicians")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<RoleMember> findTechnicians(){
    List<RoleMember> technicians;
    technicians = dao.findAll(RoleIds.TECHNICIAN); 
    return technicians;
  }
  
  @Path("admins")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<RoleMember> findAdmins(){
    List<RoleMember> admins;
    admins = dao.findAll(RoleIds.ADMIN); 
    return admins;
  }
  
  @Path("supervisors")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<RoleMember> findSupervisors(){
    List<RoleMember> managers;
    managers = dao.findAll(RoleIds.SUPERVISOR); 
    return managers;
  }
}
