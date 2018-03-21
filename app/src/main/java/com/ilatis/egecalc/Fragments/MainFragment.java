package com.ilatis.egecalc.Fragments;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;

import com.ilatis.egecalc.Data.DATAHelper;
import com.ilatis.egecalc.Data.DataRait;
import com.ilatis.egecalc.Data.EditHelper;
import com.ilatis.egecalc.Data.ListForInterface;
import com.ilatis.egecalc.R;
import com.ilatis.egecalc.University;

import java.util.ArrayList;

/**
 * Created by thymomenosgata on 21.03.18.
 */

public class MainFragment extends Fragment {

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
    private View v;
    private ViewPager viewPager;
    ArrayList<ListForInterface> listV = new ArrayList<>();

    public MainFragment(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_main, container, false);
        ScrollView scroll = (ScrollView)v.findViewById(R.id.scroll);
        scroll.setVerticalScrollBarEnabled(false);
        sqlH = new DATAHelper(getContext());
        eSQL = new EditHelper(getContext());
        sqlR = new DataRait(getContext());
        final SQLiteDatabase sqlE = eSQL.getWritableDatabase();
        final SQLiteDatabase rSql = sqlR.getWritableDatabase();
        final SQLiteDatabase sql = sqlH.getWritableDatabase();
        viewPager = (ViewPager) v.findViewById(R.id.viewpager);

        rus = (EditText)v.findViewById(R.id.rusBalls);
        math = (EditText)v.findViewById(R.id.mathBalls);
        obsh = (EditText)v.findViewById(R.id.obshBalls);
        fiz = (EditText)v.findViewById(R.id.fizBalls);
        him = (EditText)v.findViewById(R.id.chemBalls);
        bio = (EditText)v.findViewById(R.id.bioBalls);
        ist = (EditText)v.findViewById(R.id.istBalls);
        ikt = (EditText)v.findViewById(R.id.iktBalls);
        geo = (EditText)v.findViewById(R.id.geoBalls);
        lang = (EditText)v.findViewById(R.id.langBalls);
        lit = (EditText)v.findViewById(R.id.litBalls);



        btn = (Button)v.findViewById(R.id.startSearch);
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
                    Intent intent = new Intent(getActivity(), University.class);
                    startActivity(intent);

                }
                else if (co != 0) {
                    error();
                    DialogFragment dialog = new ErrorDialog();
                    dialog.show(getFragmentManager(), "Error1");
                }
                else if(coc < 3){
                    DialogFragment dialog = new ErrorDialogTwo();
                    dialog.show(getFragmentManager(), "Error2");
                }
            }
        });

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        rus.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!rus.getText().toString().equals("")){
                    if(Integer.parseInt(rus.getText().toString()) <= 33) {
                        rus.setError(getResources().getString(R.string.minBullsR));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        math.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!math.getText().toString().equals("")){
                    if(Integer.parseInt(math.getText().toString()) <= 26) {
                        math.setError(getResources().getString(R.string.minBullsM));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        obsh.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!obsh.getText().toString().equals("")){
                    if(Integer.parseInt(obsh.getText().toString()) <= 39) {
                        obsh.setError(getResources().getString(R.string.minBullsO));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        fiz.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!fiz.getText().toString().equals("")){
                    if(Integer.parseInt(fiz.getText().toString()) <= 35) {
                        fiz.setError(getResources().getString(R.string.minBullsF));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        him.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!him.getText().toString().equals("")){
                    if(Integer.parseInt(him.getText().toString()) <= 35) {
                        him.setError(getResources().getString(R.string.minBullsF));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        bio.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!bio.getText().toString().equals("")){
                    if(Integer.parseInt(bio.getText().toString()) <= 35) {
                        bio.setError(getResources().getString(R.string.minBullsF));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ist.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!ist.getText().toString().equals("")){
                    if(Integer.parseInt(ist.getText().toString()) <= 28) {
                        ist.setError(getResources().getString(R.string.minBullsI));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ikt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!ikt.getText().toString().equals("")){
                    if(Integer.parseInt(ikt.getText().toString()) <= 39) {
                        ikt.setError(getResources().getString(R.string.minBullsO));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        geo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!geo.getText().toString().equals("")){
                    if(Integer.parseInt(geo.getText().toString()) <= 39) {
                        geo.setError(getResources().getString(R.string.minBullsO));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        lang.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!lang.getText().toString().equals("")){
                    if(Integer.parseInt(lang.getText().toString()) <= 21) {
                        lang.setError(getResources().getString(R.string.minBullsIn));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        lit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!lit.getText().toString().equals("")){
                    if(Integer.parseInt(lit.getText().toString()) <= 31) {
                        lit.setError(getResources().getString(R.string.minBullsL));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void error(){
        eSQL = new EditHelper(getContext());
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
}
