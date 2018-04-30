package com.example.acer.findforfood.network;

import com.example.acer.findforfood.delegates.LoginUserDelegate;
import com.example.acer.findforfood.events.SuccessLoginEvent;
import com.example.acer.findforfood.events.SuccessRegisterEvent;
import com.example.acer.findforfood.network.responses.LoginResponse;
import com.example.acer.findforfood.network.responses.RegisterRespond;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;
import java.util.regex.PatternSyntaxException;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Acer on 1/27/2018.
 */

public class AccountControlRetrofitDataAgent implements AccountControlDataAgent{
    private static AccountControlRetrofitDataAgent sObjInstance;

    private AccountControlApi accountControlApi;

    private AccountControlRetrofitDataAgent(){

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();


        Retrofit retrofit=new Retrofit.Builder() //2
                .baseUrl("http://padcmyanmar.com/padc-3/burpple-food-places/apis/v1/")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(httpClient)
                .build();
        accountControlApi=retrofit.create(AccountControlApi.class);

    }

    public static AccountControlRetrofitDataAgent getsObjInstance(){
        if(sObjInstance==null)
        {
            sObjInstance=new AccountControlRetrofitDataAgent();
        }
        return sObjInstance;
    }

    @Override
    public void loginUser(String phoneNo, String password) {
        final Call<LoginResponse> getLoginResponseCall = accountControlApi.getLogin(phoneNo,password);
        getLoginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                         LoginResponse loginResponse = response.body();
                         if (loginResponse!=null){
                             SuccessLoginEvent event = new SuccessLoginEvent(loginResponse.getLoginUser());
                             EventBus.getDefault().post(event);
                         }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void RegisterUser(String phoneNo, String password, String name) {
        Call<RegisterRespond> getRegisterRespondCall = accountControlApi.getRegister(phoneNo, password, name);
        getRegisterRespondCall.enqueue(new Callback<RegisterRespond>() {
            @Override
            public void onResponse(Call<RegisterRespond> call, Response<RegisterRespond> response) {
                RegisterRespond registerRespond = response.body();
                if (registerRespond!=null){
                    SuccessRegisterEvent event = new SuccessRegisterEvent(registerRespond.getRegisterUser());
                    EventBus.getDefault().post(event);
                }
            }

            @Override
            public void onFailure(Call<RegisterRespond> call, Throwable t) {

            }
        });

    }
}
