package com.siegfried.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
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

    private boolean verifyFields() {
        String account = aq.id(R.id.edit_account).getEditText().toString();

        if (account.isEmpty()) {
            Toast.makeText(this, "Please Enter Account.", Toast.LENGTH_SHORT).show();
            return false;
        }

        String password = aq.id(R.id.edit_password).getEditText().toString();
        if (password.isEmpty()) {
            Toast.makeText(this, "Please Enter Password.", Toast.LENGTH_SHORT).show();
            return false;
        }

        String confirmPassword = aq.id(R.id.edit_confirm_password).getEditText().toString();
        if (password.isEmpty()) {
            Toast.makeText(this, "Please Enter Confirm Password.", Toast.LENGTH_SHORT).show();
            return false;
        }


        return true;
    }

    private void signUp() {
        String account = aq.id(R.id.edit_account).getEditText().toString();
        String password = aq.id(R.id.edit_password).getEditText().toString();

        final ProgressDialog dialog = ProgressDialog.show(this, "", "Loading");
        ServiceManager.getInstance().signUp(aq, account, password, new APIHandler() {
            @Override
            public void onResponseAvailable() {
                dialog.dismiss();
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
