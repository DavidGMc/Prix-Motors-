package com.androidavid.clucthmaster.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.androidavid.clucthmaster.databinding.ItemPdbBinding
import com.androidavid.clucthmaster.model.Products

class HomeAdapter(private val onItemClickListener: (Products) -> Unit): RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): HomeViewHolder {
      return HomeViewHolder(ItemPdbBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val currentBalinera = differ.currentList[position]

        holder.itemPdbBinding.anoTextView.text= currentBalinera.ano.toString()
        holder.itemPdbBinding.modeloTextView.text= currentBalinera.modelo
        holder.itemPdbBinding.marcaTextView.text= currentBalinera.marca
        holder.itemPdbBinding.imageView.load(currentBalinera.picture_uno) {
            listener(onError = { _, throwable ->
                Log.e("PrensaAdapter", "Error al cargar la imagen: ${throwable.throwable.message}")
            })
        }
        holder.itemView.setOnClickListener {
            onItemClickListener(currentBalinera)

        }
        // Ajustar dinámicamente el ancho del elemento
        val displayMetrics = holder.itemView.context.resources.displayMetrics
        val screenWidth = displayMetrics.widthPixels
        val itemWidth = screenWidth / 3 // Dividir por 3 para que quepan hasta 3 elementos en el ancho de la pantalla
        val layoutParams = holder.itemView.layoutParams
        layoutParams.width = itemWidth
        holder.itemView.layoutParams = layoutParams

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
    class HomeViewHolder(val itemPdbBinding: ItemPdbBinding): RecyclerView.ViewHolder(itemPdbBinding.root)
    private val differCallback = object : DiffUtil.ItemCallback<Products>(){
        override fun areItemsTheSame(oldItem: Products, newItem: Products): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.ano == newItem.ano &&
                    oldItem.picture_uno == newItem.picture_uno &&
                    oldItem.marca == newItem.marca &&
                    oldItem.modelo == newItem.modelo
        }

        override fun areContentsTheSame(oldItem: Products, newItem: Products): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, differCallback)



}