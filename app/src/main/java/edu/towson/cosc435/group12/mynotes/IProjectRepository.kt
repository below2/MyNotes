package edu.towson.cosc435.group12.mynotes

interface IProjectRepository {
    fun getProjects(): List<Project>
    fun getProject(projectId: String): Project
    fun addProject(project: Project)
    fun removeProject(project: Project)
    fun editProject(projectId: String, projectName: String)
}