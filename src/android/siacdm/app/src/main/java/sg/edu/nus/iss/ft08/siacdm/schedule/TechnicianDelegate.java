package sg.edu.nus.iss.ft08.siacdm.schedule;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.ft08.siacdm.Urls;
import sg.edu.nus.iss.ft08.siacdm.http.RestClient;

import sg.edu.nus.iss.ft08.siacdm.model.UserRole;

public class TechnicianDelegate extends AsyncTask<Void, Void, List<UserRole>> {

  private ScheduleDetailController controller;
  private RestClient client;

  TechnicianDelegate(ScheduleDetailController controller) {
    this.controller = controller;
    client = new RestClient();
  }

  @Override
  protected List<UserRole> doInBackground(Void... params) {
    List<UserRole> results = new ArrayList<>();
    try {
      String data = client.get(Urls.forTechnicians());
      JSONArray array = new JSONArray(data);
      for (int i = 0; i < array.length(); i++) {
        JSONObject o = array.getJSONObject(i);
        UserRole ur = UserRole.fromJson(o);
        results.add(ur);
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return results;
  }

  @Override
  protected void onPostExecute(List<UserRole> users) {
    controller.showTechnicians(users);
  }
}
