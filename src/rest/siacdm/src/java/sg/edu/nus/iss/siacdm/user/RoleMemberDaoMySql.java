package sg.edu.nus.iss.siacdm.user;

import java.util.List;
import sg.edu.nus.iss.siacdm.model.RoleMember;
import sg.edu.nus.iss.siacdm.util.MySqlQuery;

public class RoleMemberDaoMySql implements RoleMemberDao {

  @Override
  public List<RoleMember> findAll(String roleId) {
    String sql
        = "select\n"
        + "  u.id as userId,\n"
        + "  u.name as userName,\n"
        + "  ur.role_id as roleId,\n"
        + "  r.role_name as roleName\n"
        + "from\n"
        + "  siacdm.user u\n"
        + "left join\n"
        + "  siacdm.user_role ur \n"
        + "  on u.id = ur.user_id\n"
        + "left join\n"
        + "  siacdm.role r \n"
        + "  on ur.role_id = r.role_id\n"
        + "where\n"
        + "  ur.role_id = ? \n"
        + "order by\n"
        + "  userId";
    
    MySqlQuery query = new MySqlQuery(sql, 
    (s) -> {
        s.setString(1, roleId);
    },
    (r) -> {
      RoleMember m = new RoleMember();
      m.setUserId(r.getString("userId"));
      m.setUserName(r.getString("userName"));
      m.setRoleId(r.getString("roleId"));
      m.setRoleName(r.getString("roleName"));
      return m;
    });
    return query.findAll();
  }
}
