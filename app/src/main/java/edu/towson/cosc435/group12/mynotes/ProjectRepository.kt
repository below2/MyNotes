package edu.towson.cosc435.group12.mynotes

import java.util.*

class ProjectRepository : IProjectRepository {
    private var _projects = listOf<Project>()

    init { }

    override fun getProjects(): List<Project> {
        return _projects
    }

    override fun getProject(projectId: String): Project {
        for (project in _projects) {
            if (project.projectId == projectId) {
                return project
            }
        }

        return Project("", "")
    }

    override fun addProject(projectName: String) {
        _projects += Project(UUID.randomUUID().toString(), projectName)
    }

    override fun removeProject(project: Project) {
        _projects = _projects.filterNot { it == project }
    }

    override fun editProject(projectId: String, projectName: String) {
        for (project in _projects) {
            if (project.projectId == projectId) {
                project.projectName = projectName
            }
        }
    }
}