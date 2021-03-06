package com.example.sakolaapp.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sakolaapp.R
import com.example.sakolaapp.functional.adapters.recycleradapters.RecyclerDetalhePedidos
import kotlinx.android.synthetic.main.activity_detalhes_pedido.*





class DetalhesPedido : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes_pedido)

        val layoutManager = LinearLayoutManager(this)
        val adapter = RecyclerDetalhePedidos()

        recycler_detalhe_pedidos.layoutManager = layoutManager
        recycler_detalhe_pedidos.adapter = adapter

    }
}