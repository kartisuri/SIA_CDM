package sg.edu.nus.iss.ft08.siacdm.authenticate;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import sg.edu.nus.iss.ft08.siacdm.ControlFactory;
import sg.edu.nus.iss.ft08.siacdm.MainController;
import sg.edu.nus.iss.ft08.siacdm.R;

public class LoginActivity extends AppCompatActivity {
    private EditText txtUserId;
    private EditText txtPassword;
    private Button btnSignIn;
    private View vwProgress;
    private View vwLoginForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUi();
        initEvents();
        MainController.setApp(getApplication());
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        ControlFactory.getLoginController().onDisplay(this);
    }

    private void initUi() {
        setContentView(R.layout.activity_login);

        txtUserId = (EditText) findViewById(R.id.email);
        txtPassword = (EditText) findViewById(R.id.password);
        btnSignIn = (Button) findViewById(R.id.email_sign_in_button);
        vwLoginForm = findViewById(R.id.login_form);
        vwProgress = findViewById(R.id.login_progress);
        getSupportActionBar().hide();
    }

    private void initEvents() {
        btnSignIn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
        txtPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });
    }

    private void attemptLogin() {
        txtUserId.setError(null);
        txtPassword.setError(null);
        String userId = txtUserId.getText().toString();
        String password = txtPassword.getText().toString();
        boolean cancel = false;
        View focusView = null;
        if (TextUtils.isEmpty(password)) {
            txtPassword.setError(getString(R.string.error_field_required));
            focusView = txtPassword;
            cancel = true;
        }
        if (TextUtils.isEmpty(userId)) {
            txtUserId.setError(getString(R.string.error_field_required));
            focusView = txtUserId;
            cancel = true;
        }
        if (cancel) {
            focusView.requestFocus();
        } else {
            showLoading();
            ControlFactory.getLoginController().login(userId, password);
        }
    }

    public void showLoading() {
        showProgress(true);
    }

    public void hideLoading() {
        showProgress(false);
    }

    public void showLoginFailure() {
        Toast.makeText(this, "Invalid user ID or password.", Toast.LENGTH_SHORT).show();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);
        vwLoginForm.setVisibility(show ? View.GONE : View.VISIBLE);
        vwLoginForm.animate().setDuration(shortAnimTime).alpha(
                show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                vwLoginForm.setVisibility(show ? View.GONE : View.VISIBLE);
            }
        });
        vwProgress.setVisibility(show ? View.VISIBLE : View.GONE);
        vwProgress.animate().setDuration(shortAnimTime).alpha(
                show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                vwProgress.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });
    }
}
