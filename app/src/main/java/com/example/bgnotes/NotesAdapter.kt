package com.example.bgnotes

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.recyclerview.widget.RecyclerView
import com.example.bgnotes.databinding.NoteItemBinding
import room_database.Note
import java.text.SimpleDateFormat
import java.util.*

private lateinit var binding: NoteItemBinding
private lateinit var selectedImageView: ImageView
private lateinit var galleryLauncher: ActivityResultLauncher<String>

class NoteAdapter(private var notes: List<Note>) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        binding = NoteItemBinding.bind(view)
        return NoteViewHolder(binding)
    }

    fun updateData(notes: List<Note>) {
        this.notes = notes
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = notes[position]
        selectedImageView=binding.rvPlaceHolder
        holder.bind(currentNote)
    }

    override fun getItemCount() = notes.size

    inner class NoteViewHolder(private val binding: NoteItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note) {
            binding.rvTitle.text = note.title
            binding.rvDescription.text = note.description
            binding.rvDate.text = note.date
        }
    }
}