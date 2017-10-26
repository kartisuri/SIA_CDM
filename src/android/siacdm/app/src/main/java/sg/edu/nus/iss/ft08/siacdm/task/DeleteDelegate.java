package sg.edu.nus.iss.ft08.siacdm.task;

import android.os.AsyncTask;

import org.json.JSONException;

import sg.edu.nus.iss.ft08.siacdm.Urls;
import sg.edu.nus.iss.ft08.siacdm.http.RestClient;
import sg.edu.nus.iss.ft08.siacdm.model.Task;

public class DeleteDelegate extends AsyncTask<Task, Void, Void> {
  private TaskDetailController controller;
  private RestClient client;

  DeleteDelegate(TaskDetailController controller) {
    this.controller = controller;
    client = new RestClient();
  }

  @Override
  protected Void doInBackground(Task... params) {
    Task task = params[0];
    try {
      client.delete(Urls.forTask(task.getId()));
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  protected void onPostExecute(Void aVoid) {
    controller.showDeleteSuccess();
  }
}
