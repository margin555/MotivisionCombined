package com.example.appsteam.motivision.Home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.appsteam.motivision.Adapters.Adapter_Profile;
import com.example.appsteam.motivision.Interface.OnItemClickListener;
import com.example.appsteam.motivision.ModelClass.Model_Motivibe_Activities;
import com.example.appsteam.motivision.R;
import com.example.appsteam.motivision.XML_class.Rounded_ImageView;
import com.example.appsteam.motivision.common.DataBaseHandler;

import java.util.ArrayList;
import java.util.List;

public class Edit_Profile_Activity extends AppCompatActivity implements OnItemClickListener {
    Rounded_ImageView edtimage;
    Button save;
    RecyclerView recyclerView;
    DataBaseHandler dataBaseHandler;
    List<Model_Motivibe_Activities> mlist = new ArrayList<>();
    Adapter_Profile adapter_profile;
    Model_Motivibe_Activities modelMotivibe_activities;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__profile_);
        edtimage = (Rounded_ImageView) findViewById(R.id.editimg);
        save = (Button) findViewById(R.id.edtsave);
        preparedata();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Edit_Profile_Activity.this, Pofile_Activity.class);
                startActivity(i);
            }
        });


        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.whatsapp);

        edtimage.setImageBitmap(icon);
    }


    @Override
    public void onClick(View view, int position) {
        modelMotivibe_activities = mlist.get(position);

        dataBaseHandler.deleteActivity(modelMotivibe_activities.getAct_name());
        preparedata();

    }

    public void preparedata() {
        recyclerView = (RecyclerView) findViewById(R.id.editrecycler);
        dataBaseHandler = new DataBaseHandler(this);
        mlist = dataBaseHandler.getAllActivities();
        adapter_profile = new Adapter_Profile(mlist, this);
        recyclerView.setAdapter(adapter_profile);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter_profile.setitemClickListener(this);


    }

}