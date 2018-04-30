package com.example.acer.findforfood.network;
import com.example.acer.findforfood.events.LoadGuideEvent;
import com.example.acer.findforfood.network.responses.GetGuideResponse;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Acer on 1/14/2018.
 */

public class GuideRetrofitDataAgent implements GuideDataAgent {
    private static GuideRetrofitDataAgent sObjInstance;

    private GuideApi guideApi;

    private GuideRetrofitDataAgent(){

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

        guideApi=retrofit.create(GuideApi.class);

    }

    public static GuideRetrofitDataAgent getsObjInstance(){
        if(sObjInstance==null)
        {
            sObjInstance=new GuideRetrofitDataAgent();
        }
        return sObjInstance;
    }
    @Override
    public void loadGuide() {

        Call<GetGuideResponse> getGuideResponseCall=guideApi.getGuides(1,"b002c7e1a528b7cb460933fc2875e916");
         getGuideResponseCall.enqueue(new Callback<GetGuideResponse>() {
             @Override
             public void onResponse(Call<GetGuideResponse> call, Response<GetGuideResponse> response) {
                 GetGuideResponse getGuideResponse=response.body();
                 if(getGuideResponse!=null) {
                     LoadGuideEvent event = new LoadGuideEvent(getGuideResponse.getGuides());
                     EventBus.getDefault().post(event);
                 }
             }

             @Override
             public void onFailure(Call<GetGuideResponse> call, Throwable t) {

             }
         });

            }



    }

