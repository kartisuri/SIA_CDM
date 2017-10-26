package sg.edu.nus.iss.ft08.siacdm.schedule;

import android.content.Intent;

import java.util.Calendar;
import java.util.List;

import sg.edu.nus.iss.ft08.siacdm.MainController;
import sg.edu.nus.iss.ft08.siacdm.SiaCdmBaseController;
import sg.edu.nus.iss.ft08.siacdm.model.Schedule;
import sg.edu.nus.iss.ft08.siacdm.model.Task;
import sg.edu.nus.iss.ft08.siacdm.model.User;

public class SchedulesController implements SiaCdmBaseController {
  private SchedulesActivity activity;
  private TaskSchedulesActivity taskSchedulesActivity;
  private UserSchedulesActivity userSchedulesActivity;
  private User targetUser;
  private Task targetTask;

  public void startUseCase() {
    Intent intent = new Intent(MainController.getApp(), SchedulesActivity.class);
    MainController.displayScreen(intent);
  }

  public void startUseCase(User targetUser) {
    this.targetUser = targetUser;
    Intent intent = new Intent(MainController.getApp(), UserSchedulesActivity.class);
    MainController.displayScreen(intent);
  }

  public void startUseCase(Task targetTask) {
    this.targetTask = targetTask;
    Intent intent = new Intent(MainController.getApp(), TaskSchedulesActivity.class);
    MainController.displayScreen(intent);
  }

  public void onDisplay(SchedulesActivity activity) {
    this.activity = activity;
    // IMPORTANT: do not set calendar start day of week
    // if you set start day of week and today is in the middle of the week
    // you will get next week monday's week number when you call c.get(Calendar.WEEK_OF_YEAR
    Calendar c = Calendar.getInstance();
    int weekNo = c.get(Calendar.WEEK_OF_YEAR);
    FindAllWeekDelegate delegate = new FindAllWeekDelegate(this);
    delegate.execute(weekNo);
  }

  public void onDisplay(int weekNo) {
    FindAllWeekDelegate delegate = new FindAllWeekDelegate(this);
    delegate.execute(weekNo);
  }

  void onDisplayTargetUser(UserSchedulesActivity activity) {
    this.userSchedulesActivity = activity;
    FindAllUserDelegate delegate = new FindAllUserDelegate(this);
    delegate.execute(targetUser);
  }

  void onDisplayTargetTask(TaskSchedulesActivity activity) {
    this.taskSchedulesActivity = activity;
    FindAllTaskDelegate delegate = new FindAllTaskDelegate(this);
    delegate.execute(targetTask);
  }

  void showSchedules(List<Schedule> slots) {
    activity.showSchedules(slots);
  }

  void showUserSchedules(List<Schedule> slots) {
    userSchedulesActivity.showSchedules(slots);
  }

  void showTaskSchedules(List<Schedule> slots) {
    taskSchedulesActivity.showSchedules(slots);
  }

  void showMessage(Boolean overlap) {
    activity.showCopySchedules();
    if (!overlap) {
      activity.showCopySaveSuccess();
    } else {
      activity.showCopyOverlap();
    }
  }

  void copySchedules(List<String> stringList){
    CopyDelegate delegate = new CopyDelegate(this);
    delegate.execute(stringList);
  }
}
