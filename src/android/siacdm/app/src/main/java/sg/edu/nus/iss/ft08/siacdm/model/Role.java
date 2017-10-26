package sg.edu.nus.iss.ft08.siacdm.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Role {
    private String role_id;

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public JSONObject toJson() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("roleId", getRole_id());
        return json;
    }

    public static Role fromJson(JSONObject json) throws JSONException {
        Role r = new Role();
        r.setRole_id(json.getString("roleId"));
        return r;
    }
}
