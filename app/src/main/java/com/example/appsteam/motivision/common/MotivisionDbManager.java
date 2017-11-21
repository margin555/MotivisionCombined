package com.example.appsteam.motivision.common;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by appsteam on 20-11-2017.
 */

public class MotivisionDbManager extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "motivisionDb.db";
    public static final String MOTIVISION_TABLE_REGISTRATION = "registration_table";
    public static final String MOTIVISION_REGISTRATION_COLUMN_ID = "id";
    public static final String MOTIVISION_REGISTRATION_COLUMN_USERNAME = "username";
    public static final String MOTIVISION_REGISTRATION_COLUMN_PIN = "pin";
    public static final String MOTIVISION_REGISTRATION_COLUMN_SECURITY_QUESTION = "security_question";
    public static final String MOTIVISION_REGISTRATION_COLUMN_ANSWER = "answer";
    public static final String MOTIVISION_REGISTRATION_COLUMN_PROFILE_PICTURE_URI = "profile_Picture";


    public MotivisionDbManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table " + MOTIVISION_TABLE_REGISTRATION + "(" + MOTIVISION_REGISTRATION_COLUMN_ID + "integer primary key, "
                + MOTIVISION_REGISTRATION_COLUMN_USERNAME + "text"
                +MOTIVISION_REGISTRATION_COLUMN_PIN+"text"
                +MOTIVISION_REGISTRATION_COLUMN_SECURITY_QUESTION+"text"
                +MOTIVISION_REGISTRATION_COLUMN_ANSWER+"text"
                +MOTIVISION_REGISTRATION_COLUMN_PROFILE_PICTURE_URI+"text)");



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean insertRegistrationDetails(String username, String pin, String security_question, String answer, String profilePicture) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MOTIVISION_REGISTRATION_COLUMN_USERNAME, username);
        contentValues.put(MOTIVISION_REGISTRATION_COLUMN_PIN, pin);
        contentValues.put(MOTIVISION_REGISTRATION_COLUMN_SECURITY_QUESTION, security_question);
        contentValues.put(MOTIVISION_REGISTRATION_COLUMN_ANSWER, answer);
        contentValues.put(MOTIVISION_REGISTRATION_COLUMN_PROFILE_PICTURE_URI, profilePicture);
        sqLiteDatabase.insert(MOTIVISION_TABLE_REGISTRATION, null, contentValues);
        return true;
    }

    public int numOfRows() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        int numOfRows = (int) DatabaseUtils.queryNumEntries(sqLiteDatabase, MOTIVISION_TABLE_REGISTRATION);
        return numOfRows;
    }

    public Cursor getDataUsernamePin(String username, String pin) {
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.query()
    }

}
