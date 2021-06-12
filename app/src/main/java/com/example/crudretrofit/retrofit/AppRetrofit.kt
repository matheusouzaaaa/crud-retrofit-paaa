package com.example.crudretrofit.retrofit

import com.example.crudretrofit.retrofit.service.ProdutoService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "http://10.0.2.2:3000/"

class AppRetrofit() {

    //associando a varival retrofit
    val retrofit by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    }

    val produtoService by lazy {
        retrofit.create(ProdutoService::class.java)
    }


}