package com.example.myapplication.services;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APITicket {

@POST("ticket/save")
@FormUrlEncoded
Call<JsonObject> guardarTicket(@Field("ticket")String ticket);
}
