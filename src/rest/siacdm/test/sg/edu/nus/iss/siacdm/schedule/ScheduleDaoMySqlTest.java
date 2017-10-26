package sg.edu.nus.iss.siacdm.schedule;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sg.edu.nus.iss.siacdm.model.Schedule;
import sg.edu.nus.iss.siacdm.schedule.ScheduleDaoMySql;
import sg.edu.nus.iss.siacdm.util.MySqlQuery;

public class ScheduleDaoMySqlTest {
  private static Schedule dummy;

  public ScheduleDaoMySqlTest() {
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
    ScheduleDaoMySql dao = new ScheduleDaoMySql();
    List<Schedule> results = dao.findAll();
    assertNotNull(results);
    assertTrue(results.stream().anyMatch(x -> x.getId() == dummy.getId()));
  }

  private void new_slot_start_end_is_in_btw_dummy_start_end() {
    /* dummy start time - 18:00:00
    dummy end time - 19:00:00
    new start time - 18:30:00
    new end time - 19:30:00
    findOverlap() should return list with dummy */
    Schedule newSlot = new Schedule();
    Date start, end;
    List<Schedule> results;
    Calendar cal = Calendar.getInstance();
    ScheduleDaoMySql dao = new ScheduleDaoMySql();
    cal.set(2010, 8, 26, 18, 30, 0);
    start = cal.getTime();
    cal.set(2010, 8, 26, 19, 30, 0);
    end = cal.getTime();
    newSlot.setStart(start);
    newSlot.setEnd(end);
    newSlot.setTechnicianId("dummyTechnician");
    results = dao.findOverlap(newSlot);
    assertNotEquals(results.size(), 0);
  }

  private void dummy_start_end_is_in_btw_new_slot_start_end() {
    /* dummy start time - 18:00:00
    dummy end time - 19:00:00
    new start time - 17:30:00
    new end time - 18:30:00
    findOverlap() should return list with dummy */
    Schedule newSlot = new Schedule();
    Date start, end;
    List<Schedule> results;
    Calendar cal = Calendar.getInstance();
    ScheduleDaoMySql dao = new ScheduleDaoMySql();
    cal.set(2010, 8, 26, 17, 30, 0);
    start = cal.getTime();
    cal.set(2010, 8, 26, 18, 30, 0);
    end = cal.getTime();
    newSlot.setStart(start);
    newSlot.setEnd(end);
    newSlot.setTechnicianId("dummyTechnician");
    results = dao.findOverlap(newSlot);
    assertNotEquals(results.size(), 0);
  }

  private void new_slot_start_end_equals_dummy_start_end() {
    /* dummy start time - 18:00:00
    dummy end time - 19:00:00
    new start time - 18:00:00
    new end time - 19:00:00
    findOverlap() should return list with dummy */
    Schedule newSlot = new Schedule();
    Date start, end;
    List<Schedule> results;
    Calendar cal = Calendar.getInstance();
    ScheduleDaoMySql dao = new ScheduleDaoMySql();
    cal.set(2010, 8, 26, 18, 00, 0);
    start = cal.getTime();
    cal.set(2010, 8, 26, 19, 00, 0);
    end = cal.getTime();
    newSlot.setStart(start);
    newSlot.setEnd(end);
    newSlot.setTechnicianId("dummyTechnician");
    results = dao.findOverlap(newSlot);
    assertNotEquals(results.size(), 0);
  }

  private void new_slot_start_end_inside_dummy_start_end() {
    /* dummy start time - 18:00:00
    dummy end time - 19:00:00
    new start time - 18:15:00
    new end time - 18:45:00
    findOverlap() should return list with dummy */
    Schedule newSlot = new Schedule();
    Date start, end;
    List<Schedule> results;
    Calendar cal = Calendar.getInstance();
    ScheduleDaoMySql dao = new ScheduleDaoMySql();
    cal.set(2010, 8, 26, 18, 15, 0);
    start = cal.getTime();
    cal.set(2010, 8, 26, 18, 45, 0);
    end = cal.getTime();
    newSlot.setStart(start);
    newSlot.setEnd(end);
    newSlot.setTechnicianId("dummyTechnician");
    results = dao.findOverlap(newSlot);
    assertNotEquals(results.size(), 0);
  }

