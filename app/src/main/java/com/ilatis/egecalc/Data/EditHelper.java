package com.ilatis.egecalc.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by thymomenosgata on 21.02.18.
 */

public class EditHelper extends SQLiteOpenHelper {

    public final static String TABLE_NAME = "edited";

    public final static String _ID = BaseColumns._ID;
    public final static String COLUMN_DISC = "disc";
    public final static String COLUMN_BALLS = "balls";

    //Имя файла базы данных
    private static final String DATABASE_NAME = "Dataedit.db";

    //Версия базы данных. При изменении схемы увеличить на единицу
    private static final int DATABASE_VERSION = 1;

    public EditHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " ("
                + _ID + " INTEGER PRIMARY KEY, "
                + COLUMN_DISC + " TEXT, "
                + COLUMN_BALLS + " INTEGER" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
