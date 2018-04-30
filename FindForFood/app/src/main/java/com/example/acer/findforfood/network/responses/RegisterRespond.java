package com.example.acer.findforfood.network.responses;

import com.example.acer.findforfood.data.vo.LoginVO;
import com.example.acer.findforfood.data.vo.RegisterVO;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Acer on 1/27/2018.
 */

public class RegisterRespond {
    private int code;
    private String message;
    @SerializedName("register_user")
    private RegisterVO registerUser;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public RegisterVO getRegisterUser() {
        return registerUser;
    }
}
