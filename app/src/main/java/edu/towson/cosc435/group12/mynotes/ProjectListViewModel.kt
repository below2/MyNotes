package edu.towson.cosc435.group12.mynotes

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ProjectListViewModel : ViewModel() {
    private val _projects: MutableState<List<Project>> = mutableStateOf(listOf())
    val projects: State<List<Project>> = _projects

    private val _repository: IProjectRepository = ProjectRepository()

    init {
        _projects.value = _repository.getProjects()
    }
}