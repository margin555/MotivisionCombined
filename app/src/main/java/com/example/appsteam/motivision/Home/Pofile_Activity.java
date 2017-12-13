package com.example.appsteam.motivision.Home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.appsteam.motivision.Adapters.Adapter_Profile;
import com.example.appsteam.motivision.ModelClass.Model_Motivibe_Activities;
import com.example.appsteam.motivision.R;
import com.example.appsteam.motivision.XML_class.Rounded_ImageView;
import com.example.appsteam.motivision.circular_Progress.Progress;
import com.example.appsteam.motivision.common.DataBaseHandler;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

public class Pofile_Activity extends AppCompatActivity {
    Rounded_ImageView image1;
    Button edtbutton;
    Adapter_Profile adapter_profile;
    List<Model_Motivibe_Activities> mlist = new ArrayList<>();
    RecyclerView recyclerView;
    DataBaseHandler dataBaseHandler;
    BottomBar bottomBar;
    Intent i;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(Pofile_Activity.this, Home_Activity.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pofile_);

        image1 = (Rounded_ImageView) findViewById(R.id.imageView);
        edtbutton = (Button) findViewById(R.id.button1);
        bottomBar = (BottomBar) findViewById(R.id.bottombar2);

        bottomBar.setDefaultTab(R.id.tab_user);



        bottomBar.setOnTabSelectListener(new OnTabSelectListener()

        {
            @Override
            public void onTabSelected(@IdRes int tabId) {

                switch (tabId) {

                    case R.id.tab_home:
                        i = new Intent(Pofile_Activity.this, Home_Activity.class);
                        startActivity(i);
                        break;
                    case R.id.tab_add:
                        i = new Intent(Pofile_Activity.this, Add_Activity.class);
                        startActivity(i);
                        break;
                    case R.id.tab_group:
                        i = new Intent(Pofile_Activity.this, Progress.class);
                        startActivity(i);


                }
            }
        });


        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        dataBaseHandler = new DataBaseHandler(this);
        mlist = dataBaseHandler.getAllActivities();
        adapter_profile = new Adapter_Profile(mlist, this);
        recyclerView.setAdapter(adapter_profile);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        edtbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Pofile_Activity.this, Edit_Profile_Activity.class);
                startActivity(i);
            }
        });

        Bitmap icon;
        icon = BitmapFactory.decodeResource(getResources(), R.drawable.whatsapp);
        image1.setImageBitmap(icon);

    }

}
