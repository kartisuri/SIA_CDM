package sg.edu.nus.iss.siacdm.account;

import sg.edu.nus.iss.siacdm.model.User;

public interface AccountDao {
  
  public User findOne(String userId, String password);
}
