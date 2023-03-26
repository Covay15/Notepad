package com.example.bgnotes

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bgnotes.databinding.FragmentMyNoteBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import room_database.NoteDao
import room_database.Note
import room_database.NotesDatabase

class MyNoteFragment : Fragment() {
    private val notes = mutableListOf<Note>()
    private lateinit var noteAdapter: NoteAdapter
    private lateinit var noteDao: NoteDao
    private lateinit var db: NotesDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_note, container, false)
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.home_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private lateinit var binding: FragmentMyNoteBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        binding = FragmentMyNoteBinding.bind(view)

        noteAdapter = NoteAdapter(notes)
        binding.rvNotes.layoutManager = LinearLayoutManager(context)
        binding.rvNotes.adapter = noteAdapter
        db = NotesDatabase.invoke(requireContext())

        CoroutineScope(Dispatchers.IO).launch {
            val notes = db.getnoteDao().getAllNotes()

            withContext(Dispatchers.Main) {
                noteAdapter.updateData(notes)

                binding.FLaddNotes.setOnClickListener {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_container, NewNoteFragment()).addToBackStack(null)
                        .commit()

                }

            }
        }
    }
}