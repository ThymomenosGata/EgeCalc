package com.ilatis.egecalc.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ilatis.egecalc.R;

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
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.university_fragment, container, false);
        return v;
    }
}
