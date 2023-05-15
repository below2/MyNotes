package edu.towson.cosc435.group12.mynotes

import androidx.room.*

@Dao
interface ProjectDAO {
    @Query("SELECT * FROM projectstable")
    fun getAllProjects(): List<Project>

    @Query("SELECT * FROM projectstable WHERE projectid = :noteId")
    fun getProjectById(noteId: String): Project?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProject(project: Project)

    @Update
    fun updateProject(project: Project)

    @Delete
    fun deleteProject(project: Project)

    @Query("DELETE FROM projectstable")
    fun deleteAllProjects()
}