package sg.edu.nus.iss.ft08.siacdm;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
  @Test
  public void addition_isCorrect() throws Exception {
    assertEquals(4, 2 + 2);

    Calendar c0 = Calendar.getInstance();
    int weekNo = c0.get(Calendar.WEEK_OF_YEAR);
    System.out.println(weekNo);


    c0.setFirstDayOfWeek(Calendar.MONDAY);
    weekNo = c0.get(Calendar.WEEK_OF_YEAR);
    System.out.println(weekNo);

    c0.set(2017, Calendar.OCTOBER, 3);
    weekNo = c0.get(Calendar.WEEK_OF_YEAR);
    System.out.println(weekNo);


    Calendar c = Calendar.getInstance();
    c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
    c.set(Calendar.WEEK_OF_YEAR, weekNo);
    c.set(Calendar.HOUR_OF_DAY, 0);
    c.set(Calendar.MINUTE, 0);
    c.set(Calendar.SECOND, 0);

    Date d = c.getTime();
    System.out.println(d);

    c.add(Calendar.DATE, 6);
    c.set(Calendar.HOUR_OF_DAY, 23);
    c.set(Calendar.MINUTE, 59);
    c.set(Calendar.SECOND, 59);

    d = c.getTime();
    System.out.println(d);

  }

}