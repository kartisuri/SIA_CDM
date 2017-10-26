package sg.edu.nus.iss.ft08.siacdm.task;

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
import sg.edu.nus.iss.ft08.siacdm.model.Task;

public class TasksActivity extends SiaCdmBaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_tasks);
    initCommonUi();
  }

  @Override
  protected void onPostCreate(@Nullable Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    ControlFactory.getTasksController().onDisplay(this);
  }

  public void showTasks(List<Task> tasks) {
    View v = findViewById(R.id.drawer_layout);
    RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_tasks);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    TasksRecyclerViewAdapter adapter = new TasksRecyclerViewAdapter(tasks);
    recyclerView.setAdapter(adapter);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.tasks, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == R.id.action_create_new) {
      ControlFactory.getTaskDetailController().startCreateUseCase();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
