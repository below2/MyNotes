package edu.towson.cosc435.group12.mynotes

import java.util.*

class ProjectRepository : IProjectRepository {
    private var _projects = listOf<Project>()

    init {
        _projects = (0..15).map { i ->
            Project(UUID.randomUUID().toString(), "Project $i")
        }
    }

    override fun getProjects(): List<Project> {
        return _projects
    }
}