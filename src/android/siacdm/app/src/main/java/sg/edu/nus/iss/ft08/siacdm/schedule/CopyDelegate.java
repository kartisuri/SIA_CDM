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

public class CopyDelegate extends AsyncTask<List<String>, Void, Boolean> {
  private SchedulesController controller;
  private RestClient client;

  CopyDelegate(SchedulesController controller) {
    this.controller = controller;
    client = new RestClient();
  }

  @Override
  protected final Boolean doInBackground(List<String>... params) {
    List<String> string = params[0];
    Boolean overlapFlag = true;
    int sourceWeekNumber = Integer.parseInt(string.get(2));
    int targetWeekNumber = Integer.parseInt(string.get(3));
    Schedule example = new Schedule();
    example.setStart(string.get(0));
    example.setEnd(string.get(1));
    List<Schedule> overlap = new ArrayList<>();
    try {
      String data = client.post(Urls.forOverlap(), example.toJson());
      JSONArray array = new JSONArray(data);
      for (int i = 0; i < array.length(); i++) {
        JSONObject o = array.getJSONObject(i);
        Schedule s = Schedule.fromJson(o);
        overlap.add(s);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (overlap.size() == 0) {
      overlapFlag = false;
      try {
        client.get(Urls.forCopyWeekSchedules(sourceWeekNumber, targetWeekNumber));
      } catch (JSONException e) {
        e.printStackTrace();
      }
    }
    return overlapFlag;
  }

  @Override
  protected void onPostExecute(Boolean overlap) {
    controller.showMessage(overlap);
  }
}
