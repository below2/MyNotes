package edu.towson.cosc435.group12.mynotes

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SampleDataDAO {
    @Insert
    fun insert(sampleNote: SampleData)

    @Query("SELECT * FROM samplenotes")
    fun getAllUsers(): List<SampleData>
}