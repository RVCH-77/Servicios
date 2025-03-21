package com.example.myapplication.services;

import com.example.myapplication.model.Saludar;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface LoginService {
    @GET("saludo/Saludar")
    Call<Saludar> pedirSaludo();

    @FormUrlEncoded
    @POST("saludo/validar")
    Call<JsonObject> validar(@Field("usuario") String usuario,
                             @Field("contrasenia") String apellido);

}
