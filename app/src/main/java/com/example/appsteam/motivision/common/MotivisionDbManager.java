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
    public static final String MOTIVISION_REGISTRATION_COLUMN_USERNAME = "Username";
    public static final String MOTIVISION_REGISTRATION_COLUMN_PASSWORD = "Password";
    public static final String MOTIVISION_REGISTRATION_COLUMN_SECURITY_QUESTION = "security_question";
    public static final String MOTIVISION_REGISTRATION_COLUMN_ANSWER = "answer";
    public static final String MOTIVISION_REGISTRATION_COLUMN_PROFILE_PICTURE_URI = "profile_Picture";


    public MotivisionDbManager(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table " + MOTIVISION_TABLE_REGISTRATION  + "(" + MOTIVISION_REGISTRATION_COLUMN_ID + "integer primary key, "
                +  MOTIVISION_REGISTRATION_COLUMN_USERNAME  + " text,"
                +  MOTIVISION_REGISTRATION_COLUMN_PASSWORD  + " text,"
                +  MOTIVISION_REGISTRATION_COLUMN_SECURITY_QUESTION  + " text,"
                +  MOTIVISION_REGISTRATION_COLUMN_ANSWER  + " text,"
                +MOTIVISION_REGISTRATION_COLUMN_PROFILE_PICTURE_URI + " text" + ");");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean insertRegistrationDetails(String username, String password, String security_question, String answer, String profilePicture) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MOTIVISION_REGISTRATION_COLUMN_USERNAME, username);
        contentValues.put(MOTIVISION_REGISTRATION_COLUMN_PASSWORD, password);
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

    public Cursor getDataUsernamePassword(String username, String password) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        /*Cursor cursor = sqLiteDatabase.query(MOTIVISION_TABLE_REGISTRATION,
                new String[]{MOTIVISION_REGISTRATION_COLUMN_USERNAME,
                            MOTIVISION_REGISTRATION_COLUMN_PASSWORD},
                  MOTIVISION_REGISTRATION_COLUMN_USERNAME + "=?" + " AND " + MOTIVISION_REGISTRATION_COLUMN_PASSWORD + "=?",
                new String[]{username, password}, null, null, null, null);*/
        Cursor cursor = sqLiteDatabase.rawQuery("select " + MOTIVISION_REGISTRATION_COLUMN_USERNAME + "," + MOTIVISION_REGISTRATION_COLUMN_PASSWORD + " from " + MOTIVISION_TABLE_REGISTRATION + " where " + MOTIVISION_REGISTRATION_COLUMN_USERNAME + " = " + "'" + username + "'" + " and "
                + MOTIVISION_REGISTRATION_COLUMN_PASSWORD+ " = " + "'" + password + "'", null);
        return cursor;

    }

    public boolean updatePin(String username, String password) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MOTIVISION_REGISTRATION_COLUMN_PASSWORD, password);
        sqLiteDatabase.update(MOTIVISION_TABLE_REGISTRATION, contentValues, "" + MOTIVISION_REGISTRATION_COLUMN_USERNAME + "=?", new String[]{username});
        return true;

    }

    public Cursor getRegistrationFullData(String username) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + MOTIVISION_TABLE_REGISTRATION + " where " + MOTIVISION_REGISTRATION_COLUMN_USERNAME + " = " + "'" + username + "'", null);
        return cursor;
    }
    public Cursor getSecurityquestion(String username) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select "+MOTIVISION_REGISTRATION_COLUMN_SECURITY_QUESTION+" from " + MOTIVISION_TABLE_REGISTRATION + " where " + MOTIVISION_REGISTRATION_COLUMN_USERNAME + " = " + "'" + username + "'", null);
        return cursor;
    }

}
