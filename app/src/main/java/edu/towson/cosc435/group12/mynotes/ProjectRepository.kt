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
}