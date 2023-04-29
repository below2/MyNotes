package edu.towson.cosc435.group12.mynotes

interface IProjectRepository {
    fun getProjects(): List<Project>
    fun addProject(projectName : String)
    fun removeProject(project : Project)
}