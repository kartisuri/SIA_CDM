package sg.edu.nus.iss.iss.siacdm.user;

import sg.edu.nus.iss.siacdm.user.RoleMemberDaoMySql;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sg.edu.nus.iss.siacdm.model.RoleMember;
import sg.edu.nus.iss.siacdm.user.RoleMemberDaoMySql;
import sg.edu.nus.iss.siacdm.util.MySqlQuery;

public class RoleMemberDaoMySqlTest {
  private static RoleMember dummy;
  public RoleMemberDaoMySqlTest() {
  }

  @BeforeClass
  public static void setUpClass() {
    deleteDummyData();
  }

  @AfterClass
  public static void tearDownClass() {
    deleteDummyData();
  }

  @Before
  public void setUp() {
    dummy = createDummyData();
  }

  @After
  public void tearDown() {
    deleteDummyData();
  }

  @Test
  public void testFindAll() {
    String roleId = dummy.getRoleId();
    RoleMemberDaoMySql dao = new RoleMemberDaoMySql();
    List<RoleMember> results = dao.findAll(roleId);
    assertNotNull(results);
    assertTrue(results.stream().allMatch(x-> x.getRoleId().equalsIgnoreCase(roleId)));
  }

  private static RoleMember createDummyData() {
    String createUserSql
        = "insert into siacdm.user\n"
        + "(id, password, name)\n"
        + "values \n"
        + "('dummy','dummy','dummy, the tester')";
    String createRoleSql
        = "insert into siacdm.role\n"
        + "(role_id, role_name)\n"
        + "values\n"
        + "('dummyrole', 'dummy role')";
    String createUserRoleSql
        = "insert into siacdm.user_role \n"
        + "(user_id, role_id, updated_by)\n"
        + "values\n"
        + "('dummy', 'dummyrole', 'tester')";
    MySqlQuery q = new MySqlQuery(createUserSql);
    q.update();
    q = new MySqlQuery(createRoleSql);
    q.update();
    q = new MySqlQuery(createUserRoleSql);
    q.update();
    String selectSql
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
        + "  ur.role_id = 'dummyrole' \n"
        + "order by\n"
        + "  userId";
    q = new MySqlQuery(selectSql,
        (r) -> {
          RoleMember m = new RoleMember();
          m.setUserId(r.getString("userId"));
          m.setUserName(r.getString("userName"));
          m.setRoleId(r.getString("roleId"));
          m.setRoleName(r.getString("roleName"));
          return m;
        });
    return q.findOne();
  }

  private static void deleteDummyData() {
    String deleteUserRoleSql = "delete from siacdm.user_role where role_id = 'dummyrole'";
    String deleteRoleSql = "delete from siacdm.role where role_id = 'dummyrole'";
    String deleteUserSql = "delete from siacdm.user where id = 'dummy'";
    if (dummy != null) {
      new MySqlQuery(deleteUserRoleSql).update();
      new MySqlQuery(deleteRoleSql).update();
      new MySqlQuery(deleteUserSql).update();
    }
  }
}
