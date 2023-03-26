package room_database

import android.provider.ContactsContract
import androidx.room.*

@Entity(tableName = "note")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val date: String,
    val label: String? = null
)