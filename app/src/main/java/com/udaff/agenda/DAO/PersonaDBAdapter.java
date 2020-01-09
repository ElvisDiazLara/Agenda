package com.udaff.agenda.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PersonaDBAdapter extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "agenda.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_PERSONA= "persona";

    public static final String COL_NOMBRE = "nombre";
    public static final String COL_CORREO= "correo";
    public static final String COL_TELEFONO= "telefono";
    public static final String COL_DIRECCION = "direccion";
    public static final String COL_NACIMIENTO= "fecha_nacimiento";

    public String[] allColumns = {COL_NOMBRE, COL_CORREO, COL_TELEFONO ,COL_DIRECCION, COL_NACIMIENTO};

    public static final String CREATE_TABLE_PERSONA = "create table "+ TABLE_PERSONA + " ("
            + COL_NOMBRE + " text, "
            + COL_CORREO + " text, "
            + COL_TELEFONO + " text, "
            + COL_DIRECCION + " text, "
            + COL_NACIMIENTO + " text); ";


    public PersonaDBAdapter(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PERSONA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERSONA);
        onCreate(db);
    }


}
