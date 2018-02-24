package com.ilatis.egecalc;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
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

import com.ilatis.egecalc.Data.DATAHelper;
import com.ilatis.egecalc.Data.EditHelper;
import com.ilatis.egecalc.Data.ListForInterface;
import com.ilatis.egecalc.Fragments.AlertDialogMain;
import com.ilatis.egecalc.Fragments.ErrorDialog;
import com.ilatis.egecalc.Fragments.ErrorDialogTwo;
import com.ilatis.egecalc.Fragments.FragmentOfBalls;
import com.ilatis.egecalc.ListAdapters.ListForEge;
import com.ilatis.egecalc.Parser.HelperClasses.ClassForUniversities;
import com.ilatis.egecalc.Parser.JsoupParser;

import java.io.IOException;
import java.util.ArrayList;

import static android.support.v4.app.DialogFragment.*;

public class MainActivity extends AppCompatActivity {

    DialogFragment dialogFragment;
    private Button btn;
    DATAHelper sqlH;
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
        eSQL = new EditHelper(getBaseContext());
        final SQLiteDatabase sqlE = eSQL.getWritableDatabase();
        SQLiteDatabase sql = sqlH.getWritableDatabase();

        Cursor c = sql.query(
                DATAHelper.TABLE_NAME,
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

        if(!c.moveToFirst()){
            dialogFragment = new AlertDialogMain();
            dialogFragment.show(getSupportFragmentManager(), "errr");

            new MyTask().execute();
        }

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

            SQLiteDatabase sql = sqlH.getWritableDatabase();

            ContentValues values = new ContentValues();
            for(ClassForUniversities un : univer){
                values.put(DATAHelper.COLUMN_UNIVERSITY, un.getUniverity());
                values.put(DATAHelper.COLUMN_SPECIALITY, un.getSpeciality());
                values.put(DATAHelper.COLUMN_DISCIPLINE, un.getDisciplins());
                values.put(DATAHelper.COLUMN_BALL, un.getBalsOf());
                values.put(DATAHelper.COLUMN_MONEY, 0);
                sql.insert(DATAHelper.TABLE_NAME, null, values);
            }
            dialogFragment.dismiss();
        }
    }


}
