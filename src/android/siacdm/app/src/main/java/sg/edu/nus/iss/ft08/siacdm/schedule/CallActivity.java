package sg.edu.nus.iss.ft08.siacdm.schedule;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import sg.edu.nus.iss.ft08.siacdm.R;

/**
 * Created by Rach on 27/10/2017.
 */

public class CallActivity extends AppCompatActivity {
  private Button buttonCall, buttonMsg;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_call);

    buttonCall = (Button) findViewById(R.id.btn_call);
    buttonMsg = (Button) findViewById(R.id.btn_message);

    buttonCall.setOnClickListener(new View.OnClickListener() {
      public void onClick(View arg0) {
        Log.d("check","check");
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel: 81878660"));
        startActivity(callIntent);
      }
    });

    buttonMsg.setOnClickListener(new View.OnClickListener() {
      public void onClick(View arg0) {
        Log.d("check1","check1button message");
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms: 81878660" ));
        intent.putExtra("sms_body", "Please reach out to xxx");
        startActivity(intent);
      }
    });
  }
}
