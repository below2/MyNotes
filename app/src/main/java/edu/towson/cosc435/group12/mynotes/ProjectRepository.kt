package edu.towson.cosc435.group12.mynotes

import java.util.*


//edit with use of database class ProjectRepository(context: Context) : IProjectRepository {
//    private val db: SQLiteDatabase
//
//    init {
//        val dbHelper = DBHelper(context)
//        db = dbHelper.writableDatabase
//    }
//
//    override fun getProjects(): List<Project> {
//        val cursor = db.query(
//            TABLE_NAME,
//            arrayOf(COLUMN_ID, COLUMN_NAME),
//            null,
//            null,
//            null,
//            null,
//            null
//        )
//        val projects = mutableListOf<Project>()
//        with(cursor) {
//            while (moveToNext()) {
//                val id = getString(getColumnIndexOrThrow(COLUMN_ID))
//                val name = getString(getColumnIndexOrThrow(COLUMN_NAME))
//                projects.add(Project(id, name))
//            }
//        }
//        return projects
//    }
//
//    override fun getProject(projectId: String): Project {
//        val cursor = db.query(
//            TABLE_NAME,
//            arrayOf(COLUMN_ID, COLUMN_NAME),
//            "$COLUMN_ID = ?",
//            arrayOf(projectId),
//            null,
//            null,
//            null
//        )
//        with(cursor) {
//            if (moveToNext()) {
//                val id = getString(getColumnIndexOrThrow(COLUMN_ID))
//                val name = getString(getColumnIndexOrThrow(COLUMN_NAME))
//                return Project(id, name)
//            }
//        }
//        return Project("", "")
//    }
//
//    override fun addProject(projectName: String) {
//        val values = ContentValues().apply {
//            put(COLUMN_ID, UUID.randomUUID().toString())
//            put(COLUMN_NAME, projectName)
//        }
//        db.insert(TABLE_NAME, null, values)
//    }
//
//    override fun removeProject(project: Project) {
//        db.delete(
//            TABLE_NAME,
//            "$COLUMN_ID = ?",
//            arrayOf(project.projectId)
//        )
//    }
//
//    override fun editProject(projectId: String, projectName: String) {
//        val values = ContentValues().apply {
//            put(COLUMN_NAME, projectName)
//        }
//        db.update(
//            TABLE_NAME,
//            values,
//            "$COLUMN_ID = ?",
//            arrayOf(projectId)
//        )
//    }
//
//    companion object {
//        private const val DATABASE_NAME = "my_notes.db"
//        private const val DATABASE_VERSION = 1
//
//        private const val TABLE_NAME = "projects"
//        private const val COLUMN_ID = "id"
//        private const val COLUMN_NAME = "name"
//
//        private const val CREATE_TABLE_SQL = "CREATE TABLE $TABLE_NAME (" +
//                "$COLUMN_ID TEXT PRIMARY KEY," +
//                "$COLUMN_NAME TEXT" +
//                ")"
//    }
//
//    private class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
//        override fun onCreate(db: SQLiteDatabase) {
//            db.execSQL(CREATE_TABLE_SQL)
//        }
//
//        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
//            db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
//            onCreate(db)
//        }
//    }
//}





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