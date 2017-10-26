package sg.edu.nus.iss.siacdm.account;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import sg.edu.nus.iss.siacdm.DaoFactory;
import sg.edu.nus.iss.siacdm.model.User;
import sg.edu.nus.iss.siacdm.user.UserDao;

@Path("/account")
public class AccountResource {
  private AccountDao accountDao;
  private UserDao userDao;

  public AccountResource() {
    accountDao = DaoFactory.getAcountDao();
    userDao = DaoFactory.getUserDao();
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public User authenticate(User user) {
    User target = accountDao.findOne(user.getUserId(), user.getPassword());
    if (target != null) {
       target = userDao.findOne(target);
       target.setAuthenticated(true);
    } else {
      target = new User();
      target.setAuthenticated(false);
    }
    return target;
  }
}
