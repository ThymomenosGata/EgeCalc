package com.ilatis.egecalc.Fragments;

import android.animation.Animator;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ilatis.egecalc.Data.DataRait;
import com.ilatis.egecalc.ListAdapters.RaitingAdapter;
import com.ilatis.egecalc.Parser.HelperClasses.ClassRaiting;
import com.ilatis.egecalc.R;

import java.util.ArrayList;

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
    DataRait sql;
    ArrayList<ClassRaiting> rait = new ArrayList<>();
    RaitingAdapter adapter;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.raiting_fragment, container, false);
        sql = new DataRait(getContext());
        SQLiteDatabase sR = sql.getWritableDatabase();

        Cursor r = sR.query(DataRait.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);

        if(r.moveToFirst()){
            int unIndex = r.getColumnIndex(DataRait.COLUMN_UNIVERSITY);
            int prIndex = r.getColumnIndex(DataRait.COLUMN_PROC);
            int zpIndex = r.getColumnIndex(DataRait.COLUMN_MONEY);

            do{
                rait.add(new ClassRaiting(r.getString(unIndex),
                        r.getString(prIndex),
                        r.getString(zpIndex)));
            }while (r.moveToNext());
        }
        r.close();
        ListView listView = (ListView)v.findViewById(R.id.raitingList);
        listView.setVerticalScrollBarEnabled(false);
        adapter = new RaitingAdapter(getContext(), rait);
        listView.setAdapter(adapter);
        return v;
    }
}
