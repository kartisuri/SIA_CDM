package sg.edu.nus.iss.ft08.siacdm.task;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.ft08.siacdm.Urls;
import sg.edu.nus.iss.ft08.siacdm.http.RestClient;
import sg.edu.nus.iss.ft08.siacdm.model.Task;

public class FindAllDelegate extends AsyncTask<Task, Void, List<Task>> {
  private TasksController controller;
  private RestClient client;

  FindAllDelegate(TasksController controller) {
    this.controller = controller;
    client = new RestClient();
  }

  @Override
  protected List<Task> doInBackground(Task... params) {
    List<Task> results = new ArrayList<>();
    try {
      String data = client.get(Urls.forTasks());
      JSONArray array = new JSONArray(data);
      for (int i = 0; i < array.length(); i++) {
        JSONObject o = array.getJSONObject(i);
        Task r = Task.fromJson(o);
        results.add(r);
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return results;
  }

  @Override
  protected void onPostExecute(List<Task> tasks) {
    controller.showTasks(tasks);
  }
}
