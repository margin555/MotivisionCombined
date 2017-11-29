package com.example.appsteam.motivision.common;

import android.content.Context;
import android.widget.Toast;

import com.example.appsteam.motivision.R;

/**
 * Created by appsteam on 22-11-2017.
 */

public class Utility {
    public static void setToast(Context appContex,String msg)
    {
        Toast.makeText(appContex, msg, Toast.LENGTH_SHORT).show();
    }
}
