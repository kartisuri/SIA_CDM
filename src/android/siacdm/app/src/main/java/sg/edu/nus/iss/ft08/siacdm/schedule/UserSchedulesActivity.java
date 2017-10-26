package sg.edu.nus.iss.ft08.siacdm.schedule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import sg.edu.nus.iss.ft08.siacdm.ControlFactory;
import sg.edu.nus.iss.ft08.siacdm.SiaCdmBaseActivity;
import sg.edu.nus.iss.ft08.siacdm.R;
import sg.edu.nus.iss.ft08.siacdm.model.Schedule;

public class UserSchedulesActivity extends SiaCdmBaseActivity {
  RecyclerView recyclerView;
  SchedulesRecyclerViewAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_user_schedules);
    initCommonUi();
  }

  @Override
  protected void onPostCreate(@Nullable Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    ControlFactory.getSchedulesController().onDisplayTargetUser(this);
  }

  public void showSchedules(List<Schedule> slots) {
    View v = findViewById(R.id.drawer_layout);
    recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_schedules);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    adapter = new SchedulesRecyclerViewAdapter(slots);
    recyclerView.setAdapter(adapter);
  }
}
