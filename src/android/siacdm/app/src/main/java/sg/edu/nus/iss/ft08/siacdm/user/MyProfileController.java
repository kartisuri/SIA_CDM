package sg.edu.nus.iss.ft08.siacdm.user;

import android.content.Intent;

import sg.edu.nus.iss.ft08.siacdm.MainController;
import sg.edu.nus.iss.ft08.siacdm.SiaCdmBaseController;
import sg.edu.nus.iss.ft08.siacdm.model.User;

public class MyProfileController implements SiaCdmBaseController {
  private MyProfileActivity activity;

  @Override
  public void startUseCase() {
    Intent intent = new Intent(MainController.getApp(), MyProfileActivity.class);
    MainController.displayScreen(intent);
  }

  public void onDisplay(MyProfileActivity activity) {
    this.activity = activity;
  }

  public void update(User record) {
    UpdateProfileDelegate delegate = new UpdateProfileDelegate(this);
    delegate.execute(record);
  }

  public void showSaveSuccess(User user) {
    activity.setCurrent(user);
    activity.showSaveSuccess();
  }
}
