package room_database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {
    @Query("SELECT * FROM note")
    fun getAllNotes(): List<Note>

    @Insert
    fun insert(note: Note)

    @Delete
    fun delete(note: Note)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertWithDate(note: Note)

    @Insert
    fun addMultipleNotes (vararg notes: Note)
}