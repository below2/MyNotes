package edu.towson.cosc435.group12.mynotes
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notestable")
data class Note(
    @PrimaryKey val noteId: String,
    val projectId: String,
    var front: String,
    var back: String
    ) {
}