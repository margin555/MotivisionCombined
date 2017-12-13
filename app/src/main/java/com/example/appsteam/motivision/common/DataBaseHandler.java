package com.example.appsteam.motivision.common;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.appsteam.motivision.ModelClass.Model_Motivibe_Activities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by appsteam on 06-12-2017.
 */
public class DataBaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Activities";
    private static final String TABLE_ACTIVITY = "Activity_Table";
    private static final String KEY_ACt_NAME = "actname";
    private static final String KEY_ACT_DES = "actdes";
    private static final String KEY_ID = "id";

    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String ACT_TABLE = "CREATE TABLE " + TABLE_ACTIVITY + " ( " + KEY_ID + " INTEGER PRIMARY KEY ," + KEY_ACt_NAME + " TEXT ," + KEY_ACT_DES + " TEXT )";
        db.execSQL(ACT_TABLE);

    }

    public void addActivities(Model_Motivibe_Activities modelMotivibe_activities) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(KEY_ACt_NAME, modelMotivibe_activities.getAct_name());
        value.put(KEY_ACT_DES, modelMotivibe_activities.getAct_des());
        db.insert(TABLE_ACTIVITY, null, value);
        db.close();
    }

    public List<Model_Motivibe_Activities> getAllActivities() {


        List<Model_Motivibe_Activities> activitylist = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_ACTIVITY, null);
        if (cursor.moveToFirst()) {
            do {
                Model_Motivibe_Activities model_profile = new Model_Motivibe_Activities();
                // model_profile.setId(Integer.parseInt(cursor.getString(0)));
                model_profile.setAct_name(cursor.getString(1));
                model_profile.setAct_des(cursor.getString(2));
                activitylist.add(model_profile);
            }
            while (cursor.moveToNext());
        }

        return activitylist;

    }

    public void deleteActivity(String value) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ACTIVITY, KEY_ACt_NAME + " = ?", new String[]{String.valueOf(value)});
        db.close();
    }

    public int getActivity() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] id = {KEY_ID};
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_ACTIVITY + " WHERE " + KEY_ID + " =(SELECT MAX(" + KEY_ID + ") FROM " + TABLE_ACTIVITY + ")", null, null);
        cursor.moveToFirst();
        int i = cursor.getCount();
        cursor.close();
        return i;


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
