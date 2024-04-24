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

class ProductsAdapter(private val onItemClickListener: (Products) -> Unit) : RecyclerView.Adapter<ProductsAdapter.PrensaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrensaViewHolder {
        val binding = ItemPdbBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PrensaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PrensaViewHolder, position: Int) {
        val currentPrensa = differ.currentList[position]
        holder.bind(currentPrensa)
        holder.itemView.setOnClickListener {
            onItemClickListener(currentPrensa)
        }

        // Ajustar dinámicamente el ancho del elemento
        val displayMetrics = holder.itemView.context.resources.displayMetrics
        val screenWidth = displayMetrics.widthPixels
        val itemWidth = screenWidth / 2 // Dividir por 2 para que quepan hasta 2 elementos en el ancho de la pantalla
        val layoutParams = holder.itemView.layoutParams
        layoutParams.width = itemWidth
        holder.itemView.layoutParams = layoutParams
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class PrensaViewHolder(private val binding: ItemPdbBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(products: Products) {
            binding.apply {
                anoTextView.text = products.ano.toString()
                modeloTextView.text = products.modelo
                marcaTextView.text = products.marca
                imageView.load(products.picture_uno) {
                    listener(onError = { _, throwable ->
                        Log.e("PrensaAdapter", "Error al cargar la imagen: ${throwable.throwable?.message}")
                    })
                }
            }
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<Products>() {
        override fun areItemsTheSame(oldItem: Products, newItem: Products): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Products, newItem: Products): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
}
