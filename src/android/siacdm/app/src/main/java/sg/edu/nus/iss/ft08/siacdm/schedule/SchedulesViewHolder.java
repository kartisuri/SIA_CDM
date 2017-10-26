package sg.edu.nus.iss.ft08.siacdm.schedule;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import sg.edu.nus.iss.ft08.siacdm.R;
import sg.edu.nus.iss.ft08.siacdm.model.Schedule;

public class SchedulesViewHolder extends RecyclerView.ViewHolder {

  public final View view;
  final TextView txtTechnician;
  final TextView txtTaskName;
  final TextView txtStartTime;
  final TextView txtEndTime;

  public Schedule schedule;

  SchedulesViewHolder(View itemView) {
    super(itemView);
    view = itemView;
    txtTaskName = (TextView) itemView.findViewById(R.id.task);
    txtTechnician = (TextView) itemView.findViewById(R.id.technician);
    txtStartTime = (TextView) itemView.findViewById(R.id.starttime);
    txtEndTime = (TextView) itemView.findViewById(R.id.endtime);
  }
}
