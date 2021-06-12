package com.example.crudretrofit.retrofit.service

import com.example.crudretrofit.model.Produto
import retrofit2.Call
import retrofit2.http.*

interface ProdutoService {
    @GET("/produtos/{id}")
    fun buscarProdutoPorId(@Path("id") id: String): Call<Produto>

    @DELETE("/produtos/{id}")
    fun excluirProdutoPorId(@Path("id") id: String): Call<Produto>

    @Headers("Content-type: application/json")
    @POST("/produtos")
    fun cadastrarProdutos(@Body userData: Produto): Call<Produto>

}