package sg.edu.nus.iss.ft08.siacdm.schedule;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import sg.edu.nus.iss.ft08.siacdm.ControlFactory;
import sg.edu.nus.iss.ft08.siacdm.R;
import sg.edu.nus.iss.ft08.siacdm.SiaCdmBaseActivity;
import sg.edu.nus.iss.ft08.siacdm.model.Schedule;
import sg.edu.nus.iss.ft08.siacdm.model.Task;
import sg.edu.nus.iss.ft08.siacdm.model.UserRole;

public class ScheduleDetailActivity extends SiaCdmBaseActivity implements
    View.OnClickListener {

  private Schedule current;
  public EditText txtStartTime;
  public EditText txtEndTime;
  public EditText txtStartDate;
  public EditText txtEndDate;
  public EditText txtRemarks;
  public boolean copy;

  int mYear;
  int mMonth;
  int mDay;

  int mHour;
  int mMinute;

  private UserRole selectedTechnician;
  private Task selectedTask;

  private List<UserRole> technicians;
  private List<Task> tasks;

  Spinner taskSpinner, technicianSpinner;

  Calendar selectedDate = Calendar.getInstance();
  Calendar selectedTime = Calendar.getInstance();

  private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
  private SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm", Locale.getDefault());

  public void setCurrent(Schedule obj) {
    current = obj;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_schedule_detail);
    initCommonUi();
    initContentUi();
    txtStartTime.setOnClickListener(this);
    txtEndTime.setOnClickListener(this);
    txtStartDate.setOnClickListener(this);
    txtEndDate.setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    if (v == txtStartDate) {
      final Calendar c = Calendar.getInstance();
      mYear = c.get(Calendar.YEAR);
      mMonth = c.get(Calendar.MONTH);
      mDay = c.get(Calendar.DAY_OF_MONTH);
      DatePickerDialog datePickerDialog = new DatePickerDialog(this,
          new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {
              Calendar calendar = Calendar.getInstance();
              calendar.set(year, monthOfYear, dayOfMonth);
              selectedDate = calendar;
              txtStartDate.setText(dateFormatter.format(calendar.getTime()));
            }
          }, mYear, mMonth, mDay);
      datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
      datePickerDialog.show();
    }
    if (v == txtEndDate) {
      final Calendar c = Calendar.getInstance();
      mYear = c.get(Calendar.YEAR);
      mMonth = c.get(Calendar.MONTH);
      mDay = c.get(Calendar.DAY_OF_MONTH);
      DatePickerDialog datePickerDialog = new DatePickerDialog(this,
          new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {
              Calendar calendar = Calendar.getInstance();
              calendar.set(year, monthOfYear, dayOfMonth);
              selectedDate = calendar;
              txtEndDate.setText(dateFormatter.format(calendar.getTime()));
            }
          }, mYear, mMonth, mDay);
      datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
      datePickerDialog.show();
    }
    if (v == txtEndTime) {
      final Calendar c = Calendar.getInstance();
      mHour = c.get(Calendar.HOUR_OF_DAY);
      mMinute = c.get(Calendar.MINUTE);
      TimePickerDialog timePickerDialog = new TimePickerDialog(this,
          new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay,
                                  int minute) {
              Calendar calendar = Calendar.getInstance();
              calendar.set(Calendar.HOUR, hourOfDay);
              calendar.set(Calendar.MINUTE, minute);
              selectedTime = calendar;
              txtEndTime.setText(timeFormatter.format(calendar.getTime()));
            }
          }, mHour, mMinute, false);
      timePickerDialog.show();
    }
    if (v == txtStartTime) {
      final Calendar c = Calendar.getInstance();
      mHour = c.get(Calendar.HOUR_OF_DAY);
      mMinute = c.get(Calendar.MINUTE);
      TimePickerDialog timePickerDialog = new TimePickerDialog(this,
          new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay,
                                  int minute) {
              Calendar calendar = Calendar.getInstance();
              calendar.set(Calendar.HOUR, hourOfDay);
              calendar.set(Calendar.MINUTE, minute);
              selectedTime = calendar;
              txtStartTime.setText(timeFormatter.format(calendar.getTime()));
            }
          }, mHour, mMinute, false);
      timePickerDialog.show();
    }
  }

  @Override
  public void onPostCreate(@Nullable Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    ControlFactory.getScheduleDetailController().onDisplay(this);
    bindValues();
  }

  private void initContentUi() {
    View v = findViewById(R.id.drawer_layout);
    txtStartDate = (EditText) v.findViewById(R.id.txt_startdate);
    txtEndDate = (EditText) v.findViewById(R.id.txt_enddate);
    txtStartTime = (EditText) v.findViewById(R.id.txt_starttime);
    txtEndTime = (EditText) v.findViewById(R.id.txt_endtime);
    txtRemarks = (EditText) v.findViewById((R.id.remarks));
  }

  private void bindValues() {
    if (current != null) {
      txtStartDate.setText(current.getStartDate());
      txtStartTime.setText(current.getStartTime());
      txtEndDate.setText(current.getEndDate());
      txtEndTime.setText(current.getEndTime());
      txtRemarks.setText(current.getRemarks());
    }
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

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.schedule_detail, menu);
    if(!currentUser.isAdmin() && !currentUser.isSupervisor()) {
      menu.findItem(R.id.action_save).setVisible(false);
      menu.findItem(R.id.action_delete).setVisible(false);
      menu.findItem(R.id.action_copy).setVisible(false);
    }
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
    if (id == R.id.action_copy) {
      copy();
      return true;
    }
    if (id == R.id.action_chat) {
      Intent chatIntent = new Intent(this,ChatsActivity.class);
      this.startActivity(chatIntent);
      return true;
    }
    if (id == R.id.action_call) {
      Intent chatIntent = new Intent(this,CallActivity.class);
      this.startActivity(chatIntent);
      return true;
    }
    if (id == R.id.action_photo) {
      Intent captureIntent = new Intent(this,CaptureActivity.class);
      this.startActivity(captureIntent);
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  public void copy() {
    initCommonUi();
    initContentUi();
    txtStartTime.setOnClickListener(this);
    txtEndTime.setOnClickListener(this);
    txtStartDate.setOnClickListener(this);
    txtEndDate.setOnClickListener(this);
    txtStartDate = ((EditText) findViewById(R.id.txt_startdate));
    txtStartDate.getText().clear();
    txtStartTime = ((EditText) findViewById(R.id.txt_starttime));
    txtStartTime.getText().clear();
    txtEndDate = ((EditText) findViewById(R.id.txt_enddate));
    txtEndDate.getText().clear();
    txtEndTime = ((EditText) findViewById(R.id.txt_endtime));
    txtEndTime.getText().clear();
    copy = true;
  }

  private void save() {
    if (current == null) {
      Schedule newRecord = new Schedule();
      newRecord.setTaskId(selectedTask.getId());
      newRecord.setTechnicianId(selectedTechnician.getUserId());
      newRecord.setRemarks(txtRemarks.getText().toString());
      newRecord.setStartDate(txtStartDate.getText().toString());
      newRecord.setStartTime(txtStartTime.getText().toString());
      newRecord.setEndDate(txtEndDate.getText().toString());
      newRecord.setEndTime(txtEndTime.getText().toString());
      newRecord.setUpdatedBy(currentUser.getUserId());
      ControlFactory.getScheduleDetailController().create(newRecord);
    } else if (copy) {
      Schedule newRecord1 = new Schedule();
      newRecord1.setTaskId(selectedTask.getId());
      newRecord1.setTechnicianId(selectedTechnician.getUserId());
      newRecord1.setRemarks(txtRemarks.getText().toString());
      newRecord1.setStartDate(txtStartDate.getText().toString());
      newRecord1.setStartTime(txtStartTime.getText().toString());
      newRecord1.setEndDate(txtEndDate.getText().toString());
      newRecord1.setEndTime(txtEndTime.getText().toString());
      newRecord1.setUpdatedBy(currentUser.getUserId());
      ControlFactory.getScheduleDetailController().copy(newRecord1);
    } else {
      current.setTaskId(selectedTask.getId());
      current.setTechnicianId(selectedTechnician.getUserId());
      current.setRemarks(txtRemarks.getText().toString());
      current.setStartDate(txtStartDate.getText().toString());
      current.setStartTime(txtStartTime.getText().toString());
      current.setEndDate(txtEndDate.getText().toString());
      current.setEndTime(txtEndTime.getText().toString());
      current.setUpdatedBy(currentUser.getUserId());
      ControlFactory.getScheduleDetailController().update(current);
    }
  }

  private void delete() {
    ControlFactory.getScheduleDetailController().delete(current);
  }

  public void showSaveSuccess() {
    ControlFactory.getSchedulesController().startUseCase();
    Toast.makeText(this, "Save successful.", Toast.LENGTH_SHORT).show();
  }

  public void showDeleteSuccess() {
    ControlFactory.getSchedulesController().startUseCase();
    Toast.makeText(this, "Delete successful.", Toast.LENGTH_SHORT).show();
  }

  public void showOverlap() {
    Toast.makeText(this, "Schedule overlap for technician. Please change time or technician",
        Toast.LENGTH_SHORT).show();
  }

  public void showTechnicians(List<UserRole> users) {
    this.technicians = users;
    View v = findViewById(R.id.drawer_layout);
    technicianSpinner = (Spinner) v.findViewById(R.id.technician_spinner);

    SpinnerAdapter adapter = new ArrayAdapter<>(this,
        R.layout.support_simple_spinner_dropdown_item, this.technicians);
    technicianSpinner.setAdapter(adapter);

    technicianSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedTechnician = technicians.get(position);
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {}
    });

    int selectedIndex = getTechnicianIndex();
    technicianSpinner.setSelection(selectedIndex);
  }

  public void showTasks(final List<Task> tasks) {
    this.tasks = tasks;
    View v = findViewById(R.id.drawer_layout);
    taskSpinner = (Spinner) v.findViewById(R.id.task_spinner);

    SpinnerAdapter adapter = new ArrayAdapter<>(this,
        R.layout.support_simple_spinner_dropdown_item,
        this.tasks);

    taskSpinner.setAdapter(adapter);

    taskSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedTask = tasks.get(position);
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {}
    });
    int selectedIndex = getTaskIndex();
    taskSpinner.setSelection(selectedIndex);
  }

  private int getTechnicianIndex() {
    if (current == null) {
      return -1;
    }
    if (technicians == null || technicians.isEmpty()) {
      return -1;
    }
    UserRole temp = new UserRole();
    temp.setUserId(current.getTechnicianId());
    return technicians.indexOf(temp);
  }

  private int getTaskIndex() {
    if (current == null) {
      return -1;
    }
    if (tasks == null || tasks.isEmpty()) {
      return -1;
    }
    Task temp = new Task();
    temp.setId(current.getTaskId());
    return tasks.indexOf(temp);
  }

}
