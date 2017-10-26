package sg.edu.nus.iss.ft08.siacdm.user;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.ft08.siacdm.Urls;
import sg.edu.nus.iss.ft08.siacdm.http.RestClient;
import sg.edu.nus.iss.ft08.siacdm.model.User;

public class FindAllDelegate extends AsyncTask<User, Void, List<User>> {
    private UsersController controller;
    private RestClient client;

    FindAllDelegate(UsersController controller){
        this.controller=controller;
        client = new RestClient();
    }

    @Override
    protected List<User> doInBackground(User... params) {
        List<User> results = new ArrayList<>();
        try {
            String data = client.get(Urls.forUsers());
            JSONArray array = new JSONArray(data);
            for (int i = 0; i < array.length(); i++) {
                JSONObject o = array.getJSONObject(i);
                User u = User.fromJson(o);
                results.add(u);
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return results;
    }

    @Override
    protected void onPostExecute(List<User> users) {
        controller.showUsers(users);
    }
}
