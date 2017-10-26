package sg.edu.nus.iss.siacdm.account;

import sg.edu.nus.iss.siacdm.model.User;
import sg.edu.nus.iss.siacdm.util.MySqlQuery;

public class AccountDaoMySql implements AccountDao {

  @Override
  public User findOne(String userId, String password) {
    String sql = "select id, password, name from siacdm.user where (id = ? and password = ?);";
    MySqlQuery query = new MySqlQuery(sql,
        (statement) -> {
          statement.setString(1, userId);
          statement.setString(2, password);
        },
        (result) -> {
          User user = new User();
          user.setUserId(result.getString("id"));
          user.setPassword(result.getString("password"));
          user.setFullName(result.getString("name"));
          user.setAuthenticated(true);
          return user;
        });
    return query.findOne();
  }
}
