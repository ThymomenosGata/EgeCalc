package com.ilatis.egecalc.Fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.ilatis.egecalc.Data.DATAHelper;
import com.ilatis.egecalc.Data.DisciplineHelper;
import com.ilatis.egecalc.Data.EditHelper;
import com.ilatis.egecalc.Data.ListForInterface;
import com.ilatis.egecalc.ListAdapters.EgeAdapter;
import com.ilatis.egecalc.ListAdapters.ListForEge;
import com.ilatis.egecalc.R;

import java.util.ArrayList;

/**
 * Created by thymomenosgata on 19.02.18.
 */

public class FragmentOfBalls extends Fragment {
    public FragmentOfBalls(){
    }

    public static FragmentOfBalls newInstace(){
        return new FragmentOfBalls();
    }

    View v;
    ArrayList<ListForInterface> listSS = new ArrayList<>();
    ArrayList<ListForEge> arrayList = new ArrayList<>();
    EditHelper eSQL;
    DATAHelper sqlH;
    DisciplineHelper sqlD;
    EgeAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.university_fragment, container, false);

        sqlH = new DATAHelper(getContext());
        eSQL = new EditHelper(getContext());
        sqlD = new DisciplineHelper(getContext());

        final SQLiteDatabase sqls = sqlD.getWritableDatabase();
        final SQLiteDatabase sql = eSQL.getWritableDatabase();
        final SQLiteDatabase sqlD = sqlH.getWritableDatabase();
        Cursor c = sql.query(
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
                listSS.add(new ListForInterface(c.getString(discIndex), c.getInt(ballIndex)));
            }while (c.moveToNext());
        }
        c.close();
        sql.delete(EditHelper.TABLE_NAME,
                null,
                null);

        c = sqlD.query(DATAHelper.TABLE_NAME,
                null,
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

        ListView listView = (ListView)v.findViewById(R.id.listForRait);
        adapter = new EgeAdapter(getContext(), arrayList);
        listView.setAdapter(adapter);


        return v;
    }
}
