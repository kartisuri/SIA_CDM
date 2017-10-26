package sg.edu.nus.iss.ft08.siacdm.model;

import org.json.JSONException;
import org.json.JSONObject;

public class UserRole {
  private String roleId;
  private String roleName;
  private String userId;
  private String userName;

  private String getRoleId() {
    return roleId;
  }

  private void setRoleId(String roleId) {
    this.roleId = roleId;
  }

  private String getRoleName() {
    return roleName;
  }

  private void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  private String getUserName() {
    return userName;
  }

  private void setUserName(String userName) {
    this.userName = userName;
  }

  public JSONObject toJson() throws JSONException {
    JSONObject json = new JSONObject();
    json.put("roleId",getRoleId());
    json.put("roleName", getRoleName());
    json.put("userId", getUserId());
    json.put("userName",getUserName());
    return json;
  }

  public static UserRole fromJson(JSONObject json) throws JSONException {
    UserRole ur = new UserRole();
    ur.setRoleId(json.getString("roleId"));
    ur.setRoleName(json.getString("roleName"));
    ur.setUserId(json.getString("userId"));
    ur.setUserName(json.getString("userName"));
    return ur;
  }

  @Override
  public String toString() {
    return userName;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof UserRole) {
      UserRole that = (UserRole) obj;
      return this.getUserId().equals(that.getUserId());
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return userId.hashCode();
  }
}
