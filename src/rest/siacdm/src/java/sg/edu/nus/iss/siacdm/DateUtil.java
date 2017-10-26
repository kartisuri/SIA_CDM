package sg.edu.nus.iss.siacdm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

  public static Date toDate_from_yyyy_MM_dd_HH_mm(String value) {
    if (value == null || value.isEmpty()) {
      return null;
    }
    SimpleDateFormat sdf = new SimpleDateFormat(
      "yyyy-MM-dd HH:mm", Locale.getDefault());
    try {
      return sdf.parse(value);
    } catch (ParseException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static Date toDate_from_yyyy_MM_dd_HH_mm(
    String yyyy_MM_dd, String HH_mm) {
    if (yyyy_MM_dd == null || yyyy_MM_dd.isEmpty()) {
      return null;
    }
    if (HH_mm == null || HH_mm.isEmpty()) {
      return null;
    }
    String temp = yyyy_MM_dd + " " + HH_mm;
    SimpleDateFormat sdf = new SimpleDateFormat(
      "yyyy-MM-dd HH:mm", Locale.getDefault());
    try {
      return sdf.parse(temp);
    } catch (ParseException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static String toString_yyyy_MM_dd(Date date) {
    if (date == null) {
      return null;
    }
    SimpleDateFormat sdf = new SimpleDateFormat(
      "yyyy-MM-dd", Locale.getDefault());
    return sdf.format(date);
  }

  public static String toString_HH_mm(Date date) {
    if (date == null) {
      return null;
    }
    SimpleDateFormat sdf = new SimpleDateFormat(
      "HH:mm", Locale.getDefault());
    return sdf.format(date);
  }

  public static String toString_dayName(Date date) {
    if (date == null) {
      return null;
    }
    SimpleDateFormat sdf = new SimpleDateFormat("E", Locale.getDefault());
    return sdf.format(date);
  }
}
