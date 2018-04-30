package com.example.acer.findforfood.events;

import com.example.acer.findforfood.data.vo.LoginVO;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Acer on 1/27/2018.
 */

public class SuccessLoginEvent {
    private LoginVO loginUser;

    public SuccessLoginEvent(LoginVO loginUser)
    {
      this.loginUser = loginUser;
    }
    public LoginVO getLoginUser(){
        return loginUser;
    }








}
