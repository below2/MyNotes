package edu.towson.cosc435.group12.mynotes

import android.content.SearchRecentSuggestionsProvider



class suggestedSearch : SearchRecentSuggestionsProvider() {

    init{
        setupSuggestions(AUTHORITY, MODE)
    }

    companion object {
        const val AUTHORITY = "edu.towson.cosc435.group12.mynotes"
        const val MODE: Int = SearchRecentSuggestionsProvider.DATABASE_MODE_QUERIES
    }

}