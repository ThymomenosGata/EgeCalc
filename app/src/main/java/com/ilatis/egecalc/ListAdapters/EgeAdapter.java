package com.ilatis.egecalc.ListAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ilatis.egecalc.R;

import java.util.ArrayList;

/**
 * Created by thymomenosgata on 20.02.18.
 */

public class EgeAdapter extends BaseAdapter {

    Context ctx;
    LayoutInflater inflater;
    ArrayList<ListForEge> objects;

    public EgeAdapter(Context context, ArrayList<ListForEge> products) {
        ctx = context;
        objects = products;
        inflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        // используем созданные, но не используемые view
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.listview, parent, false);
        }

        ListForEge listVV = getListVV(position);

        // заполняем View в пункте списка данными
        ((TextView) view.findViewById(R.id.napr)).setText(String.valueOf(listVV.special));
        ((TextView) view.findViewById(R.id.disciplins)).setText(String.valueOf(listVV.disvipl));
        ((TextView) view.findViewById(R.id.university)).setText(listVV.univers);
        ((TextView) view.findViewById(R.id.balls)).setText(String.valueOf(listVV.ball));
        ((TextView) view.findViewById(R.id.zp)).setText(String.valueOf(listVV.money));

        return view;
    }

    // товар по позиции
    ListForEge getListVV(int position) {
        return ((ListForEge) getItem(position));
    }
}
