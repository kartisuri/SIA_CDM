package sg.edu.nus.iss.ft08.siacdm.authenticate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import org.json.JSONException;

import sg.edu.nus.iss.ft08.siacdm.Constants;
import sg.edu.nus.iss.ft08.siacdm.ControlFactory;
import sg.edu.nus.iss.ft08.siacdm.MainController;
import sg.edu.nus.iss.ft08.siacdm.SiaCdmBaseController;
import sg.edu.nus.iss.ft08.siacdm.model.User;

public class LoginController implements SiaCdmBaseController {
  private LoginActivity loginActivity;

  public void onDisplay(LoginActivity loginActivity) {
    this.loginActivity = loginActivity;
  }

  void login(String userId, String password) {
    loginActivity.showLoading();
    User user = new User();
    user.setUserId(userId);
    user.setPassword(password);
    LoginDelegate delegate = new LoginDelegate(this);
    delegate.execute(user);
  }

  public void logout() {
    removeUserFromSharedPreferences();
    Intent intent = new Intent(MainController.getApp(), LoginActivity.class);
    MainController.displayScreen(intent);
  }

  void loggedIn(User user) {
    loginActivity.hideLoading();
    if (!user.isAuthenticated()) {
      loginActivity.showLoginFailure();
      return;
    }
    keepUserInSharedPreferences(user);
    ControlFactory.getMainController().startUseCase();
  }

  private void keepUserInSharedPreferences(User user) {
    SharedPreferences preferences = loginActivity.getSharedPreferences(Constants.APP_PREFERENCES, Context.MODE_PRIVATE);
    try {
      preferences
          .edit()
          .putString(Constants.KEY_USER_ID, user.getUserId())
          .putString(Constants.KEY_USER_NAME, user.getFullName())
          .putString(Constants.KEY_USER_INFO, user.toJsonCRUD().toString())
          .apply();
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

  private void removeUserFromSharedPreferences() {
    SharedPreferences preferences = loginActivity.getSharedPreferences(Constants.APP_PREFERENCES, Context.MODE_PRIVATE);
    preferences
        .edit()
        .remove(Constants.KEY_USER_ID)
        .remove(Constants.KEY_USER_NAME)
        .remove(Constants.KEY_USER_INFO)
        .apply();
  }

  @Override
  public void startUseCase() {
    Intent intent = new Intent(MainController.getApp(), LoginActivity.class);
    MainController.displayScreen(intent);
  }
}
