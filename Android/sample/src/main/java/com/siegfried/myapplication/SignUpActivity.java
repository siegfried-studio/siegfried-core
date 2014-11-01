package com.siegfried.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.siegfried.core.APIError;
import com.siegfried.core.APIHandler;
import com.siegfried.core.ServiceManager;

import org.json.JSONObject;


public class SignUpActivity extends Activity {

    private AQuery aq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        aq = new AQuery(this);

        aq.id(R.id.button_sign_up).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });


    }

    private void signUp() {
        String account = aq.id(R.id.edit_account).getEditText().toString();
        String password = aq.id(R.id.edit_password).getEditText().toString();
        ServiceManager.getInstance().signUp(aq, account, password, new APIHandler() {
            @Override
            public void onResponseAvailable() {
                Toast.makeText(SignUpActivity.this, "HHHH", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(APIError error) {
                Toast.makeText(SignUpActivity.this, "Error.Code:" + error.getErrorCode(), Toast.LENGTH_SHORT).show();
            }

            protected void handleRestData(JSONObject json) {
                Toast.makeText(SignUpActivity.this, json.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_up, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
