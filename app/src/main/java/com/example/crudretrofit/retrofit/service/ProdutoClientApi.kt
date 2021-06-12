package com.example.crudretrofit.retrofit.service

import com.example.crudretrofit.model.Produto
import com.example.crudretrofit.retrofit.AppRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProdutoClientApi (private val produtoService: ProdutoService = AppRetrofit().produtoService) {
    fun buscarProdutoPorId(
        id:String,
        onSuccess: (produto: Produto?) -> Unit,
        onFail: (erro: String?) -> Unit
    ){
        produtoService.buscarProdutoPorId(id).enqueue(object: Callback<Produto> { // trata de forma assincrona para não impactar na aplicação depois. para não precisar criar coroutines.
            override fun onFailure(call: Call<Produto>, t: Throwable) {
                onFail(t.message)
            }

            override fun onResponse(call: Call<Produto>, response: Response<Produto>) {
                if(response.isSuccessful){
                    onSuccess(response.body())
                }else{
                    onFail("Erro não identificado")
                }
            }
        })
    }

    fun excluirProdutoPorId(
        id:String,
        onSuccess: (produto: Produto?) -> Unit,
        onFail: (erro: String?) -> Unit
    ){
        produtoService.excluirProdutoPorId(id).enqueue(object: Callback<Produto> { // trata de forma assincrona para não impactar na aplicação depois. para não precisar criar coroutines.
            override fun onFailure(call: Call<Produto>, t: Throwable) {
                onFail(t.message)
            }

            override fun onResponse(call: Call<Produto>, response: Response<Produto>) {
                if(response.isSuccessful){
                    onSuccess(response.body())
                }else{
                    onFail("Erro não identificado")
                }
            }
        })
    }

    fun cadastrarProdutos(
        userData: Produto,
        onSuccess: (produto: Produto?) -> Unit,
        onFail: (erro: String?) -> Unit
    ){
        produtoService.cadastrarProdutos(userData).enqueue(
            object : Callback<Produto> {
                override fun onFailure(call: Call<Produto>, t: Throwable) {
                    onFail(t.message)
                }
                override fun onResponse( call: Call<Produto>, response: Response<Produto>) {
                    if(response.isSuccessful){
                        onSuccess(response.body())
                    }else{
                        onFail("Erro não identificado")
                    }
                }
            }
        )
    }


}