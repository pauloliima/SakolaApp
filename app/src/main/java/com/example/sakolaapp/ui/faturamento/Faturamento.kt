package com.example.sakolaapp.ui.faturamento

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sakolaapp.R

class Faturamento : Fragment() {

    companion object {
        fun newInstance() = Faturamento()
    }

    private lateinit var viewModel: FaturamentoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.faturamento_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FaturamentoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}