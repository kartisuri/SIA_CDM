package sg.edu.nus.iss.ft08.siacdm;

import sg.edu.nus.iss.ft08.siacdm.authenticate.LoginController;
import sg.edu.nus.iss.ft08.siacdm.task.TaskDetailController;
import sg.edu.nus.iss.ft08.siacdm.task.TasksController;
import sg.edu.nus.iss.ft08.siacdm.schedule.ScheduleDetailController;
import sg.edu.nus.iss.ft08.siacdm.schedule.SchedulesController;
import sg.edu.nus.iss.ft08.siacdm.user.MyProfileController;
import sg.edu.nus.iss.ft08.siacdm.user.UserDetailController;
import sg.edu.nus.iss.ft08.siacdm.user.UsersController;

public class ControlFactory {
  private static MainController mainController = null;
  private static LoginController loginController = null;
  private static TasksController tasksController = null;
  private static SchedulesController schedulesController = null;
  private static UsersController usersController = null;
  private static TaskDetailController taskDetailController = null;
  private static ScheduleDetailController scheduleDetailController = null;
  private static UserDetailController userDetailController = null;
  private static MyProfileController myProfileController = null;

  public static MainController getMainController() {
    if (mainController == null) mainController = new MainController();
    return mainController;
  }

  public static LoginController getLoginController() {
    if (loginController == null) loginController = new LoginController();
    return loginController;
  }

  public static TasksController getTasksController() {
    if (tasksController == null) tasksController = new TasksController();
    return tasksController;
  }

  public static SchedulesController getSchedulesController() {
    if (schedulesController == null)
      schedulesController = new SchedulesController();
    return schedulesController;
  }

  public static UsersController getUsersController() {
    if (usersController == null) usersController = new UsersController();
    return usersController;
  }

  public static TaskDetailController getTaskDetailController() {
    if (taskDetailController == null)
      taskDetailController = new TaskDetailController();
    return taskDetailController;
  }

  public static ScheduleDetailController getScheduleDetailController() {
    if (scheduleDetailController == null)
      scheduleDetailController = new ScheduleDetailController();
    return scheduleDetailController;
  }

  public static UserDetailController getUserDetailController() {
    if (userDetailController == null) userDetailController = new UserDetailController();
    return userDetailController;
  }

  public static MyProfileController getMyProfileController() {
    if(myProfileController == null) myProfileController = new MyProfileController();
    return myProfileController;
  }
}
