package com.example.acer.findforfood.network;

import com.example.acer.findforfood.network.responses.GetGuideResponse;
import com.example.acer.findforfood.network.responses.GetPromotionResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Acer on 1/14/2018.
 */

public interface GuideApi {
    @FormUrlEncoded
    @POST("getGuides.php")
    Call<GetGuideResponse> getGuides(@Field("page") int page,
                                         @Field("access_token") String accessToken);



}
