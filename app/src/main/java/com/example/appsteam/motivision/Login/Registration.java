package com.example.appsteam.motivision.Login;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appsteam.motivision.R;
import com.example.appsteam.motivision.common.MotivisionDbManager;
import com.example.appsteam.motivision.common.Utility;

public class Registration extends AppCompatActivity {
    private Spinner dropdown;
    private TextView login_page;
    private EditText reg_username, reg_passwd, reg_confirm_passwd, reg_answer;
    private Button reg_btn;
    private String mUsername, mPassword, mQuestion, mConfirmPassword, mAnswer;
    MotivisionDbManager motivisionDbManager;
    Utility utility = new Utility();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        dropdown = (Spinner) findViewById(R.id.security_qstn);
        login_page = (TextView) findViewById(R.id.login_page);
        reg_username = (EditText) findViewById(R.id.registration_username);
        reg_passwd = (EditText) findViewById(R.id.registration_password);
        reg_confirm_passwd = (EditText) findViewById(R.id.registration_cnfrmpassword);
        reg_answer = (EditText) findViewById(R.id.registration_answer);
        reg_btn = (Button) findViewById(R.id.registration_button);
        motivisionDbManager=new MotivisionDbManager(getApplicationContext());

        String[] items = new String[]{"What’s your dream destination?", "Where were you born?", "What’s the name of your favorite Teacher?", "What’s your best past time hobby?"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, items);
        dropdown.setPrompt("Security Question!");
        dropdown.setAdapter(adapter);
        dropdown.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter,
                        R.layout.contact_spinner_row_nothing_selected,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));


        login_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logincall();
            }
        });

        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUsername = reg_username.getText().toString();
                mPassword = reg_passwd.getText().toString();
                mConfirmPassword = reg_confirm_passwd.getText().toString();
                mQuestion = dropdown.getSelectedItem().toString();
                mAnswer = reg_answer.getText().toString();
                int value = motivisionDbManager.numOfRows();
                boolean flag;
                if (value == 0) {
                    flag = true;
                } else {
                    flag = false;
                }
                if (flag == true) {

                    if (mUsername.isEmpty() || mPassword.isEmpty() || mQuestion.isEmpty() || mConfirmPassword.isEmpty() || mAnswer.isEmpty()) {
                        utility.setToast(getApplicationContext(), getResources().getString(R.string.Enter_All));
                    } else if (mPassword.length() < 8) {
                        utility.setToast(getApplicationContext(), getResources().getString(R.string.passwd_length));
                    } else if ((mPassword).equals(mConfirmPassword)) {
                        motivisionDbManager.insertRegistrationDetails(mUsername, mPassword, mQuestion, mAnswer, "");
                        utility.setToast(getApplicationContext(), getResources().getString(R.string.reg_success));
                        logincall();
                    } else {
                        utility.setToast(getApplicationContext(), "Passwords Don't match");
                    }
                }
                if (flag == false) {

                    Cursor cursor = motivisionDbManager.getRegistrationFullData(mUsername);
                    if (cursor.getCount() == 0) {
                        cursor.moveToFirst();
                        if (mUsername.isEmpty() || mPassword.isEmpty() || mConfirmPassword.isEmpty() || mQuestion.isEmpty() || mAnswer.isEmpty()) {
                            utility.setToast(getApplicationContext(), getResources().getString(R.string.Enter_All));
                        } else if (mPassword.length() < 8) {
                            utility.setToast(getApplicationContext(), getResources().getString(R.string.passwd_length));
                        } else if (mPassword.equals(mConfirmPassword)) {
                            motivisionDbManager.insertRegistrationDetails(mUsername, mPassword, mConfirmPassword, mQuestion, mAnswer);
                            utility.setToast(getApplicationContext(), getResources().getString(R.string.reg_success));
                            logincall();
                        } else {
                            utility.setToast(getApplicationContext(), "Passwords Don't Match");
                        }

                    } else
                        utility.setToast(getApplicationContext(), getResources().getString(R.string.usr_exists));
                }

            }
        });
    }


    private void logincall() {
        Intent log_intent = new Intent(Registration.this, MainActivity.class);
        startActivity(log_intent);
    }
}
