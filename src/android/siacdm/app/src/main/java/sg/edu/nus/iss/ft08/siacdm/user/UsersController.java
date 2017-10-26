package sg.edu.nus.iss.ft08.siacdm.user;

import android.content.Intent;

import java.util.List;

import sg.edu.nus.iss.ft08.siacdm.MainController;

import sg.edu.nus.iss.ft08.siacdm.SiaCdmBaseController;
import sg.edu.nus.iss.ft08.siacdm.model.User;

public class UsersController implements SiaCdmBaseController {
  private UsersActivity activity;

  public void startUseCase() {
    Intent intent = new Intent(MainController.getApp(), UsersActivity.class);
    MainController.displayScreen(intent);
  }

  public void onDisplay(UsersActivity activity){
    this.activity = activity;
    FindAllDelegate delegate = new FindAllDelegate(this);
    delegate.execute(new User());
  }

  void showUsers(List<User> users){
    activity.showUsers(users);
  }
}
