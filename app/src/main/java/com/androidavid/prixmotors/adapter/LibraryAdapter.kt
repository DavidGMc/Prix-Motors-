import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.androidavid.prixmotors.databinding.ItemCategoriesBinding
import com.androidavid.prixmotors.model.Category

class LibraryAdapter(private val onItemClickListener: (Category) -> Unit) : RecyclerView.Adapter<LibraryAdapter.CategoryViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ItemCategoriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val currentCategory = differ.currentList[position]
        holder.binding.textViewCategoryName.text = currentCategory.nombre
        // Asignar el icono de la categoría
        val context = holder.itemView.context
        val resourceId = context.resources.getIdentifier(currentCategory.iconoResId, "drawable", context.packageName)
        holder.binding.imageViewCategoryIcon.setImageResource(resourceId)
        holder.itemView.setOnClickListener {
            onItemClickListener(currentCategory)
        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    inner class CategoryViewHolder( val binding: ItemCategoriesBinding) : RecyclerView.ViewHolder(binding.root)

    // Differ para manejar las diferencias en la lista de categorías
    private val differCallback = object : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }
    }


     val differ = AsyncListDiffer(this, differCallback)

}
