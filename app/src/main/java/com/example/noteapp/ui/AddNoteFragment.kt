package com.example.noteapp.ui

import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.noteapp.R
import com.example.noteapp.db.Note
import com.example.noteapp.db.NoteDatabase
import kotlinx.android.synthetic.main.fragment_add_note.*
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 */
@InternalCoroutinesApi
class AddNoteFragment : baseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_note, container, false)
    }

    @InternalCoroutinesApi
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        button_save.setOnClickListener {
            val noteTitle = edit_text_title.text.toString().trim()
            val noteBody = edit_text_note.text.toString().trim()

            if(noteTitle.isEmpty()){
                edit_text_title.error = "Title required"
                edit_text_title.requestFocus()
                return@setOnClickListener
            }
            if (noteBody.isEmpty()){
                edit_text_note.error = "note required"
                edit_text_note.requestFocus()
                return@setOnClickListener
            }

            launch {
                val note = Note(noteTitle, noteBody)
                context?.let {
                    NoteDatabase(it).getNoteDao().addNote(note)
                    it.toast("Note Saved")
                }
            }



        }
    }
//
//    private fun saveNote(note: Note) {
//        class SaveNote : AsyncTask<Void, Void, Void>(){
//            override fun doInBackground(vararg params: Void?): Void? {
//                NoteDatabase(activity!!).getNoteDao().addNote(note)
//                return null
//            }
//
//            override fun onPostExecute(result: Void?) {
//                super.onPostExecute(result)
//                Toast.makeText(activity, "Note saved", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//        SaveNote().execute()
//    }
}
