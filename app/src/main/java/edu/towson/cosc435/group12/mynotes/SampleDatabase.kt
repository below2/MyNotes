package edu.towson.cosc435.group12.mynotes

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SampleData::class], version = 2)
abstract class SampleDatabase : RoomDatabase() {
    abstract fun sampleDataDao(): SampleDataDAO

    companion object {
        @Volatile
        private var INSTANCE: SampleDatabase? = null

        fun getInstance(context: Context): SampleDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SampleDatabase::class.java,
                    "new-database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
