package com.example.sakolaapp.Fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sakolaapp.R

class RetiradaFragment : Fragment() {

    companion object {
        fun newInstance() = RetiradaFragment()
    }

    private lateinit var viewModel: RetiradaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.retirada_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RetiradaViewModel::class.java)
        // TODO: Use the ViewModel
    }

}