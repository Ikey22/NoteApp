package com.example.noteapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NoteDao {
    //Define functions to access database
    @Insert
    suspend fun addNote(note: Note)

    @Query("SELECT * FROM note ")
    suspend fun getAllNotes() : List<Note>

    @Insert
    suspend fun addMultipleNotes(vararg note: Note)
}