package com.ilatis.egecalc;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ilatis.egecalc.Data.DATAHelper;
import com.ilatis.egecalc.Data.DataRait;
import com.ilatis.egecalc.Data.EditHelper;
import com.ilatis.egecalc.Data.ListForInterface;
import com.ilatis.egecalc.Data.MSClass;
import com.ilatis.egecalc.Data.StructClass;
import com.ilatis.egecalc.Fragments.AlertDialogMain;
import com.ilatis.egecalc.Fragments.ErrorDialog;
import com.ilatis.egecalc.Fragments.ErrorDialogTwo;
import com.ilatis.egecalc.ListAdapters.ListForEge;
import com.ilatis.egecalc.Parser.HelperClasses.ClassRaiting;
import com.ilatis.egecalc.Parser.ParseRaiting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DialogFragment dialogFragment;
    private Button btn;
    DATAHelper sqlH;
    EditHelper eSQL;
    DataRait sqlR;
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

    private void loadFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.containerss, fragment);
        ft.commit();
    }

    ArrayList<ListForInterface> listV = new ArrayList<>();

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sqlH = new DATAHelper(getBaseContext());
        eSQL = new EditHelper(getBaseContext());
        sqlR = new DataRait(getBaseContext());
        final SQLiteDatabase sqlE = eSQL.getWritableDatabase();
        final SQLiteDatabase rSql = sqlR.getWritableDatabase();
        final SQLiteDatabase sql = sqlH.getWritableDatabase();

        Cursor c = sql.query(
                DATAHelper.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        final Cursor r = rSql.query(
                DataRait.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        if(!c.moveToFirst() || !r.moveToFirst()){
            dialogFragment = new AlertDialogMain();
            dialogFragment.show(getSupportFragmentManager(), "errr");
            if(!c.moveToFirst() && !r.moveToFirst()) {
                new MyTask().execute();
                new newTask().execute();
            }
            else if(!c.moveToFirst()){
                new MyTask().execute();
            }
            else if(!r.moveToFirst()){
                new newTask().execute();
            }
        }

        c.close();
        r.close();

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
                sqlE.delete(EditHelper.TABLE_NAME, null, null);
                int co = 0;
                int coc = 0;
                if(!rus.getText().toString().equals("")){
                    if(Integer.parseInt(rus.getText().toString()) <= 100) {
                        listV.add(new ListForInterface("русский язык",
                                Integer.parseInt(rus.getText().toString())));
                        coc++;
                    }
                    else
                        co++;
                }
                if(!math.getText().toString().equals("")){
                    if(Integer.parseInt(math.getText().toString()) <= 100) {
                        listV.add(new ListForInterface("математика",
                                Integer.parseInt(math.getText().toString())));
                        coc++;
                    }
                    else
                        co++;
                }
                if(!obsh.getText().toString().equals("")){
                    if(Integer.parseInt(obsh.getText().toString()) <= 100) {
                        listV.add(new ListForInterface("обществознание",
                                Integer.parseInt(obsh.getText().toString())));
                        coc++;
                    }
                    else
                        co++;
                }
                if(!fiz.getText().toString().equals("")){
                    if(Integer.parseInt(fiz.getText().toString()) <= 100) {
                        listV.add(new ListForInterface("физика",
                                Integer.parseInt(fiz.getText().toString())));
                        coc++;
                    }
                    else
                        co++;
                }
                if(!him.getText().toString().equals("")){
                    if(Integer.parseInt(him.getText().toString()) <= 100) {
                        listV.add(new ListForInterface("химия",
                                Integer.parseInt(him.getText().toString())));
                        coc++;
                    }
                    else
                        co++;
                }
                if(!bio.getText().toString().equals("")){
                    if(Integer.parseInt(bio.getText().toString()) <= 100) {
                        listV.add(new ListForInterface("биология",
                                Integer.parseInt(bio.getText().toString())));
                        coc++;
                    }
                    else
                        co++;
                }
                if(!ist.getText().toString().equals("")){
                    if(Integer.parseInt(ist.getText().toString()) <= 100) {
                        listV.add(new ListForInterface("история",
                                Integer.parseInt(ist.getText().toString())));
                        coc++;
                    }
                    else
                        co++;
                }
                if(!ikt.getText().toString().equals("")){
                    if(Integer.parseInt(ikt.getText().toString()) <= 100) {
                        listV.add(new ListForInterface("информатика и ИКТ",
                                Integer.parseInt(ikt.getText().toString())));
                        coc++;
                    }
                    else
                        co++;
                }
                if(!geo.getText().toString().equals("")){
                    if(Integer.parseInt(geo.getText().toString()) <= 100) {
                        listV.add(new ListForInterface("география",
                                Integer.parseInt(geo.getText().toString())));
                        coc++;
                    }
                    else
                        co++;
                }
                if(!lang.getText().toString().equals("")){
                    if(Integer.parseInt(lang.getText().toString()) <= 100) {
                        listV.add(new ListForInterface("иностранный язык",
                                Integer.parseInt(lang.getText().toString())));
                        coc++;
                    }
                    else
                        co++;
                }
                if(!lit.getText().toString().equals("")){
                    if( Integer.parseInt(lit.getText().toString()) <= 100) {
                        listV.add(new ListForInterface("литература",
                                Integer.parseInt(lit.getText().toString())));
                        coc++;
                    }
                    else
                        co++;

                }

                if(co == 0 && coc > 2) {
                    ContentValues vs = new ContentValues();
                    for (ListForInterface list : listV) {
                        vs.put(EditHelper.COLUMN_DISC, list.getDisc());
                        vs.put(EditHelper.COLUMN_BALLS, list.getBall());
                        sqlE.insert(EditHelper.TABLE_NAME,
                                null,
                                vs);
                    }
                    listV.removeAll(listV);
                    Intent intent = new Intent(MainActivity.this, UniversytisActivity.class);
                    startActivity(intent);
                }
                else if (co != 0) {
                    error();
                    DialogFragment dialog = new ErrorDialog();
                    dialog.show(getSupportFragmentManager(), "Error1");
                }
                else if(coc < 3){
                    DialogFragment dialog = new ErrorDialogTwo();
                    dialog.show(getSupportFragmentManager(), "Error2");
                }
            }
        });
    }

    private void error(){
        eSQL = new EditHelper(getBaseContext());
        SQLiteDatabase sqlE = eSQL.getWritableDatabase();
        sqlE.delete(EditHelper.TABLE_NAME, null, null);
        rus.setText("");
        math.setText("");
        obsh.setText("");
        bio.setText("");
        him.setText("");
        geo.setText("");
        fiz.setText("");
        ikt.setText("");
        ist.setText("");
        lang.setText("");
        lit.setText("");
    }

    private class MyTask extends AsyncTask<Void, ArrayList<ListForEge>, ArrayList<ListForEge>> {
        @Override
        protected ArrayList<ListForEge> doInBackground(Void... params) {
            ArrayList<ListForEge> univers = new ArrayList<>();
            univers = getList();
            return univers;
        }

        @Override
        protected void onPostExecute(ArrayList<ListForEge> univer) {
            super.onPostExecute(univer);

            sqlH = new DATAHelper(getBaseContext());
            SQLiteDatabase sql = sqlH.getWritableDatabase();

            ContentValues values = new ContentValues();
            for(ListForEge un : univer){
                values.put(DATAHelper.COLUMN_UNIVERSITY, un.getUnivers());
                values.put(DATAHelper.COLUMN_SPECIALITY, un.getSpecial());
                values.put(DATAHelper.COLUMN_DISCIPLINE, un.getDisvipl());
                values.put(DATAHelper.COLUMN_BALL, un.getBall());
                values.put(DATAHelper.COLUMN_MONEY, un.getMoney());
                sql.insert(DATAHelper.TABLE_NAME, null, values);
            }
        }
    }

    private class newTask extends AsyncTask<Void, ArrayList<ClassRaiting>, ArrayList<ClassRaiting>>{

        @Override
        protected ArrayList<ClassRaiting> doInBackground(Void... voids) {
            ArrayList<ClassRaiting> rait = new ArrayList<>();
            try {
                for(ClassRaiting r : ParseRaiting.Parser())
                    rait.add(r);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return rait;
        }

        @Override
        protected void onPostExecute(ArrayList<ClassRaiting> rait) {
            super.onPostExecute(rait);
            sqlR = new DataRait(getBaseContext());

            SQLiteDatabase sR = sqlR.getWritableDatabase();

            ContentValues val = new ContentValues();
            for(ClassRaiting r : rait){
                val.put(DataRait.COLUMN_UNIVERSITY, r.getName());
                val.put(DataRait.COLUMN_PROC, r.getProc());
                val.put(DataRait.COLUMN_MONEY, r.getMoney());
                sR.insert(DataRait.TABLE_NAME, null, val);
            }
            dialogFragment.dismiss();
        }
    }
    public List<StructClass> getJs(){
        Gson gson = new Gson();
        BufferedReader br;
        List<StructClass> list;
        List<StructClass> list2;
        InputStream is = getResources().openRawResource(R.raw.msk);
        Type founderListType = new TypeToken<ArrayList<StructClass>>(){}.getType();
        br = new BufferedReader(new InputStreamReader(is));
        list = gson.fromJson(br, founderListType);
        is = getResources().openRawResource(R.raw.spb);
        br = new BufferedReader(new InputStreamReader(is));
        list2 = gson.fromJson(br, founderListType);
        for(StructClass st : list2)
            list.add(st);
        list2.clear();
        return list;
    }

    public ArrayList<ListForEge> getList(){
        List<StructClass> list;
        ArrayList<ListForEge> arrayList = new ArrayList<>();
        list = getJs();
        for(StructClass st : list){
            for(MSClass ms : st.getPrograms()) {
                String exa = "";
                String s = "";
                if(ms.getExams()!=null) {
                    for (int j = 0; j < ms.getExams().getEge().size(); j++) {
                        int i = 1;
                        exa += ms.getExams().getEge().get(j);
                        if (i < ms.getExams().getEge().size() - 1)
                            exa += ", ";
                        i++;
                    }
                }
                else
                    exa = "?";
                arrayList.add(new ListForEge(st.getName(), exa, ms.getCommon_name(), ms.getScore(), (int) ms.getSalary()));
            }
        }
        return arrayList;
    }
}
