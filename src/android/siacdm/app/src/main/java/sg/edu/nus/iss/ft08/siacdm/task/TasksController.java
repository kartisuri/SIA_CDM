package sg.edu.nus.iss.ft08.siacdm.task;

import android.content.Intent;

import java.util.List;

import sg.edu.nus.iss.ft08.siacdm.MainController;
import sg.edu.nus.iss.ft08.siacdm.SiaCdmBaseController;
import sg.edu.nus.iss.ft08.siacdm.model.Task;

public class TasksController implements SiaCdmBaseController {
  private TasksActivity activity;

  public void startUseCase() {
    Intent intent = new Intent(MainController.getApp(), TasksActivity.class);
    MainController.displayScreen(intent);
  }

  public void onDisplay(TasksActivity activity) {
    this.activity = activity;

    FindAllDelegate delegate =  new FindAllDelegate(this);

    delegate.execute(new Task());
  }

  public void showTasks(List<Task> tasks){
    activity.showTasks(tasks);
  }

  public void showTask(Task task){
    System.out.println(task.getName());
    System.out.println(task.getDescription());
  }
}
