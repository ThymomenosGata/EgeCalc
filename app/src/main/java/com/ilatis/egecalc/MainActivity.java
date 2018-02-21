package com.ilatis.egecalc;

import android.annotation.SuppressLint;
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
import android.widget.EditText;

import com.ilatis.egecalc.Data.DATAHelper;
import com.ilatis.egecalc.Data.DisciplineHelper;
import com.ilatis.egecalc.Data.EditHelper;
import com.ilatis.egecalc.Data.ListForInterface;
import com.ilatis.egecalc.Fragments.FragmentOfBalls;
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
    private Button btn;
    DATAHelper sqlH;
    DisciplineHelper sqlD;
    EditHelper eSQL;
    private EditText rus;
    private EditText math;
    private EditText obsh;
    private EditText ist;
    private EditText him;
    private EditText bio;
    private EditText ikt;
    private EditText geo;
    private EditText lang;
    private EditText lit;
    private EditText fiz;

    ArrayList<ListForInterface> listV = new ArrayList<>();



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<ListForEge> arrayList = new  ArrayList<ListForEge>();
        sqlH = new DATAHelper(getBaseContext());
        sqlD = new DisciplineHelper(getBaseContext());
        eSQL = new EditHelper(getBaseContext());

        final SQLiteDatabase sqlE = eSQL.getWritableDatabase();
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

        final Cursor ccc = sqlE.query(
                EditHelper.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        if(!c.moveToFirst() && !cc.moveToFirst()){
            new MyTask().execute();
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

        rus = (EditText)findViewById(R.id.rusBalls);
        math = (EditText)findViewById(R.id.mathBalls);
        obsh = (EditText)findViewById(R.id.obshBalls);
        fiz = (EditText)findViewById(R.id.fizBalls);
        him = (EditText)findViewById(R.id.chemBalls);
        bio = (EditText)findViewById(R.id.bioBalls);
        ist = (EditText)findViewById(R.id.istBalls);
        ikt = (EditText)findViewById(R.id.iktBalls);
        geo = (EditText)findViewById(R.id.geoBalls);
        lang = (EditText)findViewById(R.id.langBalls);
        lit = (EditText)findViewById(R.id.litBalls);


        btn = (Button)findViewById(R.id.startSearch);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!rus.getText().toString().equals("")){
                    listV.add(new ListForInterface("русский",
                            Integer.parseInt(rus.getText().toString())));
                }
                if(!math.getText().toString().equals("")){
                    listV.add(new ListForInterface("математика",
                            Integer.parseInt(math.getText().toString())));
                }
                if(!obsh.getText().toString().equals("")){
                    listV.add(new ListForInterface("обществознание",
                            Integer.parseInt(obsh.getText().toString())));
                }
                if(!fiz.getText().toString().equals("")){
                    listV.add(new ListForInterface("физика",
                            Integer.parseInt(fiz.getText().toString())));
                }
                if(!him.getText().toString().equals("")){
                    listV.add(new ListForInterface("химия",
                            Integer.parseInt(him.getText().toString())));
                }
                if(!bio.getText().toString().equals("")){
                    listV.add(new ListForInterface("биология",
                            Integer.parseInt(bio.getText().toString())));
                }
                if(!ist.getText().toString().equals("")){
                    listV.add(new ListForInterface("история",
                            Integer.parseInt(ist.getText().toString())));
                }
                if(!ikt.getText().toString().equals("")){
                    listV.add(new ListForInterface("информатика",
                            Integer.parseInt(ikt.getText().toString())));
                }
                if(!geo.getText().toString().equals("")){
                    listV.add(new ListForInterface("география",
                            Integer.parseInt(geo.getText().toString())));
                }
                if(!lang.getText().toString().equals("")){
                    listV.add(new ListForInterface("иностранный язык",
                            Integer.parseInt(lang.getText().toString())));
                }
                if(!lit.getText().toString().equals("")){
                    listV.add(new ListForInterface("литература",
                            Integer.parseInt(lit.getText().toString())));
                }

                ContentValues vs = new ContentValues();
                for(ListForInterface list : listV){
                    vs.put(EditHelper.COLUMN_DISC, list.getDisc());
                    vs.put(EditHelper.COLUMN_BALLS, list.getBall());
                    sqlE.insert(EditHelper.TABLE_NAME,
                            null,
                            vs);
                }

                loadFragment(FragmentOfBalls.newInstace());
            }
        });
    }

    private class MyTask extends AsyncTask<Void, ArrayList<ClassForUniversities>, ArrayList<ClassForUniversities>> {

        @Override
        protected ArrayList<ClassForUniversities> doInBackground(Void... params) {
            ArrayList<ClassForUniversities> univers = new ArrayList<>();
            try {
                for(ClassForUniversities cl : JsoupParser.Parse())
                    univers.add(cl);
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
                System.out.println(un.getUniverity());

                if(un.getDisp().getBio())
                    vals.put(DisciplineHelper.COLUMN_BIO, 1);
                else
                    vals.put(DisciplineHelper.COLUMN_BIO, 0);

                if(un.getDisp().getRus())
                    vals.put(DisciplineHelper.COLUMN_RU, 1);
                else
                    vals.put(DisciplineHelper.COLUMN_RU, 0);

                if(un.getDisp().getMat())
                    vals.put(DisciplineHelper.COLUMN_MATH, 1);
                else
                    vals.put(DisciplineHelper.COLUMN_MATH, 0);

                if(un.getDisp().getObsh())
                    vals.put(DisciplineHelper.COLUMN_OBSH, 1);
                else
                    vals.put(DisciplineHelper.COLUMN_OBSH, 0);

                if(un.getDisp().getIst())
                    vals.put(DisciplineHelper.COLUMN_IST, 1);
                else
                    vals.put(DisciplineHelper.COLUMN_IST, 0);

                if(un.getDisp().getInyz())
                    vals.put(DisciplineHelper.COLUMN_LANG, 1);
                else
                    vals.put(DisciplineHelper.COLUMN_LANG, 0);

                if(un.getDisp().getGeo())
                    vals.put(DisciplineHelper.COLUMN_GEO, 1);
                else
                    vals.put(DisciplineHelper.COLUMN_GEO, 0);
                if(un.getDisp().getLit())
                    vals.put(DisciplineHelper.COLUMN_LIT, 1);
                else
                    vals.put(DisciplineHelper.COLUMN_LIT, 0);

                if(un.getDisp().getHim())
                    vals.put(DisciplineHelper.COLUMN_CHEM, 1);
                else
                    vals.put(DisciplineHelper.COLUMN_CHEM, 0);

                if(un.getDisp().getIkt())
                    vals.put(DisciplineHelper.COLUMN_IKT, 1);
                else
                    vals.put(DisciplineHelper.COLUMN_IKT, 0);
                if(un.getDisp().getFiz())
                    vals.put(DisciplineHelper.COLUMN_FIZ, 1);
                else
                    vals.put(DisciplineHelper.COLUMN_FIZ, 0);

                sql.insert(DATAHelper.TABLE_NAME, null, values);
                sqls.insert(DisciplineHelper.TABLE_NAME, null, vals);
            }
        }
    }
}
