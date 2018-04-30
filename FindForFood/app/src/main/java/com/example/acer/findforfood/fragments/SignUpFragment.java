package com.example.acer.findforfood.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.acer.findforfood.R;

import butterknife.ButterKnife;

/**
 * Created by Acer on 1/26/2018.
 */

public class SignUpFragment extends android.support.v4.app.Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up,container,false);
        ButterKnife.bind(this,view);
        return view;

    }
}
