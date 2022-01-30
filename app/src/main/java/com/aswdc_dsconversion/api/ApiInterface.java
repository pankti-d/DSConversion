package com.aswdc_dsconversion.api;

import com.aswdc_dsconversion.Bean.Model_ApiResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("PostAppFeedback/AppPostFeedback")
    Call<Model_ApiResponse> insertFeedback(@Header("API_KEY") String API_KEY,
                                           @Field("AppName") String a,
                                           @Field("VersionNo") String b,
                                           @Field("Platform") String c,
                                           @Field("PersonName") String d,
                                           @Field("Mobile") String e,
                                           @Field("Email") String f,
                                           @Field("Message") String g,
                                           @Field("Remarks") String h);
}


