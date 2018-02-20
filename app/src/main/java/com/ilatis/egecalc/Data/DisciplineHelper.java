package com.ilatis.egecalc.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by thymomenosgata on 20.02.18.
 */

public class DisciplineHelper extends SQLiteOpenHelper {

    public final static String TABLE_NAME = "Datadiss";

    public final static String _ID = BaseColumns._ID;
    public final static String COLUMN_RU = "rus";
    public final static String COLUMN_MATH = "math";
    public final static String COLUMN_OBSH = "obsh";
    public final static String COLUMN_FIZ = "fiz";
    public final static String COLUMN_IST = "ist";
    public final static String COLUMN_BIO = "bio";
    public final static String COLUMN_CHEM = "chem";
    public final static String COLUMN_LANG = "lang";
    public final static String COLUMN_IKT = "ikt";
    public final static String COLUMN_GEO = "geo";
    public final static String COLUMN_LIT = "lit";

    //Имя файла базы данных
    private static final String DATABASE_NAME = "Datadis.db";

    //Версия базы данных. При изменении схемы увеличить на единицу
    private static final int DATABASE_VERSION = 1;

    public DisciplineHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " ("
                + _ID + " INTEGER PRIMARY KEY, "
                + COLUMN_RU + " INTEGER, "
                + COLUMN_MATH + " INTEGER, "
                + COLUMN_OBSH + " INTEGER, "
                + COLUMN_FIZ + " INTEGER, "
                + COLUMN_IST + " INTEGER, "
                + COLUMN_BIO + " INTEGER, "
                + COLUMN_CHEM + " INTEGER, "
                + COLUMN_LANG + " INTEGER, "
                + COLUMN_IKT + " INTEGER, "
                + COLUMN_GEO + " INTEGER, "
                + COLUMN_LIT + " INTEGER" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