  private void dummy_start_end_inside_new_slot_start_end() {
    /* dummy start time - 18:00:00
    dummy end time - 19:00:00
    new start time - 17:30:00
    new end time - 19:30:00
    findOverlap() should return list with dummy */
    Schedule newSlot = new Schedule();
    Date start, end;
    List<Schedule> results;
    Calendar cal = Calendar.getInstance();
    ScheduleDaoMySql dao = new ScheduleDaoMySql();
    cal.set(2010, 8, 26, 17, 30, 0);
    start = cal.getTime();
    cal.set(2010, 8, 26, 19, 30, 0);
    end = cal.getTime();
    newSlot.setStart(start);
    newSlot.setEnd(end);
    newSlot.setTechnicianId("dummyTechnician");
    results = dao.findOverlap(newSlot);
    assertNotEquals(results.size(), 0);
  }

  private void new_slot_start_end_not_overlap_dummy_start_end() {
    /* dummy start time - 18:00:00
    dummy end time - 19:00:00
    new start time - 19:00:00
    new end time - 20:00:00
    findOverlap() should return empty list */
    Schedule newSlot = new Schedule();
    Date start, end;
    List<Schedule> results;
    Calendar cal = Calendar.getInstance();
    ScheduleDaoMySql dao = new ScheduleDaoMySql();
    cal.set(2010, 8, 26, 19, 01);
    start = cal.getTime();
    cal.set(2010, 8, 26, 20, 00);
    end = cal.getTime();
    newSlot.setStart(start);
    newSlot.setEnd(end);
    newSlot.setTechnicianId("dummyTechnician");
    results = dao.findOverlap(newSlot);
    assertEquals(results.size(), 0);
  }

  @Test
  public void testFindOverlap() {
    new_slot_start_end_equals_dummy_start_end();
    dummy_start_end_inside_new_slot_start_end();
    new_slot_start_end_inside_dummy_start_end();
    new_slot_start_end_is_in_btw_dummy_start_end();
    dummy_start_end_is_in_btw_new_slot_start_end();
    new_slot_start_end_not_overlap_dummy_start_end();
  }

  private void dummy_inside_range() {
    /* dummy start time - 18:00:00
    dummy end time - 19:00:00
    new start time - 17:30:00
    new end time - 19:30:00
    findRange() should return list with dummy */
    Schedule newSlot = new Schedule();
    Date start, end;
    List<Schedule> results;
    Calendar cal = Calendar.getInstance();
    ScheduleDaoMySql dao = new ScheduleDaoMySql();
    cal.set(2010, 8, 26, 17, 30, 0);
    start = cal.getTime();
    cal.set(2010, 8, 26, 19, 30, 0);
    end = cal.getTime();
    newSlot.setStart(start);
    newSlot.setEnd(end);
    results = dao.findRange(newSlot);
    assertNotEquals(results.size(), 0);
  }

  private void dummy_outside_range() {
    /* dummy start time - 18:00:00
    dummy end time - 19:00:00
    new start time - 16:30:00
    new end time - 17:30:00
    findRange() should return empty list */
    Schedule newSlot = new Schedule();
    Date start, end;
    List<Schedule> results;
    Calendar cal = Calendar.getInstance();
    ScheduleDaoMySql dao = new ScheduleDaoMySql();
    cal.set(2010, 8, 26, 16, 30, 0);
    start = cal.getTime();
    cal.set(2010, 8, 26, 17, 30, 0);
    end = cal.getTime();
    newSlot.setStart(start);
    newSlot.setEnd(end);
    results = dao.findRange(newSlot);
    assertEquals(results.size(), 0);
  }

  @Test
  public void testFindRange() {
    dummy_inside_range();
    dummy_outside_range();
  }

