package com.androidavid.prixmotors.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.androidavid.prixmotors.databinding.ItemNotesBinding
import com.androidavid.prixmotors.fragment.FragmentNotesDirections
import com.androidavid.prixmotors.model.Note

class NoteAdapter: RecyclerView.Adapter<NoteAdapter.NoteViewHolder>(), Filterable {

    private var originalNotes: List<Note> = emptyList()
    private var filteredNotes: List<Note> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(ItemNotesBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return filteredNotes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = filteredNotes[position]
        holder.itemBinding.noteTitle.text = currentNote.noteTitle
        holder.itemBinding.noteDesc.text = currentNote.noteDesc
        holder.itemView.setOnClickListener {
            val directions = FragmentNotesDirections.actionFragmentNotesToFragmentEditNote(currentNote)
            it.findNavController().navigate(directions)
        }
    }

    class NoteViewHolder(val itemBinding: ItemNotesBinding) : RecyclerView.ViewHolder(itemBinding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.noteDesc == newItem.noteDesc &&
                    oldItem.noteTitle == newItem.noteTitle
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    fun submitList(notes: List<Note>) {
        originalNotes = notes
        filteredNotes = notes
        differ.submitList(notes)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList = mutableListOf<Note>()
                val query = constraint?.toString()?.trim() ?: ""

                if (query.isEmpty()) {
                    filteredList.addAll(originalNotes)
                } else {
                    for (note in originalNotes) {
                        if (note.noteTitle.contains(query, ignoreCase = true) ||
                            note.noteDesc.contains(query, ignoreCase = true)) {
                            filteredList.add(note)
                        }
                    }
                }

                val filterResults = FilterResults()
                filterResults.values = filteredList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredNotes = results?.values as? List<Note> ?: emptyList()
                differ.submitList(filteredNotes)
            }
        }
    }
}
