package sg.edu.nus.iss.ft08.siacdm.schedule;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import sg.edu.nus.iss.ft08.siacdm.ControlFactory;
import sg.edu.nus.iss.ft08.siacdm.SiaCdmBaseActivity;
import sg.edu.nus.iss.ft08.siacdm.R;
import sg.edu.nus.iss.ft08.siacdm.model.Schedule;

import static sg.edu.nus.iss.ft08.siacdm.Constants.DAY_NAME_FRIDAY;
import static sg.edu.nus.iss.ft08.siacdm.Constants.DAY_NAME_MONDAY;
import static sg.edu.nus.iss.ft08.siacdm.Constants.DAY_NAME_SATURDAY;
import static sg.edu.nus.iss.ft08.siacdm.Constants.DAY_NAME_SUNDAY;
import static sg.edu.nus.iss.ft08.siacdm.Constants.DAY_NAME_THURSDAY;
import static sg.edu.nus.iss.ft08.siacdm.Constants.DAY_NAME_TUESDAY;
import static sg.edu.nus.iss.ft08.siacdm.Constants.DAY_NAME_WEDNESDAY;

public class SchedulesActivity extends SiaCdmBaseActivity {
  private TextView lblMonday;
  private TextView lblTuesday;
  private TextView lblWednesday;
  private TextView lblThursday;
  private TextView lblFriday;
  private TextView lblSaturday;
  private TextView lblSunday;
  private RecyclerView recyclerView;
  private SchedulesRecyclerViewAdapter adapter;

