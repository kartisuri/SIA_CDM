package sg.edu.nus.iss.ft08.siacdm;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import sg.edu.nus.iss.ft08.siacdm.model.User;

public class SiaCdmBaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
  protected User currentUser;
  TextView txtCurrentUserId;
  TextView txtCurrentUserFullName;

  protected void initCommonUi() {
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.setDrawerListener(toggle);
    toggle.syncState();
    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);
    View headerView = navigationView.getHeaderView(0);
    txtCurrentUserId = (TextView) headerView.findViewById(R.id.current_user_id);
    txtCurrentUserFullName = (TextView) headerView.findViewById(R.id.current_user_full_name);
    currentUser = new User();
    SharedPreferences preferences = getSharedPreferences(Constants.APP_PREFERENCES, Context.MODE_PRIVATE);
    if (preferences != null) {
      boolean isLoggedIn = preferences.contains(Constants.KEY_USER_ID);
      if (isLoggedIn) {
        String userInfo = preferences.getString(Constants.KEY_USER_INFO, "");
        try {
          JSONObject userInJson = new JSONObject(userInfo);
          currentUser = User.fromJson(userInJson);
        } catch (JSONException e) {
          e.printStackTrace();
          currentUser.setUserId(preferences.getString(Constants.KEY_USER_ID, "unknown"));
          currentUser.setFullName(preferences.getString(Constants.KEY_USER_NAME, "unknown"));
        }
        txtCurrentUserId.setText(currentUser.getUserId());
        txtCurrentUserFullName.setText(currentUser.getFullName());
      } else {
        ControlFactory.getLoginController().startUseCase();
      }
    }
    if(!currentUser.isAdmin()
        && !currentUser.isSupervisor()) {
      Menu menu = navigationView.getMenu();
      menu.findItem(R.id.nav_users).setVisible(false);
      menu.findItem(R.id.nav_tasks).setVisible(false);
    }
  }

  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected(@NonNull MenuItem item) {
    int id = item.getItemId();
    if (id == R.id.nav_home) {
      ControlFactory.getMainController().startUseCase();
    } else if (id == R.id.nav_users) {
      ControlFactory.getUsersController().startUseCase();
    } else if (id == R.id.nav_schedules) {
      ControlFactory.getSchedulesController().startUseCase();
    } else if (id == R.id.nav_tasks) {
      ControlFactory.getTasksController().startUseCase();
    } else if (id == R.id.nav_signout) {
      ControlFactory.getLoginController().logout();
    } else if (id == R.id.nav_my_profile) {
      ControlFactory.getMyProfileController().startUseCase();
    }
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }

  @Override
  public void onBackPressed() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  protected void updateDrawerheader(){
    txtCurrentUserId.setText(currentUser.getUserId());
    txtCurrentUserFullName.setText(currentUser.getFullName());
  }
}
