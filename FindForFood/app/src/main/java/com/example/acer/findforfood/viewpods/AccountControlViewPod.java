package com.example.acer.findforfood.viewpods;

import android.app.Fragment;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.example.acer.findforfood.R;
import com.example.acer.findforfood.data.vo.models.LoginUserModel;
import com.example.acer.findforfood.delegates.BeforeLoginDelegates;
import com.example.acer.findforfood.delegates.LoginUserDelegate;
import com.example.acer.findforfood.events.SuccessLoginEvent;
import com.example.acer.findforfood.events.UserLogoutEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Acer on 1/27/2018.
 */

public class AccountControlViewPod extends FrameLayout {
    @BindView(R.id.vp_before_login)
    BeforeLoginUserViewPod vpBeforeLoginUserViewPod;
    @BindView(R.id.vp_login_user)
    LoginUserViewPod vpLoginUserViewPod;

    private BeforeLoginDelegates beforeLoginDelegates;
    public AccountControlViewPod(@NonNull Context context) {
        super(context);
    }

    public AccountControlViewPod(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AccountControlViewPod(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this,this);
        refreshUserSession();
        EventBus.getDefault().register(this);
    }

    public void setDelegate(BeforeLoginDelegates beforeLoginDelegates)
    {
        vpBeforeLoginUserViewPod.setDelegate(beforeLoginDelegates);
    }

    public void setDelegate(LoginUserDelegate loginUserDelegate)
    {
        vpLoginUserViewPod.setDelegate(loginUserDelegate);
    }

    public void refreshUserSession(){
        if(LoginUserModel.getObjInstance().isUserLogin()){
            vpBeforeLoginUserViewPod.setVisibility(View.GONE);
            vpLoginUserViewPod.setVisibility(View.VISIBLE);
        }else{
            vpBeforeLoginUserViewPod.setVisibility(View.VISIBLE);
            vpLoginUserViewPod.setVisibility(View.GONE);
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginSuccess(SuccessLoginEvent event){
        vpBeforeLoginUserViewPod.setVisibility(View.GONE);
        vpLoginUserViewPod.setVisibility(View.VISIBLE);
        vpLoginUserViewPod.bindData(event.getLoginUser());
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLogoutUser(UserLogoutEvent event){
        vpBeforeLoginUserViewPod.setVisibility(View.VISIBLE);
        vpLoginUserViewPod.setVisibility(View.GONE);
    }
}
