package com.udaff.agenda.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDBAdapter extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "agenda.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_USER = "user";

    public static final String COL_NAME = "name";
    public static final String COL_EMAIL = "email";
    public static final String COL_PHONE = "phone";
    public static final String COL_ADDRESS = "address";
    public static final String COL_BIRTHDAY = "birthday";

    public String[] allColumns = {COL_NAME, COL_EMAIL, COL_PHONE, COL_ADDRESS, COL_BIRTHDAY};

    public static final String CREATE_TABLE_USER = "create table " + TABLE_USER + " ("
            + COL_NAME + " text, "
            + COL_EMAIL + " text, "
            + COL_PHONE + " text, "
            + COL_ADDRESS + " text, "
            + COL_BIRTHDAY + " text); ";


    public UserDBAdapter(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }


}
