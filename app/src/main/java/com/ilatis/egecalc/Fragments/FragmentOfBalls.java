package com.ilatis.egecalc.Fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ilatis.egecalc.Data.DATAHelper;
import com.ilatis.egecalc.Data.EditHelper;
import com.ilatis.egecalc.Data.ListForInterface;
import com.ilatis.egecalc.ListAdapters.EgeAdapter;
import com.ilatis.egecalc.ListAdapters.ListForEge;
import com.ilatis.egecalc.R;

import java.util.ArrayList;
import java.util.regex.Pattern;

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
    EgeAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.university_fragment, container, false);

        sqlH = new DATAHelper(getContext());
        eSQL = new EditHelper(getContext());

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


        if(c.moveToFirst()){
            int univerIndex = c.getColumnIndex(DATAHelper.COLUMN_UNIVERSITY);
            int specifIndex = c.getColumnIndex(DATAHelper.COLUMN_SPECIALITY);
            int discIndex = c.getColumnIndex(DATAHelper.COLUMN_DISCIPLINE);
            int ballsIndex = c.getColumnIndex(DATAHelper.COLUMN_BALL);
            int moneyIndex = c.getColumnIndex(DATAHelper.COLUMN_MONEY);
            do{
                arrayList.add(new ListForEge(c.getString(univerIndex),
                        c.getString(discIndex), c.getString(specifIndex),
                        c.getInt(ballsIndex), c.getInt(moneyIndex)));
            }while (c.moveToNext());
        }

        ArrayList<ListForEge> arrayZ = new ArrayList<>();
        int sum = 0;
        for(ListForInterface list : listSS){
            sum+=list.getBall();
        }

        for(ListForEge ege : arrayList){
            if(ege.getBall() <= sum && search(listSS, ege)){
                arrayZ.add(new ListForEge(ege.getUnivers(), ege.getDisvipl(),
                        ege.getSpecial(), ege.getBall(), ege.getMoney()));
            }
        }
        ListView listView = (ListView)v.findViewById(R.id.listForRait);
        adapter = new EgeAdapter(getContext(), arrayZ);
        listView.setAdapter(adapter);

        return v;
    }

    public static String[] getDisc(String input) {
        Pattern pattern = Pattern.compile(", ");
        String[] str = pattern.split(input);
        return str;
    }

    public boolean search(ArrayList<ListForInterface> ls, ListForEge list){
            String[] supDisc = getDisc(list.getDisvipl());
            int count = 0;
            for(ListForInterface l : ls){
                for(String str : supDisc){
                    if(l.getDisc().equals(str)){
                        count++;
                    }
                }
            }
            System.out.println(count);
            if(count == supDisc.length)
                return true;
            else
                return false;
    }
}
