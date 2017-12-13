package com.example.appsteam.motivision.Home;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.appsteam.motivision.Adapters.ViewPagerAdapter;
import com.example.appsteam.motivision.ModelClass.Model_Motivibe_Activities;
import com.example.appsteam.motivision.ModelClass.OneFragment;
import com.example.appsteam.motivision.R;
import com.example.appsteam.motivision.XML_class.CenteringTabLayout;
import com.example.appsteam.motivision.circular_Progress.Progress;
import com.example.appsteam.motivision.common.DataBaseHandler;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

public class Home_Activity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    DataBaseHandler dataBaseHandler;
    Intent i;
    CenteringTabLayout tabLayout;
    BottomBar bottomBar;
    ViewPager viewPager;
    List<Model_Motivibe_Activities> activitylist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);


        dataBaseHandler = new DataBaseHandler(this);
        activitylist = dataBaseHandler.getAllActivities();
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (CenteringTabLayout) findViewById(R.id.tab);
        bottomBar = (BottomBar) findViewById(R.id.bottombar1);
        tabLayout.setTabTextColors(
                getResources().getColor(R.color.colowhite),
                getResources().getColor(R.color.colowhite)
        );
        setupViewPager(viewPager);
        viewPager.setCurrentItem(activitylist.size());
        tabLayout.setupWithViewPager(viewPager);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener()

        {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.tab_user:
                        i = new Intent(Home_Activity.this, Pofile_Activity.class);
                        startActivity(i);
                        break;
                    case R.id.tab_add:
                        i = new Intent(Home_Activity.this, Add_Activity.class);
                        startActivity(i);
                        break;
                    case R.id.tab_group:
                        i = new Intent(Home_Activity.this, Progress.class);
                        startActivity(i);

                }
            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        if (activitylist.size() == 0) {
            Intent intent = new Intent(Home_Activity.this, Add_Activity.class);
            startActivity(intent);
        } else {
            for (int j = 0; j < activitylist.size(); j++) {
                adapter.addFragment(new OneFragment(), activitylist.get(j).getAct_name().toString());
                viewPager.setAdapter(adapter);
            }
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}