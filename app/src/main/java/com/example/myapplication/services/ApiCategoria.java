package com.example.myapplication.services;

import com.example.myapplication.model.Categoria;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiCategoria {

    @GET("Categoria/getAll")
    Call<List<Categoria>> obtenerCategorias();}
