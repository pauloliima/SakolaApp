package com.example.sakolaapp.Fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sakolaapp.R
import com.example.sakolaapp.functional.adapters.DBO.AdicionarCarrinhoFirebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso
import com.xwray.groupie.*
import kotlinx.android.synthetic.main.cabecalho_confirmar_compra.*
import kotlinx.android.synthetic.main.carrinho_layout.*
import kotlinx.android.synthetic.main.carrinho_layout.view.*
import kotlinx.android.synthetic.main.deixar_pedido_layout.view.*

class EntregaFragment : Fragment() {

    companion object {
        fun newInstance() = EntregaFragment()
    }

    lateinit var adapter: GroupAdapter<GroupieViewHolder>

    val adapter2 = adapterMenu()

    val uid = FirebaseAuth.getInstance().currentUser.toString()

    private lateinit var viewModel: EntregaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.cabecalho_confirmar_compra, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val layoutManager = LinearLayoutManager(context)
        recycler_Carrinho.layoutManager = layoutManager
        recycler_Carrinho.adapter = adapter

        val layoutManager2 = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recycler_Deixar_Pedido.layoutManager = layoutManager2
        recycler_Deixar_Pedido.adapter = adapter2
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EntregaViewModel::class.java)

    }

    private inner class CarrinhoItem(internal val adProdutos: AdicionarCarrinhoFirebase) :
        Item<GroupieViewHolder>() {
        override fun getLayout(): Int = R.layout.carrinho_layout

        override fun bind(viewHolder: GroupieViewHolder, position: Int) {

            viewHolder.itemView.nomeProdutoCarrinho.text = adProdutos.produto
            viewHolder.itemView.QtdProdutoCarrinho.text = adProdutos.qtd
            viewHolder.itemView.PriceItemCarrinho.text = adProdutos.price

            Picasso.get().load(adProdutos.img).into(ImgItemCarrinho)

        }

    }

    private fun buscarProduto() {
        FirebaseFirestore.getInstance().collection(uid)
            .document()
            .collection("Carrinho")
            .addSnapshotListener { value, error ->
                error?.let {
                    return@addSnapshotListener
                }
                value?.let {
                    for (doc in it) {
                        val produtos = doc.toObject(AdicionarCarrinhoFirebase::class.java)
                        adapter.add(CarrinhoItem(produtos))
                    }
                }
            }
    }

    val list = listOf<String>(
        "No portão da Casa/Prédio",
        "Com o porteiro do Prédio/Condominio",
        "Encontrar com Entregador"
    )

    inner class adapterMenu(): RecyclerView.Adapter<adapterMenu.viewHolder>(){
        inner class viewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.deixar_pedido_layout, parent, false)
            return viewHolder(view)
        }

        override fun onBindViewHolder(holder: viewHolder, position: Int) {
            holder.itemView.item_deixar_pedido.text = list[position]
            holder.itemView.setOnClickListener {
            }
        }

        override fun getItemCount(): Int = list.size
    }

}