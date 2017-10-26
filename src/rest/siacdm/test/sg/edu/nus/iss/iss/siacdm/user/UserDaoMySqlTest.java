package sg.edu.nus.iss.iss.siacdm.user;

import sg.edu.nus.iss.siacdm.user.UserDaoMySql;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import sg.edu.nus.iss.siacdm.model.Role;
import sg.edu.nus.iss.siacdm.model.RoleMember;
import sg.edu.nus.iss.siacdm.model.User;
import sg.edu.nus.iss.siacdm.util.MySqlQuery;

public class UserDaoMySqlTest {
    private static User dummy;
    
    public UserDaoMySqlTest(){
        
    } 
    
    @BeforeClass
    public static void setUpClass(){
        deleteDummyData();   
    }
    
    @AfterClass
    public static void tearDownClass(){
        deleteDummyData();
    }
    
    @Before
    public void setUp(){
        dummy = createDummyData();
    }
    
    @After
    public void tearDown(){
        deleteDummyData();
    }
    
    @Test
    public void testFindAll(){
       UserDaoMySql dao = new UserDaoMySql();
       List<User> users = dao.findAll();
       assertNotNull(users);
       assertTrue(users.stream().anyMatch(x -> x.getUserId().equals(dummy.getUserId())));
       assertTrue(users.stream().anyMatch(x -> x.getRoles().size()>0));
    }
    
    @Test
    public void testFindOne(){
        UserDaoMySql dao = new UserDaoMySql();
        User user = dao.findOne(dummy);
        assertNotNull(user);
        assertEquals(dummy.getUserId(), user.getUserId());
        assertEquals(dummy.getPassword(), user.getPassword());
        assertEquals(dummy.getFullName(), user.getFullName());
        assertEquals(dummy.getRoles().size(), user.getRoles().size());
    }
    
    @Test
    public void testCreate(){
        deleteDummyData();
        UserDaoMySql dao = new UserDaoMySql();
        dao.createUser(dummy);
        User user = dao.findOne(dummy);
        assertNotNull(user);
        assertEquals(dummy.getUserId(), user.getUserId());
        assertEquals(dummy.getPassword(), user.getPassword());
        assertEquals(dummy.getFullName(), user.getFullName());
        assertEquals(dummy.getRoles().size(),user.getRoles().size());
    }
    
    @Test
    public void testUpdate(){
        User expected = dummy;
        Role role1 = new Role();
        Role role2 = new Role();
        role1.setRoleId("technician");
        role2.setRoleId("admin");
        List<Role> roles = new ArrayList<>();
        roles.add(role1);
        roles.add(role2);
        expected.setPassword("new_Password");
        expected.setFullName("new_Name");
        expected.setRoles(roles);
        UserDaoMySql dao = new UserDaoMySql();
        dao.updateUser(expected);
        User user = dao.findOne(expected);
        assertNotNull(user);
        assertEquals(expected.getUserId(), user.getUserId());
        assertEquals(expected.getPassword(), user.getPassword());
        assertEquals(expected.getFullName(), user.getFullName());
        assertEquals(expected.getRoles().size(), user.getRoles().size());
        List<Role> userRoles = user.getRoles();
        userRoles.forEach(m-> {                    
                    assertTrue(roles.stream().anyMatch(x -> x.getRoleId().equals(m.getRoleId())));
                });
    }
    
    @Test
    public void testDelete(){
        UserDaoMySql dao = new UserDaoMySql();
        dao.deleteUser(dummy);
        User user = dao.findOne(dummy);
        assertNull(user);
    }
    
    private static User createDummyData() {
        String createSql = "insert into siacdm.user "
                            + "(id, password, name) "
                            + "values ('dummy','dummy password','dummy name')";
        MySqlQuery query = new MySqlQuery(createSql);
        query.update();
        String createSqlForRoles = "insert into \n" +
                                "siacdm.user_role (user_id, role_id, updated_by) \n" +
                                "values \n" +
                                "('dummy','supervisor','system'),\n" +
                                "('dummy','admin','system');";      
        MySqlQuery query2 = new MySqlQuery(createSqlForRoles);
        query2.update();
        String Selectsql = "select id,password,name \n" +
                            "from siacdm.user\n" +
                            "where id='dummy'";
        query = new MySqlQuery(Selectsql, 
                (u) -> {
                   User user = new User();
                   user.setUserId(u.getString("id"));
                   user.setPassword(u.getString("password"));
                   user.setFullName(u.getString("name"));
                   return user;
                });
        User user = query.findOne();
        String SelectSqlForRole = "select user_id, role_id, updated_by\n" +
                                "from siacdm.user_role\n" +
                                "where user_id = 'dummy';";
        query2 = new MySqlQuery(SelectSqlForRole, 
                (m)-> {
                    RoleMember rm = new RoleMember();
                    rm.setRoleId(m.getString("role_id"));
                    return rm;
                });
        List<RoleMember> rolemembers = query2.findAll();
        rolemembers.forEach(m-> {
                    List<Role> roles = user.getRoles();
                    Role thisRole = new Role();
                    thisRole.setRoleId(m.getRoleId());
                    roles.add(thisRole);
        });
        return user;
    }
    
    private static void deleteDummyData(){
        String deletesql = "delete from siacdm.user where id = ?";
        if(dummy != null){
            MySqlQuery query = new MySqlQuery(deletesql, 
            (d) -> {
               d.setString(1, dummy.getUserId());
            });
            query.update();
        }
        String deleteRoleSql = "delete from siacdm.user_role where user_id = ?";
        if(dummy != null){
            MySqlQuery query = new MySqlQuery(deleteRoleSql, 
                    (p)-> {
                       p.setString(1, dummy.getUserId()); 
                    });
            query.update();
        }
    }
}
