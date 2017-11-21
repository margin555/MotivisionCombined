package com.example.appsteam.motivision.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appsteam.motivision.R;

public class ResetPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        Button reset=(Button)findViewById(R.id.reset);
        TextView reset_login=(TextView)findViewById(R.id.reset_login);
        
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
                Toast.makeText(ResetPassword.this, "Success! Your Password has been changed!", Toast.LENGTH_SHORT).show();
            }
        });
        
    }
}
