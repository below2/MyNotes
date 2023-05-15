package edu.towson.cosc435.group12.mynotes

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
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

    override fun addProject(project: Project) {
        _projects += project
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