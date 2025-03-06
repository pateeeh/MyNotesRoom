package com.example.mynotesroom.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.mynotesroom.database.Note
import com.example.mynotesroom.database.NoteDao
import com.example.mynotesroom.database.NoteRoomDatabase
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class NoteRepository(application: Application) {
    private val mNotesDao: NoteDao
    private val executorService = Executors.newSingleThreadExecutor()
    init {
        val db = NoteRoomDatabase.getDatabase(application)
        mNotesDao = db.noteDao()
    }
    fun getAllNotes(): LiveData<List<Note>> = mNotesDao.getAllNotes()
    fun insert(note: Note) {
        executorService.execute{mNotesDao.insert(note)}
    }
    fun delete(note:Note) {
        executorService.execute{mNotesDao.delete(note)}
    }
    fun update(note:Note) {
        executorService.execute{mNotesDao.update(note)}
    }

}