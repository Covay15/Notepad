package com.example.bgnotes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bgnotes.databinding.FragmentMyNoteBinding
import room_database.NoteDao
import room_database.Note

class MyNoteFragment : Fragment() {
    private val notes = mutableListOf<Note>()
    private lateinit var noteAdapter: NoteAdapter
    private lateinit var noteDao: NoteDao
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_note, container, false)
    }

    private lateinit var binding: FragmentMyNoteBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMyNoteBinding.bind(view)

        noteAdapter=NoteAdapter(notes)
        binding.rvNotes.layoutManager = LinearLayoutManager (context)
        binding.rvNotes.adapter = noteAdapter

        binding.FLaddNotes.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.nav_container, NewNoteFragment()).addToBackStack(null).commit()

        }

    }
}