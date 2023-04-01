package edu.towson.cosc435.group12.mynotes

sealed class Routes(var route: String) {
    object Notes : Routes("notes")
    object Projects : Routes("projects")
}