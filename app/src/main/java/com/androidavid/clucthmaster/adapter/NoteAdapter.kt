package com.androidavid.clucthmaster.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.androidavid.clucthmaster.databinding.ItemNotesBinding

import com.androidavid.clucthmaster.fragment.FragmentNotesDirections
import com.androidavid.clucthmaster.model.Note

class NoteAdapter: RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return  NoteViewHolder(ItemNotesBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun getItemCount(): Int {
        return  differ.currentList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = differ.currentList[position]
        holder.itemBinding.noteTitle.text = currentNote.noteTitle
        holder.itemBinding.noteDesc.text = currentNote.noteDesc
        holder.itemView.setOnClickListener{
           val directions = FragmentNotesDirections.actionFragmentNotesToFragmentEditNote(currentNote)
            it.findNavController().navigate(directions)
        }
    }

    class NoteViewHolder(val itemBinding: ItemNotesBinding):RecyclerView.ViewHolder(itemBinding.root)

        private val differCallback = object : DiffUtil.ItemCallback<Note>(){
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





}