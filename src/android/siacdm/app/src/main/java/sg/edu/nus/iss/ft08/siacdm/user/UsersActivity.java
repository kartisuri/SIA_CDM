package sg.edu.nus.iss.ft08.siacdm.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import sg.edu.nus.iss.ft08.siacdm.ControlFactory;
import sg.edu.nus.iss.ft08.siacdm.SiaCdmBaseActivity;
import sg.edu.nus.iss.ft08.siacdm.R;
import sg.edu.nus.iss.ft08.siacdm.model.User;

public class UsersActivity extends SiaCdmBaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_users);
    initCommonUi();
  }

  @Override
  public void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        ControlFactory.getUsersController().onDisplay(this);
  }

  public void showUsers(List<User> users){
      View v = findViewById(R.id.drawer_layout);
      RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_users);
      recyclerView.setLayoutManager(new LinearLayoutManager(this));
      UsersRecycleViewAdapter adapter = new UsersRecycleViewAdapter(users);
      recyclerView.setAdapter(adapter);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.users, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == R.id.action_create_new) {
        ControlFactory.getUserDetailController().startCreateUseCase();
        return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
