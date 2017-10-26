package sg.edu.nus.iss.ft08.siacdm.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Schedule {
  private int id;
  private int taskId;
  private String start;
  private String end;
  private String technicianId;
  private String taskName;
  private String technicianName;
  private String remarks;
  private String updatedBy;
  private String startDate;
  private String startTime;
  private String endDate;
  private String endTime;
  private String dayName;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getTaskId() {
    return taskId;
  }

  public void setTaskId(int taskId) {
    this.taskId = taskId;
  }

  public String getStart() {
    return start;
  }

  public void setStart(String start) {
    this.start = start;
  }

  private String getEnd() {
    return end;
  }

  public void setEnd(String end) {
    this.end = end;
  }

  public String getTechnicianId() {
    return technicianId;
  }

  public void setTechnicianId(String technicianId) {
    this.technicianId = technicianId;
  }

  public String getTaskName() {
    return taskName;
  }

  private void setTaskName(String taskName) {
    this.taskName = taskName;
  }

  public String getTechnicianName() {
    return technicianName;
  }

  private void setTechnicianName(String technicianName) {
    this.technicianName = technicianName;
  }

  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  private String getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(String updatedBy) {
    this.updatedBy = updatedBy;
  }

  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  public String getDayName() {
    return dayName;
  }

  private void setDayName(String dayName) {
    this.dayName = dayName;
  }

  public JSONObject toJson() throws JSONException {
    JSONObject json = new JSONObject();
    json.put("id", getId());
    json.put("taskId", getTaskId());
    json.put("technicianId", getTechnicianId());
    json.put("remarks", getRemarks());
    json.put("start", dateTimeJoiner(getStartDate(), getStartTime()));
    json.put("end", dateTimeJoiner(getEndDate(), getEndTime()));
    json.put("startDate", getStartDate());
    json.put("startTime", getStartTime());
    json.put("endDate", getEndDate());
    json.put("endTime", getEndTime());
    json.put("updatedBy", getUpdatedBy());
    return json;
  }

  public static Schedule fromJson(JSONObject json) throws JSONException {
    Schedule s = new Schedule();
    if (json.has("id"))
      s.setId(json.getInt("id"));
    if (json.has("taskId"))
      s.setTaskId(json.getInt("taskId"));
    if (json.has("technicianId"))
      s.setTechnicianId(json.getString("technicianId"));
    if (json.has("remarks"))
      s.setRemarks(json.getString("remarks"));
    if (json.has("taskName"))
      s.setTaskName(json.getString("taskName"));
    if (json.has("technicianName"))
      s.setTechnicianName(json.getString("technicianName"));
    if (json.has("updatedBy"))
      s.setUpdatedBy(json.getString("updatedBy"));
    if (json.has("dayName"))
      s.setDayName(json.getString("dayName"));
    if (json.has("start"))
      s.setStart(json.getString("start"));
      s.setStartDate(dateSplitter(json.getString("start")));
      s.setStartTime(timeSplitter(json.getString("start")));
    if (json.has("end"))
      s.setEnd(json.getString("end"));
      s.setEndDate(dateSplitter(json.getString("end")));
      s.setEndTime(timeSplitter(json.getString("end")));
    return s;
  }

  private static String dateSplitter(String date){
    String[] parts = date.split("T");
    return parts[0];
  }

  private static String timeSplitter(String date){
    String[] parts = date.split("T");
    return parts[1].split("\\+")[0].substring(0, 5);
  }

  private static String dateTimeJoiner(String date, String time){
    return date + "T" + time + ":00+08:00";
  }
}



