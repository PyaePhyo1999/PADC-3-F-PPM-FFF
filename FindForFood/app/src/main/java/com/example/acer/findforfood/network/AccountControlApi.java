package com.example.acer.findforfood.network;

import com.example.acer.findforfood.network.responses.LoginResponse;
import com.example.acer.findforfood.network.responses.RegisterRespond;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Acer on 1/27/2018.
 */

public interface AccountControlApi {
    @FormUrlEncoded
    @POST("login.php")
    Call<LoginResponse> getLogin(@Field("phoneNo") String phoneNo,
                                 @Field("password") String password);
    @FormUrlEncoded
    @POST("register.php")
    Call<RegisterRespond> getRegister(@Field("phoneNo") String phoneNo,
                                      @Field("password") String password,
                                      @Field("name") String name);
}
