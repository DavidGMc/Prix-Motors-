package com.androidavid.prixmotors.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.androidavid.prixmotors.databinding.ItemPdbBinding
import com.androidavid.prixmotors.model.Products

class ProductsAdapter(private val onItemClickListener: (Products) -> Unit) : RecyclerView.Adapter<ProductsAdapter.PrensaViewHolder>(),
    Filterable {

    private var originalProducts: List<Products> = emptyList()
    private var filteredProducts: List<Products> = emptyList()

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

    fun submitList(products: List<Products>) {
        originalProducts = products
        filteredProducts = products
        differ.submitList(products)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList = mutableListOf<Products>()
                val query = constraint?.toString()?.trim() ?: ""

                if (query.isEmpty()) {
                    filteredList.addAll(originalProducts)
                } else {
                    for (product in originalProducts) {
                        if (product.marca.contains(query, ignoreCase = true) ||
                            product.modelo.contains(query, ignoreCase = true)
                        ) {
                            filteredList.add(product)
                        }
                    }
                }

                val filterResults = FilterResults()
                filterResults.values = filteredList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredProducts = results?.values as? List<Products> ?: emptyList()
                differ.submitList(filteredProducts)
            }
        }
    }
}
