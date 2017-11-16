package com.example.appsteam.motivision.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.appsteam.motivision.R;

public class Registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Spinner dropdown = (Spinner)findViewById(R.id.security_qstn);
        TextView login=(TextView)findViewById(R.id.login_page);
        String[] items = new String[]{"Security Question ?","What’s your dream destination?", "Where were you born?","What’s the name of your favorite Teacher?","What’s your best past time hobby?"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.spinner_item, items);
        dropdown.setAdapter(adapter);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent log_intent=new Intent(Registration.this,MainActivity.class);
                startActivity(log_intent);
            }
        });
    }
}
