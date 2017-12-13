package com.example.appsteam.motivision.Home;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appsteam.motivision.ModelClass.Model_Motivibe_Activities;
import com.example.appsteam.motivision.R;
import com.example.appsteam.motivision.common.DataBaseHandler;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class Add_Activity extends AppCompatActivity {
    DataBaseHandler dataBaseHandler;
    Button btnadd;
    EditText ed_activity, ed_des;
    Home_Activity homeActivity;
    BottomBar bottomBar;
    Intent i;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(Add_Activity.this, Home_Activity.class);
        startActivity(i);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_);
        dataBaseHandler = new DataBaseHandler(this);
        homeActivity = new Home_Activity();
        bottomBar=(BottomBar)findViewById(R.id.bottombar3);
        bottomBar.setDefaultTab(R.id.tab_add);

        bottomBar.setOnTabSelectListener(new OnTabSelectListener()

        {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {

                    case R.id.tab_user:
                        i=new Intent(Add_Activity.this,Pofile_Activity.class);
                        startActivity(i);
                        break;

                }
                if (tabId==R.id.tab_home){
                    i = new Intent(Add_Activity.this, Home_Activity.class);
                    startActivity(i);

                }
            }


        });
        ed_activity = (EditText) findViewById(R.id.activity);
        btnadd = (Button) findViewById(R.id.addactivity);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((ed_activity.getText().toString().equals(""))) {
                    Toast.makeText(Add_Activity.this, "Enter Activity Name....", Toast.LENGTH_SHORT).show();
                } else {
                    dataBaseHandler.addActivities(new Model_Motivibe_Activities(ed_activity.getText().toString(), ""));

                    Intent i = new Intent(Add_Activity.this, Home_Activity.class);


                    startActivity(i);

                }


            }
        });


    }
}