  List<Schedule> allSlots;
  private List<Schedule> mondaySlots;
  private List<Schedule> tuesdaySlots;
  private List<Schedule> wednesdaySlots;
  private List<Schedule> thursdaySlots;
  private List<Schedule> fridaySlots;
  private List<Schedule> saturdaySlots;
  private List<Schedule> sundaySlots;
  Calendar selectedDate = Calendar.getInstance();
  int mYear;
  int mMonth;
  int mDay;
  int sourceWeekNo;
  int targetWeekNo;
  Calendar source;
  Calendar target;
  TextView sourceDate;
  TextView targetDate;
  Dialog dialog;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_schedules);
    initCommonUi();
    initContentUi();
  }

  private void initContentUi() {
    initDayLabels();
  }

  private void initDayLabels() {
    View v = findViewById(R.id.drawer_layout);
    lblMonday = (TextView) v.findViewById(R.id.label_monday);
    lblTuesday = (TextView) v.findViewById(R.id.label_tuesday);
    lblWednesday = (TextView) v.findViewById(R.id.label_wednesday);
    lblThursday = (TextView) v.findViewById(R.id.label_thursday);
    lblFriday = (TextView) v.findViewById(R.id.label_friday);
    lblSaturday = (TextView) v.findViewById(R.id.label_saturday);
    lblSunday = (TextView) v.findViewById(R.id.label_sunday);

    lblMonday.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        greyoutDayLabels();
        highlightDayLabel(lblMonday);
        showDaySchedules(mondaySlots);
      }
    });
    lblTuesday.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        greyoutDayLabels();
        highlightDayLabel(lblTuesday);
        showDaySchedules(tuesdaySlots);
      }
    });
    lblWednesday.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        greyoutDayLabels();
        highlightDayLabel(lblWednesday);
        showDaySchedules(wednesdaySlots);
      }
    });
    lblThursday.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        greyoutDayLabels();
        highlightDayLabel(lblThursday);
        showDaySchedules(thursdaySlots);
      }
    });
    lblFriday.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        greyoutDayLabels();
        highlightDayLabel(lblFriday);
        showDaySchedules(fridaySlots);
      }
    });
    lblSaturday.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        greyoutDayLabels();
        highlightDayLabel(lblSaturday);
        showDaySchedules(saturdaySlots);
      }
    });
    lblSunday.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        greyoutDayLabels();
        highlightDayLabel(lblSunday);
        showDaySchedules(sundaySlots);
      }
    });
  }

  private void showDaySchedules(List<Schedule> slots) {
    if (recyclerView == null)
      return;
    changeTitle(slots);
    adapter.setSchedules(slots);
    adapter.notifyDataSetChanged();
  }

  private void greyoutDayLabels() {
    lblMonday.setTextColor(getResources().getColor(R.color.grey));
    lblTuesday.setTextColor(getResources().getColor(R.color.grey));
    lblWednesday.setTextColor(getResources().getColor(R.color.grey));
    lblThursday.setTextColor(getResources().getColor(R.color.grey));
    lblFriday.setTextColor(getResources().getColor(R.color.grey));
    lblSaturday.setTextColor(getResources().getColor(R.color.grey));
    lblSunday.setTextColor(getResources().getColor(R.color.grey));
  }

  private void highlightDayLabel(TextView label) {
    label.setTextColor(getResources().getColor(R.color.colorAccent));
  }

  @Override
  protected void onPostCreate(@Nullable Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    ControlFactory.getSchedulesController().onDisplay(this);
  }

  public void showSchedules(List<Schedule> slots) {
    initSlots(slots);
    View v = findViewById(R.id.drawer_layout);
    recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_schedules);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    adapter = new SchedulesRecyclerViewAdapter(mondaySlots);
    recyclerView.setAdapter(adapter);
    changeTitle(mondaySlots);
  }

  private void changeTitle(List<Schedule> slots) {
    if (slots.size() > 0) {
      String title = slots.get(0).getStartDate();
      setTitle(title);
    } else {
      setTitle("Schedules");
    }
  }

  private void initSlots(List<Schedule> slots) {
    allSlots = slots;
    mondaySlots = new ArrayList<>();
    tuesdaySlots = new ArrayList<>();
    wednesdaySlots = new ArrayList<>();
    thursdaySlots = new ArrayList<>();
    fridaySlots = new ArrayList<>();
    saturdaySlots = new ArrayList<>();
    sundaySlots = new ArrayList<>();

    for (Schedule s : allSlots) {
      String dayName = s.getDayName();
      if (dayName.equalsIgnoreCase(DAY_NAME_MONDAY)) {
        mondaySlots.add(s);
      } else if (dayName.equalsIgnoreCase(DAY_NAME_TUESDAY)) {
        tuesdaySlots.add(s);
      } else if (dayName.equalsIgnoreCase(DAY_NAME_WEDNESDAY)) {
        wednesdaySlots.add(s);
      } else if (dayName.equalsIgnoreCase(DAY_NAME_THURSDAY)) {
        thursdaySlots.add(s);
      } else if (dayName.equalsIgnoreCase(DAY_NAME_FRIDAY)) {
        fridaySlots.add(s);
      } else if (dayName.equalsIgnoreCase(DAY_NAME_SATURDAY)) {
        saturdaySlots.add(s);
      } else if (dayName.equalsIgnoreCase(DAY_NAME_SUNDAY)) {
        sundaySlots.add(s);
      }
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.schedules, menu);
    if (!currentUser.isAdmin() && !currentUser.isSupervisor()) {
      menu.findItem(R.id.action_create_new).setVisible(false);
    }
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == R.id.action_create_new) {
      ControlFactory.getScheduleDetailController().startCreateUseCase();
      return true;
    }
    if (id == R.id.action_go_week) {
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
              int weekNo = calendar.get(Calendar.WEEK_OF_YEAR);
              ControlFactory.getSchedulesController().onDisplay(weekNo);
            }
          }, mYear, mMonth, mDay);
      datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
      datePickerDialog.show();
      return true;
    }
    if (id == R.id.action_copy_week) {
      dialog = new Dialog(this);
      dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
      dialog.setContentView(R.layout.dialog_copy_week);
      dialog.show();
      sourceDate = (TextView) dialog.findViewById(R.id.dialog_source_date);
      sourceDate.setText(ScheduleUtil.toString_yyyy_MM_dd(ScheduleUtil.getToday()));
      targetDate = (TextView) dialog.findViewById(R.id.dialog_target_date);
      targetDate.setText(ScheduleUtil.toString_yyyy_MM_dd(ScheduleUtil.getToday()));
      Button copy_save = (Button) dialog.findViewById(R.id.dialog_save);
      sourceDate.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          getDatePicker("source");
        }
      });
      targetDate.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          getDatePicker("target");
        }
      });
      copy_save.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v) {
          List<String> stringParams = new ArrayList<>();
          source = ScheduleUtil.getDate_from_week_number(targetWeekNo);
          source.set(Calendar.HOUR_OF_DAY, 0);
          source.set(Calendar.MINUTE, 0);
          source.set(Calendar.SECOND, 0);
          target = ScheduleUtil.getDate_from_week_number(targetWeekNo);
          target.add(Calendar.DAY_OF_WEEK, 6);
          target.set(Calendar.HOUR_OF_DAY, 23);
          target.set(Calendar.MINUTE, 59);
          target.set(Calendar.SECOND, 59);
          stringParams.add(ScheduleUtil.toString_yyyy_MM_dd_HH_mm_ss(source.getTime()));
          stringParams.add(ScheduleUtil.toString_yyyy_MM_dd_HH_mm_ss(target.getTime()));
          stringParams.add(Integer.toString(sourceWeekNo));
          stringParams.add(Integer.toString(targetWeekNo));
          dialog.dismiss();
          ControlFactory.getSchedulesController().copySchedules(stringParams);
        }
      });
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  private void getDatePicker(final String string){
    final Calendar c = Calendar.getInstance();
    mYear = c.get(Calendar.YEAR);
    mMonth = c.get(Calendar.MONTH);
    mDay = c.get(Calendar.DAY_OF_MONTH);
    sourceWeekNo = c.get(Calendar.WEEK_OF_YEAR);
    targetWeekNo = c.get(Calendar.WEEK_OF_YEAR);
    DatePickerDialog datePickerDialog = new DatePickerDialog(this,
        new DatePickerDialog.OnDateSetListener() {
          @Override
          public void onDateSet(DatePicker view, int year,
                                int monthOfYear, int dayOfMonth) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, monthOfYear, dayOfMonth);
            selectedDate = calendar;
            if (string.equals("source")){
              sourceWeekNo = calendar.get(Calendar.WEEK_OF_YEAR);
              sourceDate.setText(ScheduleUtil.toString_yyyy_MM_dd(selectedDate.getTime()));
            } else {
              targetWeekNo = calendar.get(Calendar.WEEK_OF_YEAR);
              targetDate.setText(ScheduleUtil.toString_yyyy_MM_dd(selectedDate.getTime()));
            }
          }
        }, mYear, mMonth, mDay);
    datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
    datePickerDialog.show();
  }

  public void showCopySaveSuccess() {
    Toast.makeText(this, "Copy successful.", Toast.LENGTH_SHORT).show();
  }

  public void showCopyOverlap() {
    Toast.makeText(this, "Schedule Overlap. Please change Target Week",
        Toast.LENGTH_SHORT).show();
  }

  public void showCopySchedules() {
    ControlFactory.getSchedulesController().onDisplay(targetWeekNo);
  }
}
