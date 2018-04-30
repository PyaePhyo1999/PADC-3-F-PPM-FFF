package com.example.acer.findforfood.data.vo.models;

import com.example.acer.findforfood.data.vo.LoginVO;
import com.example.acer.findforfood.events.SuccessLoginEvent;
import com.example.acer.findforfood.events.UserLogoutEvent;
import com.example.acer.findforfood.network.AccountControlDataAgent;
import com.example.acer.findforfood.network.AccountControlRetrofitDataAgent;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Acer on 1/27/2018.
 */

public class LoginUserModel {
    private static LoginUserModel sObjInstance;
    private LoginVO mloginUser;
    private AccountControlDataAgent mDataAgent;
    private LoginUserModel(){
        mDataAgent = AccountControlRetrofitDataAgent.getsObjInstance();
        EventBus.getDefault().register(this);
    }
    public static LoginUserModel getObjInstance(){

        if(sObjInstance== null){
            sObjInstance = new LoginUserModel();

        }
            return sObjInstance;
    }
    public void loginUser(String phoneNo,String password){
        mDataAgent.loginUser(phoneNo,password);
    }
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onLoginUserSuccess(SuccessLoginEvent event){
        mloginUser = event.getLoginUser();
    }
    public boolean isUserLogin(){
        return mloginUser!=null;
    }
    public void logout(){
        mloginUser = null;
        UserLogoutEvent event = new UserLogoutEvent();
        EventBus.getDefault().post(event);
    }
}
