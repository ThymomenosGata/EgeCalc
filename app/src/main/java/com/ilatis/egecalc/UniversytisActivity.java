package com.ilatis.egecalc;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ilatis.egecalc.Data.DATAHelper;
import com.ilatis.egecalc.Data.EditHelper;
import com.ilatis.egecalc.Data.ListForInterface;
import com.ilatis.egecalc.Data.StructClass;
import com.ilatis.egecalc.Fragments.FragmentOfBalls;
import com.ilatis.egecalc.Fragments.RaitingFragment;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thymomenosgata on 23.02.18.
 */

public class UniversytisActivity extends AppCompatActivity {

    EditHelper eSQL;
    DATAHelper sqlH;
    ArrayList<ListForInterface> list = new ArrayList<>();
    public static final String APP_PREFERENCES = "setting";
    public static final String APP_PREFERENCES_STATE = "state";
    private SharedPreferences mSettings;
    public int i = 0;
    private void loadFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.containerss, fragment);
        ft.commit();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("ResourceAsColor")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universiti_activity);
        eSQL = new EditHelper(getBaseContext());
        final SQLiteDatabase sqlE = eSQL.getWritableDatabase();
        final Button btnEk = (Button) findViewById(R.id.butEkz);
        final Button btnMn = (Button) findViewById(R.id.butZP);
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        if (mSettings.contains(APP_PREFERENCES_STATE)) {
            i = mSettings.getInt(APP_PREFERENCES_STATE, 0);
        }

        if(i == 0){
            btnEk.setTextColor(getColor(R.color.bottomsTextColor));
            btnMn.setTextColor(getColor(R.color.textColor));
            loadFragment(FragmentOfBalls.newInstace());
        }else{
            btnEk.setTextColor(getColor(R.color.textColor));
            btnMn.setTextColor(getColor(R.color.bottomsTextColor));
            loadFragment(RaitingFragment.newInstace());
        }

        Cursor c = sqlE.query(
                EditHelper.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        if(c.moveToFirst()){
            int discIndex = c.getColumnIndex(EditHelper.COLUMN_DISC);
            int ballIndex = c.getColumnIndex(EditHelper.COLUMN_BALLS);
            do{
                list.add(new ListForInterface(c.getString(discIndex), c.getInt(ballIndex)));
            }while (c.moveToNext());
        }

        btnEk.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                i = 0;
                btnEk.setTextColor(getColor(R.color.bottomsTextColor));
                btnMn.setTextColor(getColor(R.color.textColor));
                SharedPreferences.Editor editor = mSettings.edit();
                editor.putInt(APP_PREFERENCES_STATE, i);
                editor.apply();
                loadFragment(FragmentOfBalls.newInstace());
            }
        });

        btnMn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                i = 1;
                btnEk.setTextColor(getColor(R.color.textColor));
                btnMn.setTextColor(getColor(R.color.bottomsTextColor));
                SharedPreferences.Editor editor = mSettings.edit();
                editor.putInt(APP_PREFERENCES_STATE, i);
                editor.apply();
                loadFragment(RaitingFragment.newInstace());
            }
        });
    }
}