  private void find_all_schedule_based_on_task_id_true() {
    Schedule newSlot = new Schedule();
    int task_id = 1;
    List<Schedule> results;
    ScheduleDaoMySql dao = new ScheduleDaoMySql();
    newSlot.setTaskId(task_id);
    results = dao.findAllSchedulesTask(newSlot);
    assertNotEquals(results.size(), 0);
  }

  private void find_all_schedule_based_on_task_id_false() {
    Schedule newSlot = new Schedule();
    int task_id = 2;
    List<Schedule> results;
    ScheduleDaoMySql dao = new ScheduleDaoMySql();
    newSlot.setTaskId(task_id);
    results = dao.findAllSchedulesTask(newSlot);
    assertEquals(results.size(), 0);
  }

  @Test
  public void testFindAllSchedulesTask() {
    find_all_schedule_based_on_task_id_false();
    find_all_schedule_based_on_task_id_true();
  }

  private void find_all_schedule_based_on_user_id_true() {
    Schedule newSlot = new Schedule();
    String user_id = "dummyTechnician";
    List<Schedule> results;
    ScheduleDaoMySql dao = new ScheduleDaoMySql();
    newSlot.setTechnicianId(user_id);
    results = dao.findAllSchedulesUser(newSlot);
    assertNotEquals(results.size(), 0);
  }

  private void find_all_schedule_based_on_user_id_false() {
    Schedule newSlot = new Schedule();
    String user_id = "dummy";
    List<Schedule> results;
    ScheduleDaoMySql dao = new ScheduleDaoMySql();
    newSlot.setTechnicianId(user_id);
    results = dao.findAllSchedulesUser(newSlot);
    assertEquals(results.size(), 0);
  }

  @Test
  public void testFindAllSchedulesUser() {
    find_all_schedule_based_on_user_id_false();
    find_all_schedule_based_on_user_id_true();
  }

  @Test
  public void testFindOne() {
    ScheduleDaoMySql dao = new ScheduleDaoMySql();
    Schedule result = dao.findOne(dummy);
    assertNotNull(result);
    assertEquals(dummy.getId(), result.getId());
    assertEquals(dummy.getTaskId(), result.getTaskId());
    assertEquals(dummy.getTechnicianId(), result.getTechnicianId());
    assertEquals(dummy.getRemarks(), result.getRemarks());
    assertEquals(dummy.getUpdatedBy(), result.getUpdatedBy());
    assertEquals(to_dd_mm_yyyy_hh_mm(dummy.getStart()),
      to_dd_mm_yyyy_hh_mm(result.getStart()));
    assertEquals(to_dd_mm_yyyy_hh_mm(dummy.getEnd()),
      to_dd_mm_yyyy_hh_mm(result.getEnd()));
  }

  @Test
  public void testUpdate() {
    Calendar cal = Calendar.getInstance();
    cal.set(2010, 9, 26, 18, 0, 0);
    Date start = cal.getTime();
    cal.set(2010, 9, 26, 19, 0, 0);
    Date end = cal.getTime();
    Schedule expected = dummy;
    expected.setTaskId(2);
    expected.setTechnicianId("new_technician");
    expected.setRemarks("new_remarks");
    expected.setStart(start);
    expected.setEnd(end);
    ScheduleDaoMySql dao = new ScheduleDaoMySql();
    Schedule result = dao.update(expected);
    assertNotNull(result);
    assertEquals(expected.getId(), result.getId());
    assertEquals(expected.getTaskId(), result.getTaskId());
    assertEquals(expected.getTechnicianId(), result.getTechnicianId());
    assertEquals(expected.getRemarks(), result.getRemarks());
    assertEquals(expected.getUpdatedBy(), result.getUpdatedBy());
    assertEquals(to_dd_mm_yyyy_hh_mm(expected.getStart()),
      to_dd_mm_yyyy_hh_mm(result.getStart()));
    assertEquals(to_dd_mm_yyyy_hh_mm(expected.getEnd()),
      to_dd_mm_yyyy_hh_mm(result.getEnd()));
  }

