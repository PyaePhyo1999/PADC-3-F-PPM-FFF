package com.example.acer.findforfood.viewpods;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.acer.findforfood.R;
import com.example.acer.findforfood.delegates.BeforeLoginDelegates;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Acer on 1/26/2018.
 */

public class BeforeLoginUserViewPod extends RelativeLayout {
    private BeforeLoginDelegates mDelegate;





    public BeforeLoginUserViewPod(Context context) {
        super(context);
    }

    public BeforeLoginUserViewPod(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BeforeLoginUserViewPod(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this,this);
    }

    @OnClick (R.id.btn_to_login)
    public void onTapToLogin(View view){
        mDelegate.onTapLogin();
    }
    @OnClick (R.id.btn_to_register)
    public void onTapToSignUp(View view){
        mDelegate.onTapSignUp();
    }

    public void setDelegate(BeforeLoginDelegates delegate){
        this.mDelegate = delegate;
    }


}
