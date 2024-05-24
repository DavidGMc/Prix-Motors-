package com.androidavid.prixmotors.repository

import com.androidavid.prixmotors.database.NoteDatabase
import com.androidavid.prixmotors.model.Note

class NoteRepository(private val db:NoteDatabase) {

    suspend fun insertNote (note: Note) = db.getNoteDao().insertNote(note)
    suspend fun updateNote (note: Note) = db.getNoteDao().updateNote(note)
    suspend fun deleteNote (note: Note) = db.getNoteDao().deleteNote(note)

    fun getAllNotes()= db.getNoteDao().getAllNotes()
    fun searchNotes(query:String?)= db.getNoteDao().searchNote(query)

}