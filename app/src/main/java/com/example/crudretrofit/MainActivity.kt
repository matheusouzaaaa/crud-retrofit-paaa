package com.example.crudretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.crudretrofit.model.Produto
import com.example.crudretrofit.retrofit.service.ProdutoClientApi

class MainActivity : AppCompatActivity() {
    val produtoClientApi: ProdutoClientApi by lazy { // tipo um NEW, mas é só quando realmente for usar.
        ProdutoClientApi()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun consultarProduto(view: View){
        val cepEdit = findViewById<EditText>(R.id.editTextTextPersonName)
        val textEstado = findViewById<TextView>(R.id.resposta)
        val nomeEdit = findViewById<EditText>(R.id.editNome)
        val precoEdit = findViewById<EditText>(R.id.editPreco)
        val id = cepEdit.text.toString()
        produtoClientApi.buscarProdutoPorId(
            id,
            onSuccess = { produto ->
                nomeEdit.setText(produto?.nome);
                precoEdit.setText(produto?.preco.toString());
            },
            onFail = { Toast.makeText(this,"Errou", Toast.LENGTH_LONG).show()}
        )
    }

    fun cadastrarProdutos(view: View){
        val nome = findViewById<EditText>(R.id.nome).text.toString()
        val preco = findViewById<EditText>(R.id.preco).text.toString()
        val produto = Produto(id = null, nome = nome, preco = preco.toFloat())
        produtoClientApi.cadastrarProdutos(
            produto,
            onSuccess = {
                Toast.makeText(this,"Cadastrado com sucesso", Toast.LENGTH_LONG).show()
            },
            onFail = { Toast.makeText(this,"Errou cadastro", Toast.LENGTH_LONG).show()}
        )
    }

    fun excluirProduto(view: View){
        val idProduto = findViewById<EditText>(R.id.excluir)
        val id = idProduto.text.toString()
        produtoClientApi.excluirProdutoPorId(
            id,
            onSuccess = {Toast.makeText(this,"Excluído com sucesso", Toast.LENGTH_LONG).show()},
            onFail = { Toast.makeText(this,"Errou exclusão", Toast.LENGTH_LONG).show()}
        )
    }

    fun alterarProdutos(view: View){
        val nome = findViewById<EditText>(R.id.editNome).text.toString()
        val preco = findViewById<EditText>(R.id.editPreco).text.toString()
        val id = findViewById<EditText>(R.id.editTextTextPersonName).text.toString()
        val produto = Produto(id = id.toInt(), nome = nome, preco = preco.toFloat())
        produtoClientApi.alterarProdutos(
            produto,
            id,
            onSuccess = {
                Toast.makeText(this,"Atualizado com sucesso", Toast.LENGTH_LONG).show()
            },
            onFail = { Toast.makeText(this,"Errou cadastro", Toast.LENGTH_LONG).show()}
        )
    }
}