package sg.edu.nus.iss.ft08.siacdm;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.ft08.siacdm.home.DashboardRecyclerViewAdapter;
import sg.edu.nus.iss.ft08.siacdm.model.DashboardItem;
import sg.edu.nus.iss.ft08.siacdm.model.Summary;

public class MainActivity extends SiaCdmBaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initCommonUi();
  }

  @Override
  protected void onPostCreate(@Nullable Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    ControlFactory.getMainController().onDisplay(this);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    return id == R.id.action_settings || super.onOptionsItemSelected(item);
  }

  private List<DashboardItem> getDashboardItems(Summary summary) {
    List<DashboardItem> items = new ArrayList<>();
    SiaCdmBaseController myProfileController = ControlFactory.getMyProfileController();
    DashboardItem forMyProfile = new DashboardItem(myProfileController);
    forMyProfile.setName("My Profile");
    String uri = "@drawable/ic_menu_myprofile";
    int userIconId = getResources().getIdentifier(uri, null, getPackageName());
    Drawable userIcon = getResources().getDrawable(userIconId);
    forMyProfile.setIcon(userIcon);
    SiaCdmBaseController usersController = ControlFactory.getUsersController();
    DashboardItem forUsers = new DashboardItem(usersController);
    forUsers.setName("Users");
    forUsers.setCount(summary.getTotalUsers());
    uri = "@drawable/ic_menu_users";
    userIconId = getResources().getIdentifier(uri, null, getPackageName());
    userIcon = getResources().getDrawable(userIconId);
    forUsers.setIcon(userIcon);
    SiaCdmBaseController schedulesController = ControlFactory.getSchedulesController();
    DashboardItem forSchedules = new DashboardItem(schedulesController);
    forSchedules.setName("Schedules");
    forSchedules.setCount(summary.getTotalSlots());
    uri = "@drawable/ic_menu_schedules";
    int scheduleIconId = getResources().getIdentifier(uri, null, getPackageName());
    Drawable scheduleIcon = getResources().getDrawable(scheduleIconId);
    forSchedules.setIcon(scheduleIcon);
    SiaCdmBaseController tasksController = ControlFactory.getTasksController();
    DashboardItem forTasks = new DashboardItem(tasksController);
    forTasks.setName("Tasks");
    forTasks.setCount(summary.getTotalTasks());
    uri = "@drawable/ic_menu_tasks";
    int taskIconId = getResources().getIdentifier(uri, null, getPackageName());
    Drawable taskIcon = getResources().getDrawable(taskIconId);
    forTasks.setIcon(taskIcon);
    items.add(forMyProfile);
    items.add(forSchedules);
    if(currentUser.isAdmin() || currentUser.isSupervisor()){
      items.add(forTasks);
      items.add(forUsers);
    }
    return items;
  }

  public void showDashboardItems(Summary summary) {
    List<DashboardItem> items = getDashboardItems(summary);
    View v = findViewById(R.id.drawer_layout);
    RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_dashboard_items);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    DashboardRecyclerViewAdapter adapter = new DashboardRecyclerViewAdapter(items);
    recyclerView.setAdapter(adapter);
  }
}
