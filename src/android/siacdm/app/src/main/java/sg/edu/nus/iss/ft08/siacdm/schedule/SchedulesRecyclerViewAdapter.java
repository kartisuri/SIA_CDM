package sg.edu.nus.iss.ft08.siacdm.schedule;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.ft08.siacdm.ControlFactory;
import sg.edu.nus.iss.ft08.siacdm.R;
import sg.edu.nus.iss.ft08.siacdm.model.Schedule;

public class SchedulesRecyclerViewAdapter extends RecyclerView.Adapter<SchedulesViewHolder> {
  private List<Schedule> schedules;

  SchedulesRecyclerViewAdapter(List<Schedule> schedules) {

    if (schedules != null) {
      this.schedules = schedules;
    } else {
      this.schedules = new ArrayList<>();
    }
  }

  public void setSchedules(List<Schedule> slots) {
    this.schedules = slots;
  }

  public List<Schedule> getSchedules() {
    return this.schedules;
  }

  @Override
  public SchedulesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater
        .from(parent.getContext())
        .inflate(R.layout.view_holder_schedule, parent, false);
    return new SchedulesViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final SchedulesViewHolder holder, int position) {
    Schedule current = schedules.get(position);
    holder.schedule = current;
    holder.txtTaskName.setText(current.getTaskName());
    holder.txtTechnician.setText(current.getTechnicianName());
    holder.txtStartTime.setText(current.getStartTime());
    holder.txtEndTime.setText(current.getEndTime());
    holder.view.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        ControlFactory.getScheduleDetailController().startEditUseCase(holder.schedule);
      }
    });
  }

  @Override
  public int getItemCount() {
    return this.schedules.size();
  }
}
