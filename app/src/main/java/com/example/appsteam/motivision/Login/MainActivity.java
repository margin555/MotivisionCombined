package com.example.appsteam.motivision.Login;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.appsteam.motivision.R;
import com.example.appsteam.motivision.circular_Progress.Progress;
import com.example.appsteam.motivision.common.MotivisionDbManager;
import com.example.appsteam.motivision.common.Utility;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private EditText username,password;
    private Button login;
    private  TextView  register,forgot_password;
  private   Utility utility;
    private String  musername,mpassword;
    private MotivisionDbManager mdbmanager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         register=(TextView)findViewById(R.id.textView4);
        forgot_password=(TextView)findViewById(R.id.forgotpswd);
       login=(Button)findViewById(R.id.login_button);
       username=(EditText)findViewById(R.id.username);
       password=(EditText)findViewById(R.id.password);
        mdbmanager =new MotivisionDbManager(getApplicationContext());

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musername = username.getText().toString();
                mpassword = password.getText().toString();
                if (musername.equals("") || mpassword.equals("")) {
                    utility.setToast(getApplicationContext(), getResources().getString(R.string.Enter_All));
                } else if (mpassword.length() > 8) {
                    Cursor cursor = mdbmanager.getDataUsernamePassword(musername,mpassword);
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

            }}
            else{
                    utility.setToast(getApplicationContext(),getResources().getString(R.string.passwd_length));
                }}
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reg_intent=new Intent(MainActivity.this,Registration.class);
                startActivity(reg_intent);
            }
        });


        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.activity_forget_password, null);
                 musername=username.getText().toString();
Cursor cursor=mdbmanager.getRegistrationFullData(musername);
if(musername.equals())
                TextView security_frgt = (TextView) mView.findViewById(R.id.security_frgt);
                final EditText mAnswer = (EditText) mView.findViewById(R.id.Answer);
                mBuilder.setPositiveButton("Request", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

               Intent reset_intent=new Intent(MainActivity.this,ResetPassword.class);
               startActivity(reset_intent);
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
        });


    }
}
