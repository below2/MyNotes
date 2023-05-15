package edu.towson.cosc435.group12.mynotes

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "samplenotes")
data class SampleData(
    @PrimaryKey val sampleNoteId: String,
    var sampleFront: String,
    var sampleBack: String
) {
}