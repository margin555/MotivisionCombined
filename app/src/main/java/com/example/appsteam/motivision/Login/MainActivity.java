package com.example.appsteam.motivision.Login;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.appsteam.motivision.R;
import com.example.appsteam.motivision.circular_Progress.Progress;
import com.example.appsteam.motivision.common.MotivisionDbManager;
import com.example.appsteam.motivision.common.Utility;


public class MainActivity extends AppCompatActivity {
    private EditText username, password,f_Answer;
    private Button login;
    private TextView register, forgot_password,security_frgt;
    private Utility utility;
    private String musername, mpassword;
    private MotivisionDbManager mdbmanager;
    public static final String MOTIVISION_PREFS_NAME = "MyPrefs";
    Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        register = (TextView) findViewById(R.id.textView4);
        forgot_password = (TextView) findViewById(R.id.forgotpswd);
        login = (Button) findViewById(R.id.login_button);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        mdbmanager = new MotivisionDbManager(getApplicationContext());
        final SharedPreferences.Editor editor = getSharedPreferences(MOTIVISION_PREFS_NAME, MODE_PRIVATE).edit();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musername = username.getText().toString();
                mpassword = password.getText().toString();
                if (musername.equals("") || mpassword.equals("")) {
                    utility.setToast(getApplicationContext(), getResources().getString(R.string.Enter_All));
                } else if (mpassword.length() > 7) {
                    Cursor cursor = mdbmanager.getDataUsernamePassword(musername, mpassword);
                    if (cursor.getCount() == 0) {
                        utility.setToast(getApplicationContext(), getResources().getString(R.string.Invalid_usrname_passwd));
                        username.setText("");
                        password.setText("");
                    } else {
                        cursor.moveToFirst();
                        String mGetDb_Username = cursor.getString(cursor.getColumnIndex(mdbmanager.MOTIVISION_REGISTRATION_COLUMN_USERNAME));
                        String mGetDb_Passwd = cursor.getString(cursor.getColumnIndex(mdbmanager.MOTIVISION_REGISTRATION_COLUMN_PASSWORD));
                        if (musername.equals(mGetDb_Username) && mpassword.equals(mGetDb_Passwd)) {
                            Intent cir_prgrs = new Intent(MainActivity.this, Progress.class);
                            startActivity(cir_prgrs);
                        }

                    }
                } else {
                    utility.setToast(getApplicationContext(), getResources().getString(R.string.passwd_length));
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reg_intent = new Intent(MainActivity.this, Registration.class);
                startActivity(reg_intent);
            }
        });


        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musername = username.getText().toString();
                 cursor = mdbmanager.getSecurityquestion(musername);
                if (musername.equals("")) {
                    utility.setToast(getApplicationContext(), getResources().getString(R.string.Enter_All));

                } else if (cursor.getCount() == 0) {
                    utility.setToast(getApplicationContext(), getResources().getString(R.string.Enter_a_valid_Username));
                } else if (cursor.getCount() != 0) {
                    cursor.moveToFirst();
                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                    View mView = getLayoutInflater().inflate(R.layout.activity_forget_password, null);
                    security_frgt = (TextView)mView.findViewById(R.id.security_frgt);
                    String mquestion = cursor.getString(cursor.getColumnIndex(MotivisionDbManager.MOTIVISION_REGISTRATION_COLUMN_SECURITY_QUESTION));
                    security_frgt.setText(mquestion);

                    f_Answer = (EditText) mView.findViewById(R.id.Answer);
                    mBuilder.setPositiveButton("Request", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            String m_answer, db_answer;

                            m_answer = f_Answer.getText().toString();
                            db_answer = cursor.getString(cursor.getColumnIndex(MotivisionDbManager.MOTIVISION_REGISTRATION_COLUMN_ANSWER));
                            if (m_answer.equals(db_answer)) {
                                editor.putString("key_username",musername);
                                editor.commit();
                                Intent reset_intent = new Intent(MainActivity.this, ResetPassword.class);
                                startActivity(reset_intent);
                            } else {
                                utility.setToast(getApplicationContext(), getResources().getString(R.string.wrong_answer));
                            }
                        }
                    });

                    mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    mBuilder.setView(mView);
                    final AlertDialog dialog = mBuilder.create();
                    dialog.show();


                }
            }
        });


    }
}
