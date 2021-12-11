package com.example.sakolaapp.ui.estoque

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sakolaapp.Activities.AdicionarEstoque
import com.example.sakolaapp.R
import com.example.sakolaapp.functional.adapters.recycleradapters.RecyclerAdapterEstoque
import kotlinx.android.synthetic.main.estoque_fragment.*
import kotlinx.android.synthetic.main.produtos_fragment.*

class Estoque : Fragment() {

    companion object {
        fun newInstance() = Estoque()
    }

    private lateinit var viewModel: EstoqueViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.estoque_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EstoqueViewModel::class.java)

        val layoutManager = LinearLayoutManager(context)
        val adapter = RecyclerAdapterEstoque()

        RecyclerEstoque.layoutManager = layoutManager
        RecyclerEstoque.adapter = adapter

        FABAddItemEstoque.setOnClickListener {
            val intent = Intent(context, AdicionarEstoque::class.java)
            startActivity(intent)
        }
    }

}