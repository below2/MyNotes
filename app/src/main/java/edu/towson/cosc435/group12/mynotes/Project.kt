package edu.towson.cosc435.group12.mynotes

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "projectstable")
data class Project(
    @PrimaryKey val projectId: String,
    var projectName: String
)