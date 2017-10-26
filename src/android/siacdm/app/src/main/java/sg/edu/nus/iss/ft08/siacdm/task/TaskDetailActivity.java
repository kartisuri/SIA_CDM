package sg.edu.nus.iss.ft08.siacdm.task;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import sg.edu.nus.iss.ft08.siacdm.ControlFactory;
import sg.edu.nus.iss.ft08.siacdm.SiaCdmBaseActivity;
import sg.edu.nus.iss.ft08.siacdm.R;
import sg.edu.nus.iss.ft08.siacdm.model.Task;

public class TaskDetailActivity extends SiaCdmBaseActivity {
  private Task current;
  private EditText txtName;
  private EditText txtDesc;
  private EditText txtDuration;
  private TextView txtAssignedCount;

  public void setCurrent(Task obj){
    current = obj;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_task_detail);
    initCommonUi();
    initContentUi();
  }

  @Override
  public void onPostCreate(@Nullable Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    ControlFactory.getTaskDetailController().onDisplay(this);
    bindValues();
  }

  private void initContentUi() {
    View v = findViewById(R.id.drawer_layout);
    txtName = (EditText) v.findViewById(R.id.task_name);
    txtDesc = (EditText) v.findViewById(R.id.task_description);
    txtDuration = (EditText) v.findViewById(R.id.task_duration);
    txtAssignedCount = (TextView) v.findViewById(R.id.assigned_slot_count);

    txtAssignedCount.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        ControlFactory.getSchedulesController().startUseCase(current);
      }
    });
  }

  private void bindValues() {
    if (current!=null) {
      txtName.setText(current.getName());
      txtDesc.setText(current.getDescription());
      txtDuration.setText(Integer.toString(current.getDuration()));
      txtAssignedCount.setText(Integer.toString(current.getAssignedCount()));
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.task_detail, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == R.id.action_delete) {
      delete();
      return true;
    }
    if (id == R.id.action_save) {
      save();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  private void delete() {
    ControlFactory.getTaskDetailController().delete(current);
  }

  private void save() {
    if(current == null){
      Task newRecord = new Task();
      newRecord.setName(txtName.getText().toString());
      newRecord.setDescription(txtDesc.getText().toString());
      newRecord.setDuration(Integer.parseInt(txtDuration.getText().toString()));
      newRecord.setUpdatedBy(currentUser.getUserId());
      ControlFactory.getTaskDetailController().create(newRecord);
    } else {
      current.setName(txtName.getText().toString());
      current.setDescription(txtDesc.getText().toString());
      current.setDuration(Integer.parseInt(txtDuration.getText().toString()));
      current.setUpdatedBy(currentUser.getUserId());
      ControlFactory.getTaskDetailController().update(current);
    }
  }

  public void showSaveSuccess() {
    Toast.makeText(this, "Save successful.", Toast.LENGTH_SHORT).show();
  }

  public void showDeleteSuccess() {
    ControlFactory.getTasksController().startUseCase();
    Toast.makeText(this, "Delete successful.", Toast.LENGTH_SHORT).show();
  }
}
