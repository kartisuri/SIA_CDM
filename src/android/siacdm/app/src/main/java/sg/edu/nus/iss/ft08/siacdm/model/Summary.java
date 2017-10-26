package sg.edu.nus.iss.ft08.siacdm.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Summary {
  private int totalUsers;
  private int totalSlots;
  private int totalTasks;

  public int getTotalUsers() {
    return totalUsers;
  }

  private void setTotalUsers(int totalUsers) {
    this.totalUsers = totalUsers;
  }

  public int getTotalSlots() {
    return totalSlots;
  }

  private void setTotalSlots(int totalSlots) {
    this.totalSlots = totalSlots;
  }

  public int getTotalTasks() {
    return totalTasks;
  }

  private void setTotalTasks(int totalTasks) {
    this.totalTasks = totalTasks;
  }

  public static Summary fromJson(JSONObject json) throws JSONException {
    Summary s = new Summary();
    s.setTotalUsers(json.getInt("totalUsers"));
    s.setTotalSlots(json.getInt("totalSlots"));
    s.setTotalTasks(json.getInt("totalTasks"));
    return s;
  }
}
