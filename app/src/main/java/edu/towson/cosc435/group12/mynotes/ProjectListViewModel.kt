package edu.towson.cosc435.group12.mynotes

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProjectListViewModel : ViewModel() {
    private val _projects: MutableState<List<Project>> = mutableStateOf(listOf())
    val projects: State<List<Project>> = _projects

    private val _repository: IProjectRepository = ProjectRepository()

    private var showSampleRequest = true

    init {
        _projects.value = _repository.getProjects()
    }

    fun getProjects(): List<Project> {
        return _repository.getProjects()
    }

    fun getProject(projectId: String): Project {
        return _repository.getProject(projectId)
    }

    fun addProject(project: Project) {
        _repository.addProject(project)
        _projects.value = _repository.getProjects()
    }

    fun addProjectDB(projectDao: ProjectDAO, project: Project) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                projectDao.insertProject(project)
            }
        }
    }

    fun removeProject(project: Project, notevm: NoteListViewModel) {
        for (note in notevm.getNotes()) {
            if (note.projectId == project.projectId) {
                notevm.removeNote(note)
            }
        }
        _repository.removeProject(project)
        _projects.value = _repository.getProjects()
    }

    fun removeProjectDB(projectDao: ProjectDAO, noteDao: NoteDAO, project: Project, notes: List<Note>) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                for (note in notes) {
                    noteDao.deleteNote(note)
                }
                projectDao.deleteProject(project)
            }
        }
    }

    fun editProject(projectId: String, projectName: String) {
        _repository.editProject(projectId, projectName)
        _projects.value = _repository.getProjects()
    }

    fun editProjectDB(projectDao: ProjectDAO, projectId: String, projectName: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                projectDao.updateProject(Project(projectId, projectName))
            }
        }
    }

    fun setSampleRequestState(state: Boolean) {
        showSampleRequest = state
    }

    fun getSampleRequestState() : Boolean{
        return showSampleRequest
    }
}