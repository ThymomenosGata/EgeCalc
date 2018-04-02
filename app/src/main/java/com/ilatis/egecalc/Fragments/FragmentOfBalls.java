package com.ilatis.egecalc.Fragments;

import android.annotation.TargetApi;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.regex.Pattern;

/**
 * Created by thymomenosgata on 19.02.18.
 */

public class FragmentOfBalls extends Fragment {
    public FragmentOfBalls(){
    }

    DialogFragment dialogFragment;

    public static FragmentOfBalls newInstace(){
        return new FragmentOfBalls();
    }

    View v;
    ArrayList<ListForInterface> listSS = new ArrayList<>();
    ArrayList<ListForEge> arrayList = new ArrayList<>();
    EditHelper eSQL;
    EgeAdapter adapter;
    ArrayList<ListForEge> arrayZ = new ArrayList<>();
    DATAHelper dHelp;
    Cursor userCursor;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.university_fragment, container, false);
        
        eSQL = new EditHelper(getContext());
        SQLiteDatabase sql = eSQL.getWritableDatabase();
        Cursor c = sql.query(
                EditHelper.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        int ball = 0;

        if(c.moveToFirst()){
            int discIndex = c.getColumnIndex(EditHelper.COLUMN_DISC);
            int ballIndex = c.getColumnIndex(EditHelper.COLUMN_BALLS);
            do{
                listSS.add(new ListForInterface(c.getString(discIndex),c.getInt(ballIndex)));
                ball+= c.getInt(ballIndex);
            }while (c.moveToNext());
        }
        c.close();


        dHelp = new DATAHelper(getContext());
        dHelp.create_db();
        SQLiteDatabase db;
        db = dHelp.open();
        userCursor =  db.rawQuery("select * from data where" +
                " ball <> 0 and ball <= " + ball , null);
        int sum = 0;

        if(userCursor.moveToFirst()){
            int univerIndex = userCursor.getColumnIndex(DATAHelper.COLUMN_UNIVERSITY);
            int specifIndex = userCursor.getColumnIndex(DATAHelper.COLUMN_SPECIALITY);
            int discIndex = userCursor.getColumnIndex(DATAHelper.COLUMN_DISCIPLINE);
            int ballsIndex = userCursor.getColumnIndex(DATAHelper.COLUMN_BALL);
            int moneyIndex = userCursor.getColumnIndex(DATAHelper.COLUMN_MONEY);
            do{
                arrayList.add(new ListForEge(userCursor.getString(univerIndex),
                        userCursor.getString(discIndex), userCursor.getString(specifIndex),
                        userCursor.getInt(ballsIndex), userCursor.getString(moneyIndex)));
            }while (userCursor.moveToNext());
        }
        userCursor.close();

        for(ListForEge ege : arrayList){
            sum=getBalls(listSS, ege);
            if(ege.getBall() <= sum && search(listSS, ege)){
                arrayZ.add(new ListForEge(ege.getUnivers(), ege.getDisvipl(),
                        ege.getSpecial(), ege.getBall(), ege.getMoney()));
            }
        }

        Collections.sort(arrayZ, new Comparator<ListForEge>() {
            @TargetApi(Build.VERSION_CODES.KITKAT)
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public int compare(ListForEge o1, ListForEge o2) {
                return Integer.compare(o2.getBall(), o1.getBall());
            }
        });
        ListView listView = (ListView)v.findViewById(R.id.listForRait);
        listView.setVerticalScrollBarEnabled(false);
        adapter = new EgeAdapter(getContext(), arrayZ);
        listView.setAdapter(adapter);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(arrayZ.size() == 0){
            dialogFragment = new ErrorInfoDialog();
            dialogFragment.show(getFragmentManager(), "infoBalls");
        }
    }

    public static String[] getDisc(String input) {
        Pattern pattern = Pattern.compile(", ");
        String[] str = pattern.split(input);
        return str;
    }

    public boolean search(ArrayList<ListForInterface> ls, ListForEge disc){
            String[] supDisc = getDisc(disc.getDisvipl());
            int cer = 0;
            for(ListForInterface l : ls){
                for(String str : supDisc){
                    if(l.getDisc().equals(str)){
                        cer++;
                    }
                }
            }
            if(cer == supDisc.length)
                return true;
            else
                return false;

    }

    public int getBalls(ArrayList<ListForInterface> ls, ListForEge disc){
        String[] supDisc = getDisc(disc.getDisvipl());
        int sum = 0;
        for(ListForInterface l : ls){
            for(String str : supDisc){
                if(l.getDisc().equals(str)){
                    sum+=l.getBall();
                }
            }
        }
        return sum;
    }
}
