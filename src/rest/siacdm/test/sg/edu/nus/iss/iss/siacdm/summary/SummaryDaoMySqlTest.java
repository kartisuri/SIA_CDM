package sg.edu.nus.iss.iss.siacdm.summary;

import sg.edu.nus.iss.siacdm.summary.SummaryDaoMySql;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sg.edu.nus.iss.siacdm.model.Summary;

public class SummaryDaoMySqlTest {

  public SummaryDaoMySqlTest() {
  }

  @BeforeClass
  public static void setUpClass() {
  }

  @AfterClass
  public static void tearDownClass() {
  }

  @Before
  public void setUp() {
  }

  @After
  public void tearDown() {
  }

  @Test
  public void testFindOne() {
    SummaryDaoMySql dao = new SummaryDaoMySql();
    Summary s = dao.findOne();
    assertNotNull(s);
    assertTrue(s.getTotalUsers() >= 0);
    assertTrue(s.getTotalSlots() >= 0);
    assertTrue(s.getTotalTasks() >= 0);
  }
}
