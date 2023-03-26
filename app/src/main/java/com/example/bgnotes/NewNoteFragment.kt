package com.example.bgnotes

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.*
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.bgnotes.databinding.FragmentMyNoteBinding
import com.example.bgnotes.databinding.FragmentNewNoteBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import room_database.Note
import room_database.NoteDao
import room_database.NotesDatabase
import java.text.SimpleDateFormat
import java.util.*

class NewNoteFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_note, container, false)
        val view = binding.root
        return view
    }

    private lateinit var binding: FragmentNewNoteBinding
    private var selectedImageUri: Uri? = null
    private lateinit var selectedImageView: ImageView
    private lateinit var db: NotesDatabase
    private lateinit var noteDao: NoteDao
    private lateinit var galleryLauncher: ActivityResultLauncher<String>


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewNoteBinding.bind(view)
        selectedImageView=binding.imgNote
        db = NotesDatabase.invoke(requireContext())


        galleryLauncher =
            registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
                if (uri != null) {
                    selectedImageUri = uri
                    binding.imgNote.setImageURI(selectedImageUri)
                }
            }

        binding.addImageBtn.setOnClickListener {
            galleryLauncher.launch("image/*")
        }

        binding.saveNoteBtn.setOnClickListener {
            val title = binding.textTitle.text.toString()
            val description = binding.textBody.text.toString()
            val dateFormat = SimpleDateFormat("dd-MMM-yyyy hh:mm a", Locale.getDefault())
            val currentDate = Date()
            val dateText = dateFormat.format(currentDate)
            val note = Note (title = title, description = description, date = dateText)

            if (title.isEmpty() && description.isEmpty()) {
                Toast.makeText(requireContext(), "Empty Note", Toast.LENGTH_SHORT).show()
            } else {
                CoroutineScope(Dispatchers.IO).launch {
                    val dao = db.getnoteDao().insert(note)
                }
                Toast.makeText(
                    requireContext(),
                    "Note has been saved successfully",
                    Toast.LENGTH_SHORT
                ).show()
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.nav_container, MyNoteFragment()).addToBackStack(null).commit()
            }

        }
    }
}