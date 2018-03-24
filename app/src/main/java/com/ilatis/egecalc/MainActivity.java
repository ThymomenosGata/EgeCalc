package com.ilatis.egecalc;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ilatis.egecalc.Data.DATAHelper;
import com.ilatis.egecalc.Data.DataRait;
import com.ilatis.egecalc.Data.MSClass;
import com.ilatis.egecalc.Data.StructClass;
import com.ilatis.egecalc.Fragments.AlertDialogMain;
import com.ilatis.egecalc.Fragments.MainFragment;
import com.ilatis.egecalc.Fragments.RaitingFragment;
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
    DATAHelper sqlH;
    DataRait sqlR;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.new_activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        getScreen(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        sqlH = new DATAHelper(getBaseContext());
        sqlR = new DataRait(getBaseContext());
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
                values.put(DATAHelper.COLUMN_MONEY, String.valueOf(un.getMoney()));
                sql.insert(DATAHelper.TABLE_NAME, null, values);
            }
            dialogFragment.dismiss();
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
            ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
            adapter.addFragment(new MainFragment(), "Побальный рейтинг");
            adapter.addFragment(new RaitingFragment(), "Рейтинг зарплат");
            viewPager.setAdapter(adapter);
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
                        exa += ms.getExams().getEge().get(j);
                        if (j < ms.getExams().getEge().size() - 1)
                            exa += ", ";
                    }
                }
                else
                    exa = "?";
                if(ms.getSalary() == 0)
                    s = "?";
                else
                    s = String.valueOf((int)ms.getSalary());
                arrayList.add(new ListForEge(st.getName(), exa, ms.getCommon_name(), ms.getScore(), s));
            }
        }
        return arrayList;
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

    }

    private void getScreen(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new MainFragment(), "Побальный рейтинг");
        adapter.addFragment(new RaitingFragment(), "Рейтинг зарплат");
        viewPager.setAdapter(adapter);
    }
}
