package sg.edu.nus.iss.ft08.siacdm.schedule;

import android.content.Intent;

import java.util.List;

import sg.edu.nus.iss.ft08.siacdm.MainController;
import sg.edu.nus.iss.ft08.siacdm.model.Schedule;
import sg.edu.nus.iss.ft08.siacdm.model.Task;
import sg.edu.nus.iss.ft08.siacdm.model.UserRole;

public class ScheduleDetailController {

  private ScheduleDetailActivity activity;
  private Schedule schedule;

  void startEditUseCase(Schedule schedule) {
    this.schedule = schedule;
    Intent intent = new Intent(MainController.getApp(), ScheduleDetailActivity.class);
    MainController.displayScreen(intent);
  }

  void startCreateUseCase() {
    this.schedule = null;
    Intent intent = new Intent(MainController.getApp(), ScheduleDetailActivity.class);
    MainController.displayScreen(intent);
  }

  void create(Schedule newRecord) {
    CreateDelegate delegate = new CreateDelegate(this);
    delegate.execute(newRecord);
  }

  void update(Schedule record) {
    UpdateDelegate delegate = new UpdateDelegate(this);
    delegate.execute(record);
  }

  public void delete(Schedule record) {
    DeleteDelegate delegate = new DeleteDelegate(this);
    delegate.execute(record);
  }

  void copy(Schedule record) {
    CreateDelegate delegate = new CreateDelegate(this);
    delegate.execute(record);
  }

  public void onDisplay(ScheduleDetailActivity activity) {
    this.activity = activity;
    activity.setCurrent(schedule);

    TechnicianDelegate technicianDelegate = new TechnicianDelegate(this);
    TaskDelegate taskDelegate = new TaskDelegate(this);

    technicianDelegate.execute();
    taskDelegate.execute();
  }

  public void showSaveSuccess(Schedule schedule) {
    if (schedule != null) {
      this.schedule = schedule;
      activity.setCurrent(schedule);
      activity.showSaveSuccess();
    } else {
      activity.showOverlap();
    }
  }

  void showDeleteSuccess() {
    activity.showDeleteSuccess();
  }

  void showTechnicians(List<UserRole> users) {
    activity.showTechnicians(users);
  }

  void showTasks(List<Task> tasks) {
    activity.showTasks(tasks);
  }
}
