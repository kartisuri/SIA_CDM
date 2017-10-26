package sg.edu.nus.iss.ft08.siacdm.task;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import sg.edu.nus.iss.ft08.siacdm.Urls;
import sg.edu.nus.iss.ft08.siacdm.http.RestClient;
import sg.edu.nus.iss.ft08.siacdm.model.Task;

public class UpdateDelegate extends AsyncTask<Task, Void, Task> {

  private TaskDetailController controller;
  private RestClient client;

  UpdateDelegate(TaskDetailController controller) {
    this.controller = controller;
    client = new RestClient();
  }

  @Override
  protected Task doInBackground(Task... params) {
    Task task = params[0];
    Task result = null;
    try {
      String data = client.post(Urls.forTask(), task.toJson());
      JSONObject json = new JSONObject(data);
      result = Task.fromJson(json);
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return result;
  }

  @Override
  protected void onPostExecute(Task task) {
    controller.showSaveSuccess(task);
  }
}
