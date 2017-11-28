package com.example.appsteam.motivision.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appsteam.motivision.R;
import com.example.appsteam.motivision.common.MotivisionDbManager;
import com.example.appsteam.motivision.common.Utility;

import static com.example.appsteam.motivision.Login.MainActivity.MOTIVISION_PREFS_NAME;

public class ResetPassword extends AppCompatActivity {
private EditText reset_passwd,resert_cnfrm_passwd;
private String m_reset_passwd,m_confrm_password;
private Utility utility;
private MotivisionDbManager motivisionDbManager;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
    prefs = getSharedPreferences(MOTIVISION_PREFS_NAME, MODE_PRIVATE);
        Button reset=(Button)findViewById(R.id.reset);
        TextView reset_login=(TextView)findViewById(R.id.reset_login);
        reset_passwd=(EditText)findViewById(R.id.reset_passwd);
        resert_cnfrm_passwd=(EditText)findViewById(R.id.reset_cnfrmpswd);
        motivisionDbManager=new MotivisionDbManager(getApplicationContext());
        reset_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reset_login=new Intent(ResetPassword.this,MainActivity.class);
                startActivity(reset_login);
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m_reset_passwd=reset_passwd.getText().toString();
                m_confrm_password=resert_cnfrm_passwd.getText().toString();
                String frgt_username;
                frgt_username=prefs.getString("key_username","");
                if(m_confrm_password.equals(m_reset_passwd)) {
                    motivisionDbManager.updatePin(frgt_username,m_confrm_password);
                    Toast.makeText(ResetPassword.this, "Success! Your Password has been changed!", Toast.LENGTH_SHORT).show();
                }
                else {
                    utility.setToast(getApplicationContext(),getResources().getString(R.string.Passwords_not_matching));
                }
            }
        });
        
    }
}
