package com.example.appsteam.motivision.ModelClass;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.example.appsteam.motivision.R;

import static android.content.ContentValues.TAG;

/**
 * Created by appsteam on 06-12-2017.
 */

public class OneFragment extends Fragment {
    CalendarView calendarview;

    public OneFragment() {

    }


    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        calendarview = new CalendarView(getContext());

        calendarview.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                String data = dayOfMonth + "/" + month + "/" + year;
                Log.d(TAG, "onSelectedDayChange: dd/mm/yyyy" + data);
            }
        });


    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    /*@NonNull
       /* @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
          final Calendar calendar= Calendar.getInstance();
            int yy=calendar.get(Calendar.YEAR);
            int mm=calendar.get(Calendar.MONTH);
            int dd=calendar.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(),this,yy,mm,dd);

        }
    */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.one_fragment, container, false);


    }

}