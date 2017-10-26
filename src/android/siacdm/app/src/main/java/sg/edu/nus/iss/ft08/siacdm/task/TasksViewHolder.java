package sg.edu.nus.iss.ft08.siacdm.task;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import sg.edu.nus.iss.ft08.siacdm.R;
import sg.edu.nus.iss.ft08.siacdm.model.Task;

public class TasksViewHolder extends RecyclerView.ViewHolder {
  public final View view;
  final TextView txtName;
  final TextView txtDescription;
  final TextView txtDuration;
  final TextView txtAssignedCount;
  public Task task;

  TasksViewHolder(View itemView) {
    super(itemView);
    view = itemView;
    txtName = (TextView) itemView.findViewById(R.id.task_name);
    txtDescription = (TextView) itemView.findViewById(R.id.task_description);
    txtDuration = (TextView) itemView.findViewById(R.id.task_duration);
    txtAssignedCount = (TextView) itemView.findViewById(R.id.assigned_slot_count);
  }
}
