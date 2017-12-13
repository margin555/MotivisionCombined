package com.example.appsteam.motivision.circular_Progress;


import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.appsteam.motivision.Home.Add_Activity;
import com.example.appsteam.motivision.Home.Home_Activity;
import com.example.appsteam.motivision.Home.Pofile_Activity;
import com.example.appsteam.motivision.R;
import com.robinhood.spark.SparkAdapter;
import com.robinhood.spark.SparkView;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class Progress extends AppCompatActivity {

    private TextView txtProgress,txtProgress1;
    private ProgressBar progressBar;
    BottomBar bottomBar;
    Intent i;
    private int pStatus = 0;
    private Handler handler = new Handler();
    float[] floatArray =
            new float[] { 21.1f, 9.9f, 1.8f, 17.5f, 28.1f ,1.5f, 41.1f, 54.9f, 9.8f, 31.5f, 28.1f ,1.5f, 28.1f, 9.9f, 19.8f, 8.5f, 61.1f ,16.5f};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress1);
        bottomBar=(BottomBar)findViewById(R.id.bottombar4);
        txtProgress = (TextView) findViewById(R.id.txtProgress);
        txtProgress1 = (TextView) findViewById(R.id.txtProgress1);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        bottomBar.setDefaultTab(R.id.tab_group);

        bottomBar.setOnTabSelectListener(new OnTabSelectListener()

        {
            @Override
            public void onTabSelected(@IdRes int tabId) {

                switch (tabId) {

                    case R.id.tab_home:
                        i = new Intent(Progress.this, Home_Activity.class);
                        startActivity(i);
                        break;
                    case R.id.tab_add:
                        i = new Intent(Progress.this, Add_Activity.class);
                        startActivity(i);

                    case R.id.tab_user:
                        i=new Intent(Progress.this,Pofile_Activity.class);
                        startActivity(i);



                }
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (pStatus <= 100) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(pStatus);
                            txtProgress.setText(pStatus + " %");

                            txtProgress1.setText("MONTHLY  PROGRESS");
                            txtProgress1.setTextColor(Color.parseColor("#FFFFFFFF"));
                            txtProgress.setTextColor(Color.parseColor("#FFFFFFFF"));

                        }
                    });
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    pStatus++;
                }
            }
        }).start();

        SparkView sparkView = (SparkView) findViewById(R.id.sparkview);

        sparkView.setAdapter(new MyAdapter(floatArray));

    }

    public class MyAdapter extends SparkAdapter {
        private float[] yData;


        public MyAdapter(float[] yData) {
            this.yData = yData;
        }



        @Override
        public int getCount() {
            return yData.length;
        }

        @Override
        public Object getItem(int index) {
            return yData[index];
        }

        @Override
        public float getY(int index) {
            return yData[index];
        }
    }
}

