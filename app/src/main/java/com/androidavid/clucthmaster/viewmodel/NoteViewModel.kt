package com.androidavid.clucthmaster.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidavid.clucthmaster.model.Note
import com.androidavid.clucthmaster.repository.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(app: Application, private val noteRepository: NoteRepository) :
    AndroidViewModel(app) {

    fun addNote(note: Note) =
        viewModelScope.launch {
            noteRepository.insertNote(note)
        }
    fun deleteNote(note: Note) =
        viewModelScope.launch {
            noteRepository.deleteNote(note)
        }
    fun updateNote(note: Note) =
        viewModelScope.launch {
            noteRepository.updateNote(note)
        }
    fun getAllNotes()= noteRepository.getAllNotes()
    fun searchNotes(query:String?)= noteRepository.searchNotes(query)
}