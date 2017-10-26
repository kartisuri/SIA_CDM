package sg.edu.nus.iss.ft08.siacdm.schedule;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.ft08.siacdm.Urls;
import sg.edu.nus.iss.ft08.siacdm.http.RestClient;
import sg.edu.nus.iss.ft08.siacdm.model.Schedule;

public class UpdateDelegate extends AsyncTask<Schedule, Void, Schedule> {
  private ScheduleDetailController controller;
  private RestClient client;

  UpdateDelegate(ScheduleDetailController controller) {
    this.controller = controller;
    client = new RestClient();
  }

  @Override
  protected Schedule doInBackground(Schedule... params) {
    Schedule schedule = params[0];
    Schedule result = null;
    List<Schedule> overlap = new ArrayList<>();
    try {
      String data = client.post(Urls.forOverlap(), schedule.toJson());
      JSONArray array = new JSONArray(data);
      for (int i = 0; i < array.length(); i++) {
        JSONObject o = array.getJSONObject(i);
        Schedule s = Schedule.fromJson(o);
        overlap.add(s);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    if (!isOverlapped(overlap, schedule)) {
      try {
        String data = client.post(Urls.forSchedule(), schedule.toJson());
        JSONObject json = new JSONObject(data);
        result = Schedule.fromJson(json);
      } catch (JSONException e) {
        e.printStackTrace();
      }
    }
    return result;
  }

  private boolean isOverlapped(List<Schedule> overlap, Schedule schedule) {
    boolean hasOverlap = false;
    if (overlap.size() == 0) {
      hasOverlap = false;
    } else if (overlap.size() > 1) {
      hasOverlap = true;
    } else if (overlap.size() == 1) {
      boolean isSameRecord = overlap.get(0).getId() == schedule.getId();
      hasOverlap = !isSameRecord;
    }
    return hasOverlap;
  }

  @Override
  protected void onPostExecute(Schedule schedule) {
    controller.showSaveSuccess(schedule);
  }
}
