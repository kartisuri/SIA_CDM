package sg.edu.nus.iss.siacdm.user;

import java.util.List;
import sg.edu.nus.iss.siacdm.model.Role;
import sg.edu.nus.iss.siacdm.model.RoleMember;
import sg.edu.nus.iss.siacdm.model.User;
import sg.edu.nus.iss.siacdm.util.MySqlQuery;

public class UserDaoMySql implements UserDao {

  @Override
  public List<User> findAll() {
    List<User> users = findAllUsers();
    List<RoleMember> roleMembers = findRoleMembers();
    if (users != null) {
      users.forEach(u -> {
        roleMembers.forEach(m -> {
          if (m.getUserId().equalsIgnoreCase(u.getUserId())) {
            List<Role> roles = u.getRoles();
            Role thisRole = new Role();
            thisRole.setRoleId(m.getRoleId());
            roles.add(thisRole);
          }
        });
        int count = findAssignedSlots(u);
        u.setSlots(count);
      });
    }
    return users;
  }

  protected List<User> findAllUsers() {
    String sql = "select \n"
        + "u.id,\n"
        + "u.password,\n"
        + "u.name\n"
        + "from siacdm.user u";
    MySqlQuery query = new MySqlQuery(sql,
        (u) -> {
          User user = new User();
          user.setUserId(u.getString("id"));
          user.setPassword(u.getString("password"));
          user.setFullName(u.getString("name"));
          return user;
        });
    return query.findAll();
  }

  protected List<RoleMember> findRoleMembers() {
    String sql = "select\n"
        + "ur.user_id,\n"
        + "ur.role_id\n"
        + "from\n"
        + "siacdm.user_role ur\n"
        + "order by\n"
        + "user_id";
    MySqlQuery query = new MySqlQuery(sql,
        (p) -> {
          RoleMember rm = new RoleMember();
          rm.setRoleId(p.getString("role_id"));
          rm.setUserId(p.getString("user_id"));
          return rm;
        });
    return query.findAll();
  }

  @Override
  public User findOne(User record) {
    User user = findOneUser(record);
    List<RoleMember> roleMembers = findRoleMember(record);
    if (user != null) {
      roleMembers.forEach(m -> {
        List<Role> roles = user.getRoles();
        Role thisRole = new Role();
        thisRole.setRoleId(m.getRoleId());
        roles.add(thisRole);
      });
      int count = findAssignedSlots(user);
      user.setSlots(count);
    }
    return user;
  }

  protected User findOneUser(User record) {
    String sql = "Select\n"
        + "u.id,\n"
        + "u.password,\n"
        + "u.name\n"
        + "from\n"
        + "siacdm.user u\n"
        + "where (u.id = ?)";
    MySqlQuery query = new MySqlQuery(sql,
        (s) -> {
          s.setString(1, record.getUserId());
        },
        (r) -> {
          User user = new User();
          user.setUserId(r.getString("id"));
          user.setPassword(r.getString("password"));
          user.setFullName(r.getString("name"));
          return user;
        });
    return query.findOne();
  }

  protected List<RoleMember> findRoleMember(User record) {
    String sql = "select\n"
        + "ur.user_id,\n"
        + "ur.role_id\n"
        + "from\n"
        + "siacdm.user_role ur\n"
        + "where \n"
        + "ur.user_id = ?";
    MySqlQuery query = new MySqlQuery(sql,
        (p) -> {
          p.setString(1, record.getUserId());
        },
        (m) -> {
          RoleMember rm = new RoleMember();
          rm.setRoleId(m.getString("role_id"));
          return rm;
        });
    return query.findAll();
  }

  @Override
  public User createUser(User newRecord) {
    String sql = "insert into siacdm.user (id, password, name) values (?,?,?)";
    MySqlQuery query = new MySqlQuery(sql,
        (c) -> {
          c.setString(1, newRecord.getUserId());
          c.setString(2, newRecord.getPassword());
          c.setString(3, newRecord.getFullName());
        });
    query.update(newRecord);
    createRole(newRecord);
    User example = new User();
    example.setUserId(newRecord.getUserId());
    User created = findOne(example);
    return created;   
  }

  private void createRole(User newrecord) {
    List<Role> roles = newrecord.getRoles();
    for (int i = 0; i < roles.size(); i++) {
      Role current = roles.get(i);
      String sql = "insert into siacdm.user_role (user_id, role_id, updated_by) \n"
          + "values (?,?,'system')";
      MySqlQuery query = new MySqlQuery(sql,
          (p) -> {
            p.setString(1, newrecord.getUserId());
            p.setString(2, current.getRoleId());
          });
      query.update(newrecord);
    }
  }

  @Override
  public User updateUser(User newRecord) {
    String sql = "update siacdm.user set password = ?, name = ? where (id = ? )";
    MySqlQuery query = new MySqlQuery(sql,
        (s) -> {
          s.setString(1, newRecord.getPassword());
          s.setString(2, newRecord.getFullName());
          s.setString(3, newRecord.getUserId());
        });
    query.update(newRecord);
    updateRole(newRecord);
    User example = new User();
    example.setUserId(newRecord.getUserId());
    User updated = findOne(example);
    return updated;
  }

  private void updateRole(User newRecord) {
    deleteRole(newRecord);
    createRole(newRecord);
  }

  @Override
  public void deleteUser(User record) {
    String sql = "delete from siacdm.user where (id = ?)";
    MySqlQuery query = new MySqlQuery(sql,
        (d) -> {
          d.setString(1, record.getUserId());
        });
    query.update();
    deleteRole(record);
  }

  private void deleteRole(User record) {
    String sql = "delete from siacdm.user_role where (user_id = ?)";
    MySqlQuery query = new MySqlQuery(sql,
        (p) -> {
          p.setString(1, record.getUserId());
        });
    query.update();
  }

  @Override
  public User findAssignedUser(User record) {
    String sql = "select * \n"
        + "from siacdm.user u\n"
        + "where \n"
        + "(u.id = ?)\n"
        + "and\n"
        + "exists\n"
        + "(\n"
        + "select null\n"
        + "from siacdm.schedule s\n"
        + "where (s.technician_id = u.id)\n"
        + ")";
    MySqlQuery query = new MySqlQuery(sql,
        (p) -> {
          p.setString(1, record.getUserId());
        },
        (m) -> {
          User user = new User();
          user.setUserId(m.getString("id"));
          user.setPassword(m.getString("password"));
          user.setFullName(m.getString("name"));
          return user;
        });
    return query.findOne();
  }

  private int findAssignedSlots(User record) {
    String sql = "select\n"
        + "(Select count(1) \n"
        + "from siacdm.schedule s \n"
        + "where s.technician_id = ?) as slots;";
    MySqlQuery query = new MySqlQuery(sql,
        (p) -> {
          p.setString(1, record.getUserId());
        },
        (m) -> {
          int count = m.getInt("slots");
          return count;
        });
    return query.findOne();
  }
}
