package edu.towson.cosc435.group12.mynotes

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Project::class], version = 1)
abstract class ProjectDatabase : RoomDatabase() {
    abstract fun projectDao(): ProjectDAO

    companion object {
        @Volatile
        private var INSTANCE: ProjectDatabase? = null

        fun getInstance(context: Context): ProjectDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProjectDatabase::class.java,
                    "project-database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
