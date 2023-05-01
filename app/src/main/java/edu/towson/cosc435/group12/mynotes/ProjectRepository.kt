package edu.towson.cosc435.group12.mynotes

import java.util.*

class ProjectRepository : IProjectRepository {
    private var _projects = listOf<Project>()

    init {
//        _projects = (0..15).map { i ->
//            Project(UUID.randomUUID().toString(), "Project $i")
//        }

        // This will be reverted to above when adding note feature is implemented, but is used for now so projectIds don't need to be passed to NoteRepository
        _projects = (0..4).map { i ->
            Project(i.toString(), "Project $i")
        }
    }

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
//        _projects += Project(UUID.randomUUID().toString(), projectName)
        // WARNING: adding/deleting projects works fine, but for now it is first populated with non-random ids so the code below allows the deletion of 1 and addition of 1 project
        _projects += Project((_projects.lastIndex + 2).toString(), projectName)
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