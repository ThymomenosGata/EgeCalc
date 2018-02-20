package com.ilatis.egecalc;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ilatis.egecalc.Data.DATAHelper;
import com.ilatis.egecalc.Data.DisciplineHelper;
import com.ilatis.egecalc.Fragments.FragmentOfBalls;
import com.ilatis.egecalc.ListAdapters.EgeAdapter;
import com.ilatis.egecalc.ListAdapters.ListForEge;
import com.ilatis.egecalc.Parser.HelperClasses.ClassForUniversities;
import com.ilatis.egecalc.Parser.JsoupParser;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private void loadFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.activityMain, fragment);
        ft.commit();
    }
    Button btn;
    DATAHelper sqlH;
    DisciplineHelper sqlD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<ListForEge> arrayList = new  ArrayList<ListForEge>();
        sqlH = new DATAHelper(getBaseContext());
        sqlD = new DisciplineHelper(getBaseContext());

        SQLiteDatabase sql = sqlH.getWritableDatabase();
        SQLiteDatabase sqls = sqlD.getWritableDatabase();

        Cursor c = sql.query(
                DATAHelper.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        Cursor cc = sqls.query(
                DisciplineHelper.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        if(!c.moveToFirst() && !cc.moveToFirst()){
            new MyTask().execute();
            System.out.println("bb");
        }
        else
            if(c.moveToFirst() && cc.moveToFirst()){

            int univerIndex = c.getColumnIndex(DATAHelper.COLUMN_UNIVERSITY);
            int specifIndex = c.getColumnIndex(DATAHelper.COLUMN_SPECIALITY);
            int ballsIndex = c.getColumnIndex(DATAHelper.COLUMN_BALL);
            int moneyIndex = c.getColumnIndex(DATAHelper.COLUMN_MONEY);

            int ruIndex = cc.getColumnIndex(DisciplineHelper.COLUMN_RU);
            int mathIndex = cc.getColumnIndex(DisciplineHelper.COLUMN_MATH);
            int obshIndex = cc.getColumnIndex(DisciplineHelper.COLUMN_OBSH);
            int fizIndex = cc.getColumnIndex(DisciplineHelper.COLUMN_FIZ);
            int istIndex = cc.getColumnIndex(DisciplineHelper.COLUMN_IST);
            int bioIndex = cc.getColumnIndex(DisciplineHelper.COLUMN_BIO);
            int chemIndex = cc.getColumnIndex(DisciplineHelper.COLUMN_CHEM);
            int langIndex = cc.getColumnIndex(DisciplineHelper.COLUMN_LANG);
            int iktIndex = cc.getColumnIndex(DisciplineHelper.COLUMN_IKT);
            int geoIndex = cc.getColumnIndex(DisciplineHelper.COLUMN_GEO);
            int litIndex = cc.getColumnIndex(DisciplineHelper.COLUMN_LIT);

            do{
                String disp = "";
                if(cc.getInt(ruIndex) == 1){
                    disp+="русский; ";
                }
                if(cc.getInt(mathIndex) == 1){
                    disp+="математика; ";
                }
                if(cc.getInt(obshIndex) == 1){
                    disp+="обществознание; ";
                }
                if(cc.getInt(fizIndex) == 1){
                    disp+="физика; ";
                }
                if(cc.getInt(istIndex) == 1){
                    disp+="история; ";
                }
                if(cc.getInt(bioIndex) == 1){
                    disp+="биология; ";
                }
                if(cc.getInt(chemIndex) == 1){
                    disp+="химия; ";
                }
                if(cc.getInt(langIndex) == 1){
                    disp+="иностранный язык; ";
                }
                if(cc.getInt(iktIndex) == 1){
                    disp+="информатика и ИКТ; ";
                }
                if(cc.getInt(geoIndex) == 1){
                    disp+="география; ";
                }
                if(cc.getInt(litIndex) == 1){
                    disp+="литература; ";
                }

                arrayList.add(new ListForEge(c.getString(univerIndex), disp,
                        c.getString(specifIndex),c.getInt(ballsIndex), c.getInt(moneyIndex)));
            }while (c.moveToNext() && cc.moveToNext());
        }
        c.close();
        cc.close();

        btn = (Button)findViewById(R.id.startSearch);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println();
                loadFragment(FragmentOfBalls.newInstace());
            }
        });
    }

    private class MyTask extends AsyncTask<Void, ArrayList<ClassForUniversities>, ArrayList<ClassForUniversities>> {

        @Override
        protected ArrayList<ClassForUniversities> doInBackground(Void... params) {
            ArrayList<ClassForUniversities> univers = new ArrayList<ClassForUniversities>();
            try {
                univers = JsoupParser.Parse();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return univers;
        }

        @Override
        protected void onPostExecute(ArrayList<ClassForUniversities> univer) {
            super.onPostExecute(univer);

            sqlH = new DATAHelper(getBaseContext());
            sqlD = new DisciplineHelper(getBaseContext());

            SQLiteDatabase sql = sqlH.getWritableDatabase();
            SQLiteDatabase sqls = sqlD.getWritableDatabase();

            ContentValues values = new ContentValues();
            ContentValues vals = new ContentValues();
            for(ClassForUniversities un : univer){
                values.put(DATAHelper.COLUMN_UNIVERSITY, un.getUniverity());
                values.put(DATAHelper.COLUMN_SPECIALITY, un.getSpeciality());
                values.put(DATAHelper.COLUMN_BALL, un.getBalsOf());
                values.put(DATAHelper.COLUMN_MONEY, 0);
                if(un.getDisp().getBio())
                    vals.put(DisciplineHelper.COLUMN_BIO, 1);
                if(un.getDisp().getRus())
                    vals.put(DisciplineHelper.COLUMN_RU, 1);
                if(un.getDisp().getMat())
                    vals.put(DisciplineHelper.COLUMN_MATH, 1);
                if(un.getDisp().getObsh())
                    vals.put(DisciplineHelper.COLUMN_OBSH, 1);
                if(un.getDisp().getIst())
                    vals.put(DisciplineHelper.COLUMN_IST, 1);
                if(un.getDisp().getInyz())
                    vals.put(DisciplineHelper.COLUMN_LANG, 1);
                if(un.getDisp().getGeo())
                    vals.put(DisciplineHelper.COLUMN_GEO, 1);
                if(un.getDisp().getLit())
                    vals.put(DisciplineHelper.COLUMN_LIT, 1);
                if(un.getDisp().getHim())
                    vals.put(DisciplineHelper.COLUMN_CHEM, 1);
                if(un.getDisp().getIkt())
                    vals.put(DisciplineHelper.COLUMN_IKT, 1);
                if(un.getDisp().getFiz())
                    vals.put(DisciplineHelper.COLUMN_FIZ, 1);
            }
            sql.insert(DATAHelper.TABLE_NAME, null, values);
            sqls.insert(DisciplineHelper.TABLE_NAME, null, vals);
        }
    }
}