  @Test
  public void testCreate() {
    deleteDummyData();
    ScheduleDaoMySql dao = new ScheduleDaoMySql();
    Schedule result = dao.create(dummy);
    assertNotNull(result);
    assertEquals(dummy.getTaskId(), result.getTaskId());
    assertEquals(dummy.getTechnicianId(), result.getTechnicianId());
    assertEquals(dummy.getRemarks(), result.getRemarks());
    assertEquals(dummy.getUpdatedBy(), result.getUpdatedBy());
    assertEquals(to_dd_mm_yyyy_hh_mm(dummy.getStart()),
      to_dd_mm_yyyy_hh_mm(result.getStart()));
    assertEquals(to_dd_mm_yyyy_hh_mm(dummy.getEnd()),
      to_dd_mm_yyyy_hh_mm(result.getEnd()));
  }

  @Test
  public void testDelete() {
    ScheduleDaoMySql dao = new ScheduleDaoMySql();
    dao.delete(dummy);
    Schedule result = dao.findOne(dummy);
    assertNull(result);
  }
  
  @Test
  public void testCopy() {
    ScheduleDaoMySql dao = new ScheduleDaoMySql();    
    Schedule newSlot = new Schedule();
    int interval = 7;
    Date start, end;
    start = dummy.getStart();
    end = dummy.getEnd();
    Calendar cal = Calendar.getInstance();
    cal.setTime(start);
    cal.add(Calendar.DATE, interval);
    start = cal.getTime();
    cal.setTime(end);
    cal.add(Calendar.DATE, interval);
    end = cal.getTime();
    newSlot.setStart(start);
    newSlot.setEnd(end);
    assertEquals(dao.findRange(newSlot).size(), 0);
    dao.copy(dummy, 7);
    assertNotEquals(dao.findRange(newSlot).size(), 0);
  }

  private static Schedule createDummyData() {
    String createSql
      = "insert into siacdm.schedule\n"
      + "(task_id, technician_id, remarks, updated_by, start, end)\n"
      + "values (1,'dummyPresenter','dummyRemarks','schedule_tester',\n"
      + "'2010-09-26 18:00:00','2010-09-26 19:00:00');";
    MySqlQuery q = new MySqlQuery(createSql);
    q.update();
    String selectSql = "select s.slot_id, s.task_id, t.name as task_name,\n"
      + "s.technician_id, te.name as technician_name, s.remarks,\n"
      + "s.start, s.end, s.updated_by\n"
      + "from siacdm.schedule s left join siacdm.task t\n"
      + "on t.task_id = s.task_id left join siacdm.user te\n"
      + "on te.id = s.technician_id where\n"
      + "s.updated_by = 'schedule_tester';";
    q = new MySqlQuery(selectSql,
      (r) -> {
        Schedule p = new Schedule();
        p.setId(r.getInt("slot_id"));
        p.setTechnicianId(r.getString("technician_id"));
        p.setRemarks(r.getString("remarks"));
        p.setTaskId(r.getInt("task_id"));
        p.setStart(r.getTimestamp("start"));
        p.setEnd(r.getTimestamp("end"));
        p.setUpdatedBy(r.getString("updated_by"));
        p.setTaskName(r.getString("task_name"));
        p.setTechnicianName(r.getString("technician_name"));
        return p;
      });
    return q.findOne();
  }

  private static void deleteDummyData() {
    String sql = "delete from siacdm.schedule where updated_by = ? ";
    if (dummy != null) {
      MySqlQuery q = new MySqlQuery(sql,
        (s) -> {
          s.setString(1, dummy.getUpdatedBy());
        });
      q.update();
    }
  }

  private String to_dd_mm_yyyy_hh_mm(Date value) {
    SimpleDateFormat sdf = new SimpleDateFormat(
      "dd-MM-yyyy HH:mm", Locale.getDefault());
    return sdf.format(value);
  }
}
