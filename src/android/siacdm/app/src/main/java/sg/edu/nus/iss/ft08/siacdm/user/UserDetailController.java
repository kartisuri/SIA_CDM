package sg.edu.nus.iss.ft08.siacdm.user;

import android.content.Intent;

import sg.edu.nus.iss.ft08.siacdm.MainController;
import sg.edu.nus.iss.ft08.siacdm.model.User;

public class UserDetailController {
    private UserDetailActivity activity;
    private User user;

    void startEditUseCase(User user) {
        this.user = user;
        Intent intent = new Intent(MainController.getApp(), UserDetailActivity.class);
        MainController.displayScreen(intent);
    }

    void startCreateUseCase() {
        this.user = null;
        Intent intent = new Intent(MainController.getApp(), UserDetailActivity.class);
        MainController.displayScreen(intent);
    }

    void create(User newRecord) {
        CreateDelegate delegate = new CreateDelegate(this);
        delegate.execute(newRecord);
    }

    void update(User record) {
        UpdateDelegate delegate = new UpdateDelegate(this);
        delegate.execute(record);
    }

    public void delete(User record) {
            DeleteDelegate delegate = new DeleteDelegate(this);
            delegate.execute(record);
    }

    public void onDisplay(UserDetailActivity activity){
        this.activity = activity;
        activity.setCurrent(user);
    }

    public void showSaveSuccess(User user) {
        this.user= user;
        activity.setCurrent(user);
        activity.showSaveSuccess();
    }

    void showDeleteSuccess(Boolean status){
        activity.showDeleteSuccess(status);
    }
}
