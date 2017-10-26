package sg.edu.nus.iss.iss.siacdm.task;

import sg.edu.nus.iss.siacdm.task.TaskDaoMySql;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sg.edu.nus.iss.siacdm.model.Task;
import sg.edu.nus.iss.siacdm.util.MySqlQuery;

public class TaskDaoMySqlTest {
  private static Task dummy;

  public TaskDaoMySqlTest() {
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
    TaskDaoMySql dao = new TaskDaoMySql();
    List<Task> results = dao.findAll();
    assertNotNull(results);
    assertTrue(results.stream().anyMatch(x -> x.getId() == dummy.getId()));
  }

  @Test
  public void testFindOne() {
    TaskDaoMySql dao = new TaskDaoMySql();
    Task result = dao.findOne(dummy);
    assertNotNull(result);
    assertEquals(dummy.getId(), result.getId());
    assertEquals(dummy.getName(), result.getName());
    assertEquals(dummy.getDescription(), result.getDescription());
    assertEquals(dummy.getDuration(), result.getDuration());
    assertEquals(dummy.getUpdatedBy(), result.getUpdatedBy());
  }

  @Test
  public void testUpdate() {
    Task expected = dummy;
    expected.setName("new_name");
    expected.setDescription("new_description");
    expected.setDuration(45);
    expected.setUpdatedBy("tester");
    TaskDaoMySql dao = new TaskDaoMySql();
    Task result = dao.update(expected);
    assertNotNull(result);
    assertEquals(expected.getId(), result.getId());
    assertEquals(expected.getName(), result.getName());
    assertEquals(expected.getDescription(), result.getDescription());
    assertEquals(expected.getDuration(), result.getDuration());
    assertEquals(expected.getUpdatedBy(), result.getUpdatedBy());
  }

  @Test
  public void testCreate() {
    deleteDummyData();
    TaskDaoMySql dao = new TaskDaoMySql();
    Task result = dao.create(dummy);
    assertNotNull(result);
    assertNotEquals(dummy.getId(), result.getId()); // id should be auto generated for a new record
    assertEquals(dummy.getName(), result.getName());
    assertEquals(dummy.getDescription(), result.getDescription());
    assertEquals(dummy.getDuration(), result.getDuration());
    assertEquals(dummy.getUpdatedBy(), result.getUpdatedBy());
  }

  @Test
  public void testDelete() {
    TaskDaoMySql dao = new TaskDaoMySql();
    dao.delete(dummy);
    Task result = dao.findOne(dummy);
    assertNull(result);
  }

  private static Task createDummyData() {
    String createSql
        = "insert into siacdm.task "
        + "(name, description, typical_duration, updated_by) "
        + "values ('dummy','dummy description',30,'test')";
    MySqlQuery q = new MySqlQuery(createSql);
    q.update();
    String selectSql
        = "select task_id, name, description, typical_duration, updated_by "
        + "from siacdm.task where name = 'dummy'";
    q = new MySqlQuery(selectSql,
        (r) -> {
          Task x = new Task();
          x.setId(r.getInt("task_id"));
          x.setName(r.getString("name"));
          x.setDescription(r.getString("description"));
          x.setDuration(r.getInt("typical_duration"));
          x.setUpdatedBy(r.getString("updated_by"));
          return x;
        });
    return q.findOne();
  }

  private static void deleteDummyData() {
    String sql = "delete from siacdm.task where name = ?";
    if (dummy != null) {
      MySqlQuery q = new MySqlQuery(sql,
          (s) -> {
            s.setString(1, dummy.getName());
          });
      q.update();
    }
  }
}
