package com.example.bgnotes

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.*
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.bgnotes.databinding.FragmentMyNoteBinding
import com.example.bgnotes.databinding.FragmentNewNoteBinding

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
    private lateinit var galleryLauncher: ActivityResultLauncher<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewNoteBinding.bind(view)
        selectedImageView=binding.imgNote

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
    }
}