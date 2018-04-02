package com.ilatis.egecalc.Fragments;

import android.annotation.TargetApi;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ilatis.egecalc.Data.ClassRaiting;
import com.ilatis.egecalc.Data.DataRait;
import com.ilatis.egecalc.ListAdapters.RaitingAdapter;
import com.ilatis.egecalc.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.regex.Pattern;

/**
 * Created by thymomenosgata on 26.02.18.
 */

public class RaitingFragment extends Fragment {
    public RaitingFragment() {
    }

    public static RaitingFragment newInstace(){
        return new RaitingFragment();
    }
    View v;
    ArrayList<ClassRaiting> rait = new ArrayList<>();
    RaitingAdapter adapter;
    DataRait dReit;
    Cursor userCursor;
    SQLiteDatabase db;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.raiting_fragment, container, false);
        String precent;
        String money;
        dReit = new DataRait(getContext());
        dReit.create_db();
        db = dReit.open();
        userCursor =  db.rawQuery("select * from "+ DataRait.TABLE_NAME, null);

        if(userCursor.moveToFirst()){
            int unIndex = userCursor.getColumnIndex(DataRait.COLUMN_UNIVERSITY);
            int prIndex = userCursor.getColumnIndex(DataRait.COLUMN_PROC);
            int zpIndex = userCursor.getColumnIndex(DataRait.COLUMN_MONEY);

            do{
                precent = getMP(userCursor.getString(prIndex));
                money = getMP(userCursor.getString(zpIndex));
                rait.add(new ClassRaiting(userCursor.getString(unIndex),
                        precent, money));
            }while (userCursor.moveToNext());
        }
        userCursor.close();
        Collections.sort(rait, new Comparator<ClassRaiting>() {
            @TargetApi(Build.VERSION_CODES.KITKAT)
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public int compare(ClassRaiting o1, ClassRaiting o2) {
                return Integer.compare(Integer.valueOf(o2.getMoney()), Integer.valueOf(o1.getMoney()));
            }

        });

        for(ClassRaiting r : rait){
            r.setMoney(r.getMoney() + " P");
            r.setProc(r.getProc() + "%");
        }
        ListView listView = (ListView)v.findViewById(R.id.raitingList);
        listView.setVerticalScrollBarEnabled(false);
        adapter = new RaitingAdapter(getContext(), rait);
        listView.setAdapter(adapter);
        return v;
    }

    public static String getMP(String input) {
        Pattern pattern = Pattern.compile("\\W");
        String[] str = pattern.split(input);
        return str[0];
    }
}
