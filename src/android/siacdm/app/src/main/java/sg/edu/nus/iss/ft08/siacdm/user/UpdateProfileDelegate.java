package sg.edu.nus.iss.ft08.siacdm.user;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import sg.edu.nus.iss.ft08.siacdm.Urls;
import sg.edu.nus.iss.ft08.siacdm.http.RestClient;
import sg.edu.nus.iss.ft08.siacdm.model.User;

public class UpdateProfileDelegate extends AsyncTask<User, Void, User> {
    private MyProfileController controller;
    private RestClient client;

    UpdateProfileDelegate(MyProfileController controller) {
        this.controller = controller;
        client = new RestClient();
    }

    @Override
    protected User doInBackground(User... params) {
        User user= params[0];
        User result = null;
        try {
            String data = client.post(Urls.forUser(), user.toJsonCRUD());
            JSONObject json = new JSONObject(data);
            result = User.fromJson(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(User user) {
        controller.showSaveSuccess(user);
    }
}
