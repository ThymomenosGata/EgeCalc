package com.ilatis.egecalc.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by thymomenosgata on 19.02.18.
 */


public class DATAHelper extends SQLiteOpenHelper {

    public final static String TABLE_NAME = "Datass";

    public final static String _ID = BaseColumns._ID;
    public final static String COLUMN_UNIVERSITY = "university";
    public final static String COLUMN_SPECIALITY = "speciality";
    public final static String COLUMN_DISCIPLINE = "discipline";
    public final static String COLUMN_BALL = "ball";
    public final static String COLUMN_MONEY = "money";


    private static final String DATABASE_NAME = "Datas.db";

    private static final int DATABASE_VERSION = 3;

    public DATAHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " ("
                + _ID + " INTEGER PRIMARY KEY, "
                + COLUMN_UNIVERSITY + " TEXT, "
                + COLUMN_SPECIALITY + " TEXT, "
                + COLUMN_DISCIPLINE + " TEXT, "
                + COLUMN_BALL + " INTEGER, "
                + COLUMN_MONEY + " TEXT" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}

