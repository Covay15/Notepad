package room_database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun getnoteDao(): NoteDao

    companion object {
        @Volatile
        private var INSTANCE: NotesDatabase? = null
        private val Lock = Any()

        operator fun invoke (context: Context): NotesDatabase {
            return INSTANCE ?: synchronized(Lock) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotesDatabase::class.java,
                    "note_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}