package sg.edu.nus.iss.ft08.siacdm.user;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import sg.edu.nus.iss.ft08.siacdm.Urls;
import sg.edu.nus.iss.ft08.siacdm.http.RestClient;
import sg.edu.nus.iss.ft08.siacdm.model.User;

public class DeleteDelegate extends AsyncTask<User, Void, Boolean> {
    private UserDetailController controller;
    private RestClient client;

    DeleteDelegate(UserDetailController controller){
        this.controller = controller;
        client = new RestClient();
    }

    @Override
    protected Boolean doInBackground(User... params) {
        User user = params[0];
        User assignedUser;
        boolean result;
        try {
            String data = client.get(Urls.forassignedUser(user.getUserId()));
            JSONObject json = new JSONObject(data);
            assignedUser = User.fromJson(json);
            if(assignedUser != null){
                result = false;
            }else {
                client.delete(Urls.forUser(user.getUserId()));
                result = true;
            }
        } catch (JSONException e) {
            try {
                client.delete(Urls.forUser(user.getUserId()));
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
            result = true;
        }
        return result;
    }

    @Override
    protected void onPostExecute(Boolean status) {
       controller.showDeleteSuccess(status);
    }
}
