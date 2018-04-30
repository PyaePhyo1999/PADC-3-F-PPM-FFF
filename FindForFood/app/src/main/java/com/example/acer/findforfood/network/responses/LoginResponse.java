package com.example.acer.findforfood.network.responses;

import com.example.acer.findforfood.data.vo.LoginVO;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Acer on 1/27/2018.
 */

public class LoginResponse {
    private int code;
    private String message;
    @SerializedName("login_user")
    private LoginVO loginUser;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public LoginVO getLoginUser() {
        return loginUser;
    }
}
