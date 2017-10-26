package sg.edu.nus.iss.ft08.siacdm.schedule;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public abstract class ScheduleUtil {

  public static String toString_yyyy_MM_dd_HH_mm_ss(Date date) {
    if (date == null)
      return null;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    return sdf.format(date);
  }

  public static String toString_yyyy_MM_dd(Date date) {
    if (date == null)
      return null;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    return sdf.format(date);
  }

  public static Date toDate_from_yyyy_MM_dd_HH_mm_ss(String value) {
    if (value == null || value.isEmpty())
      return null;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddTHH:mm:ss", Locale.getDefault());
    try {
      return sdf.parse(value);
    } catch (ParseException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static Calendar getDate_from_week_number(int weekNo){
    Calendar c = Calendar.getInstance();
    c.set(Calendar.WEEK_OF_YEAR, weekNo);
    c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
    return c;
  }

  public static Date getToday() {
    Calendar c = Calendar.getInstance();
    c.set(Calendar.HOUR_OF_DAY, 0);
    c.set(Calendar.MINUTE, 0);
    c.set(Calendar.SECOND, 0);
    return c.getTime();
  }

  public static String toString_dd_MMM_yyyy(int year, int month, int dayOfMonth) {
    Calendar c = Calendar.getInstance();
    c.set(Calendar.YEAR, year);
    c.set(Calendar.MONTH, month);
    c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

    return toString_yyyy_MM_dd_HH_mm_ss(c.getTime());
  }

  public static String toString_hh_mm_a(int hourOfDay, int minute) {
    Calendar c = Calendar.getInstance();
    c.set(c.get(Calendar.YEAR),
        c.get(Calendar.MONTH),
        c.get(Calendar.DAY_OF_MONTH),
        hourOfDay,
        minute);

    return toString_hh_mm_a(c.getTime());
  }

  public static String toString_d_MMM_yyyy_H_mm(String date) {
    if (date == null)
      return null;

    SimpleDateFormat sdf = new SimpleDateFormat("d-MMM-yyyy H:mm a", Locale.getDefault());
    return sdf.format(date);
  }

  public static Date toDate_from_d_MMM_yyyy_H_mm(String value) {
    if (value == null || value.isEmpty())
      return null;

    SimpleDateFormat sdf = new SimpleDateFormat("d-MMM-yyyy H:mm", Locale.getDefault());
    try {
      return sdf.parse(value);
    } catch (ParseException e) {
      e.printStackTrace();
      return null;
    }
  }


  public static Date toTime_from_hh_mm_a(String value) {
    if (value == null || value.isEmpty())
      return null;

    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.getDefault());
    try {
      return sdf.parse(value);
    } catch (ParseException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static String toString_hh_mm_a(Date date) {
    if (date == null)
      return null;

    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.getDefault());
    return sdf.format(date);
  }

  public static Date setCurrentDate(Date time) {
    Calendar setCurrentTime = Calendar.getInstance();
    setCurrentTime.setTime(time);
    setCurrentTime.set(Calendar.getInstance().get(Calendar.YEAR),
        Calendar.getInstance().get(Calendar.MONTH),
        Calendar.getInstance().get(Calendar.DATE));
    return setCurrentTime.getTime();
  }

}
