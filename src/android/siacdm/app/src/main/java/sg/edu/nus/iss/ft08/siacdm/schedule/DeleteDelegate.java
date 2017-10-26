package sg.edu.nus.iss.ft08.siacdm.schedule;

import android.os.AsyncTask;

import org.json.JSONException;

import sg.edu.nus.iss.ft08.siacdm.Urls;
import sg.edu.nus.iss.ft08.siacdm.http.RestClient;
import sg.edu.nus.iss.ft08.siacdm.model.Schedule;

public class DeleteDelegate extends AsyncTask<Schedule, Void, Void> {
  private ScheduleDetailController controller;
  private RestClient client;

  DeleteDelegate(ScheduleDetailController controller) {
    this.controller = controller;
    client = new RestClient();
  }

  @Override
  protected Void doInBackground(Schedule... params) {
    Schedule schedule = params[0];
    try {
      String data = client.delete(Urls.forSchedule(schedule.getId()));
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
