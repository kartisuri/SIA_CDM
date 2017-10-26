package sg.edu.nus.iss.ft08.siacdm.task;

import android.content.Intent;

import sg.edu.nus.iss.ft08.siacdm.MainController;
import sg.edu.nus.iss.ft08.siacdm.model.Task;

public class TaskDetailController {
  private TaskDetailActivity activity;
  private Task task;
  private TasksController tasksController = new TasksController();

  void startEditUseCase(Task task) {
    this.task = task;
    Intent intent = new Intent(MainController.getApp(), TaskDetailActivity.class);
    MainController.displayScreen(intent);
  }

  void startCreateUseCase() {
    this.task = null;
    Intent intent = new Intent(MainController.getApp(), TaskDetailActivity.class);
    MainController.displayScreen(intent);
  }

  public void create(Task newRecord) {
    CreateDelegate delegate = new CreateDelegate(this);
    delegate.execute(newRecord);
  }

  public void update(Task record) {
    UpdateDelegate delegate = new UpdateDelegate(this);
    delegate.execute(record);
  }

  public void delete(Task record) {
    DeleteDelegate delegate = new DeleteDelegate(this);
    delegate.execute(record);
  }

  public void onDisplay(TaskDetailActivity activity){
    this.activity = activity;
    activity.setCurrent(task);
  }

  public void showSaveSuccess(Task task) {
    this.task = task;
    activity.setCurrent(task);
    activity.showSaveSuccess();
    tasksController.startUseCase();
  }

  void showDeleteSuccess(){
    activity.showDeleteSuccess();
    tasksController.startUseCase();
  }
}
