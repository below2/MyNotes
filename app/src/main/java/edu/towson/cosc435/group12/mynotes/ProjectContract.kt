package edu.towson.cosc435.group12.mynotes

import android.provider.BaseColumns

object ProjectContract {

    class ProjectEntry : BaseColumns {
        companion object {
            const val TABLE_NAME = "projects"
            const val COLUMN_NAME_PROJECT_ID = "project_id"
            const val COLUMN_NAME_PROJECT_NAME = "project_name"
        }
    }
}
