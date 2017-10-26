package sg.edu.nus.iss.ft08.siacdm.user;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatCheckBox;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.ft08.siacdm.ControlFactory;
import sg.edu.nus.iss.ft08.siacdm.SiaCdmBaseActivity;
import sg.edu.nus.iss.ft08.siacdm.R;
import sg.edu.nus.iss.ft08.siacdm.model.Role;
import sg.edu.nus.iss.ft08.siacdm.model.User;

public class UserDetailActivity extends SiaCdmBaseActivity {
  private User current;
  private EditText txtUserID;
  private EditText txtFullName;
  private EditText txtPassword;
  private TextView txtSlots;
  private AppCompatCheckBox chkTechnician;
  private AppCompatCheckBox chkSupervisor;
  private AppCompatCheckBox chkSystemAdmin;

  Boolean technicianBoolean;
  Boolean adminBoolean;
  Boolean supervisorBoolean;

  Role role;
  List<Role> roles = new ArrayList<>();

  public void setCurrent(User obj) {
    current = obj;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_user_detail);
    initCommonUi();
    initContentUi();
  }

  @Override
  protected void onPostCreate(@Nullable Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    ControlFactory.getUserDetailController().onDisplay(this);
    bindValues();
  }

  private void initContentUi() {
    View v = findViewById(R.id.drawer_layout);
    txtUserID = (EditText) v.findViewById(R.id.user_ID_txt);
    txtFullName = (EditText) v.findViewById(R.id.user_full_name);
    txtPassword = (EditText) v.findViewById(R.id.user_password);
    chkSupervisor = (AppCompatCheckBox) v.findViewById(R.id.supervisor_checkbox);
    chkSystemAdmin = (AppCompatCheckBox) v.findViewById(R.id.systemadmin_checkbox);
    chkTechnician = (AppCompatCheckBox) v.findViewById(R.id.technician_checkbox);
    txtSlots = (TextView) findViewById(R.id.slot_user);

    txtSlots.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        ControlFactory.getSchedulesController().startUseCase(current);
      }
    });
  }

  private void bindValues() {
    if (current != null) {
      txtUserID.setText(current.getUserId());
      txtFullName.setText(current.getFullName());
      txtPassword.setText(current.getPassword());
      txtSlots.setText(Integer.toString(current.getAssignedSlot()));
      for (Role role : current.getRoles()) {
        if (role.getRole_id().equals("admin")) {
          AppCompatCheckBox adminReloadChk = (AppCompatCheckBox) findViewById(R.id.systemadmin_checkbox);
          adminReloadChk.setChecked(true);
        }
        if (role.getRole_id().equals("technician")) {
          AppCompatCheckBox technicianReloadChk = (AppCompatCheckBox) findViewById(R.id.technician_checkbox);
          technicianReloadChk.setChecked(true);
        }
        if (role.getRole_id().equals("supervisor")) {
          AppCompatCheckBox supervisorReloadChk = (AppCompatCheckBox) findViewById(R.id.supervisor_checkbox);
          supervisorReloadChk.setChecked(true);
        }
      }
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.user_detail, menu);
    return true;
  }
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == R.id.action_delete_user) {
      delete();
      return true;
    }
    if (id == R.id.action_save_user) {
      setRoles();
      save();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  private void setRoles() {
    if (chkSupervisor.isChecked()) {
      role = new Role();
      supervisorBoolean = true;
      role.setRole_id((String) chkSupervisor.getText());
      roles.add(role);
    }
    if (chkSystemAdmin.isChecked()) {
      role = new Role();
      adminBoolean = true;
      role.setRole_id((String) chkSystemAdmin.getText());
      roles.add(role);
    }
    if (chkTechnician.isChecked()) {
      role = new Role();
      technicianBoolean = true;
      role.setRole_id((String) chkTechnician.getText());
      roles.add(role);
    }
  }

  private void save() {
    if (current == null) {
      User newRecord = new User();
      newRecord.setUserId(txtUserID.getText().toString());
      newRecord.setFullName(txtFullName.getText().toString());
      newRecord.setPassword(txtPassword.getText().toString());
      newRecord.setRoles(roles);
      boolean cancel = false;
      View focusView = null;
      if (TextUtils.isEmpty(newRecord.getUserId())) {
        txtUserID.setError(getString(R.string.UserIDString));
        focusView = txtUserID;
        cancel = true;
      }
      if (TextUtils.isEmpty(newRecord.getPassword())) {
        txtPassword.setError(getString(R.string.UserPassword));
        focusView = txtPassword;
        cancel = true;
      }
      if (cancel) {
        focusView.requestFocus();
      } else {
        ControlFactory.getUserDetailController().create(newRecord);
      }
    } else {
      current.setUserId(txtUserID.getText().toString());
      current.setFullName(txtFullName.getText().toString());
      current.setPassword(txtPassword.getText().toString());
      current.setRoles(roles);
      View focusView = null;
      boolean cancel = false;
      if (TextUtils.isEmpty(current.getUserId())) {
        txtUserID.setError(getString(R.string.UserIDString));
        focusView = txtUserID;
        cancel = true;
      }
      if (TextUtils.isEmpty(current.getPassword())) {
        txtPassword.setError(getString(R.string.UserPassword));
        focusView = txtPassword;
        cancel = true;
      }
      if (cancel) {
        focusView.requestFocus();
      } else {
        ControlFactory.getUserDetailController().update(current);
      }
    }
  }

  private void delete() {
    ControlFactory.getUserDetailController().delete(current);
  }

  public void showSaveSuccess() {
    Toast.makeText(this, "Save successful.", Toast.LENGTH_SHORT).show();
  }

  public void showDeleteSuccess(Boolean status) {
    if (status) {
      ControlFactory.getUsersController().startUseCase();
      Toast.makeText(this, "Delete successful.", Toast.LENGTH_SHORT).show();
    } else {
      Toast.makeText(this, "User Assigned, Cannot Delete", Toast.LENGTH_SHORT).show();
    }
  }
}
