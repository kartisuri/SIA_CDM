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
import sg.edu.nus.iss.ft08.siacdm.model.User;

public class FindAllUserDelegate extends AsyncTask<User, Void, List<Schedule>> {
  private SchedulesController controller;
  private RestClient client;

  FindAllUserDelegate(SchedulesController controller) {
    this.controller = controller;
    client = new RestClient();
  }

  @Override
  protected List<Schedule> doInBackground(User... params) {
    User user = params[0];
    List<Schedule> results = new ArrayList<>();
    try {
      String data = client.get(Urls.forSchedulesUser(user.getUserId()));
      JSONArray array = new JSONArray(data);
      for (int i = 0; i < array.length(); i++) {
        JSONObject o = array.getJSONObject(i);
        Schedule s = Schedule.fromJson(o);
        results.add(s);
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return results;
  }

  @Override
  protected void onPostExecute(List<Schedule> slots) {
    controller.showUserSchedules(slots);
  }
}
