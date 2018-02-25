package com.ilatis.egecalc.ListAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ilatis.egecalc.Parser.HelperClasses.ClassRaiting;
import com.ilatis.egecalc.R;

import java.util.ArrayList;

/**
 * Created by thymomenosgata on 26.02.18.
 */

public class RaitingAdapter extends BaseAdapter {

    Context ctx;
    LayoutInflater inflater;
    ArrayList<ClassRaiting> objects;

    public RaitingAdapter(Context ctx, ArrayList<ClassRaiting> objects) {
        this.ctx = ctx;
        this.inflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.objects = objects;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.raiting_list, parent, false);
        }

        ClassRaiting list = getRaiting(position);

        // заполняем View в пункте списка данными
        ((TextView) view.findViewById(R.id.uni)).setText(String.valueOf(list.getName()));
        ((TextView) view.findViewById(R.id.raitProc)).setText(String.valueOf(list.getProc()));
        ((TextView) view.findViewById(R.id.raitZp)).setText(list.getMoney());

        return view;
    }

    ClassRaiting getRaiting(int position) {
        return ((ClassRaiting) getItem(position));
    }
}
