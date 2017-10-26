package sg.edu.nus.iss.ft08.siacdm.task;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.ft08.siacdm.ControlFactory;
import sg.edu.nus.iss.ft08.siacdm.R;
import sg.edu.nus.iss.ft08.siacdm.model.Task;

public class TasksRecyclerViewAdapter extends RecyclerView.Adapter<TasksViewHolder> {
  private final List<Task> tasks;

  TasksRecyclerViewAdapter(List<Task> tasks) {
    if(tasks !=null) {
      this.tasks = tasks;
    } else {
      this.tasks = new ArrayList<>();
    }
  }

  @Override
  public TasksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater
        .from(parent.getContext())
        .inflate(R.layout.view_holder_task, parent, false);
    return new TasksViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final TasksViewHolder holder, int position) {
    Task current = tasks.get(position);
    holder.task = current;
    holder.txtName.setText(current.getName());
    holder.txtDescription.setText(current.getDescription());
    holder.txtDuration.setText(Integer.toString(current.getDuration()));
    holder.txtAssignedCount.setText(Integer.toString(current.getAssignedCount()));
    holder.view.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View v) {
        ControlFactory.getTaskDetailController().startEditUseCase(holder.task);
      }
    });
  }

  @Override
  public int getItemCount() {
    return this.tasks.size();
  }
}
