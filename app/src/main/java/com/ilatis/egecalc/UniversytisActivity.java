package com.ilatis.egecalc;

import android.annotation.SuppressLint;
import android.content.ContentValues;
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

import com.ilatis.egecalc.Data.DATAHelper;
import com.ilatis.egecalc.Data.EditHelper;
import com.ilatis.egecalc.Data.ListForInterface;
import com.ilatis.egecalc.Fragments.FragmentOfBalls;

import java.util.ArrayList;

/**
 * Created by thymomenosgata on 23.02.18.
 */

public class UniversytisActivity extends AppCompatActivity {

    EditHelper eSQL;
    DATAHelper sqlH;
    ArrayList<ListForInterface> list = new ArrayList<>();
    private void loadFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.containerss, fragment);
        ft.commit();
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universiti_activity);
        eSQL = new EditHelper(getBaseContext());
        sqlH = new DATAHelper(getBaseContext());
        final SQLiteDatabase sqlD = sqlH.getWritableDatabase();
        final SQLiteDatabase sqlE = eSQL.getWritableDatabase();
        final Button btnEk = (Button) findViewById(R.id.butEkz);
        final Button btnMn = (Button) findViewById(R.id.butZP);
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
        loadFragment(FragmentOfBalls.newInstace());

        btnEk.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                btnEk.setTextColor(getColor(R.color.enterTextColor));
                btnMn.setTextColor(getColor(R.color.textColor));
                ContentValues vs = new ContentValues();
                for (ListForInterface lists : list) {
                    vs.put(EditHelper.COLUMN_DISC, lists.getDisc());
                    vs.put(EditHelper.COLUMN_BALLS, lists.getBall());
                    sqlE.insert(EditHelper.TABLE_NAME,
                            null,
                            vs);
                }
                loadFragment(FragmentOfBalls.newInstace());
            }
        });

        btnMn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                btnEk.setTextColor(getColor(R.color.textColor));
                btnMn.setTextColor(getColor(R.color.enterTextColor));
                //loadFragment(FragmentOfBalls.newInstace());
            }
        });

    }
}
