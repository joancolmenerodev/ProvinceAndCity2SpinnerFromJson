package com.example.joancolmenero.provinceandcity2spinnersfromjson.network;

import com.example.joancolmenero.provinceandcity2spinnersfromjson.model.ResponseJSON;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("mobile/province")
    Call<Map<String,ResponseJSON>> getProvinceAndCity();

}