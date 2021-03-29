package com.example.jetpackdatastore.dataStore

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey


class UserPreferences (
 context: Context
)
    {
    private val applicationContext = context.applicationContext
    private val dataStore: DataStore<Preferences>

        init {
        dataStore = applicationContext.createDataStore(
            name = "app_preferences"
        )
    }
        val bookmark: Flow<String?>
        get() = dataStore.data.map { preferences -> preferences[KEY_BOOKMARK] }

        suspend fun saveBookmark(bookmark: String) {
            dataStore.edit { preferences ->
                preferences[KEY_BOOKMARK] = bookmark
            }
        }

        companion object {
            val KEY_BOOKMARK = preferencesKey<String>("key_bookmark")
        }
    }
