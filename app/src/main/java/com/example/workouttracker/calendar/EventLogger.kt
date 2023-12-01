package com.example.workouttracker.calendar
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.prefs.Preferences
import androidx.datastore.preferences.core.edit

class EventLoggerData (private val dataStore: DataStore<Preferences>){

private object Keys {
    val EVENT = stringPreferencesKey("event")
}
//fun getEvent(dataStore: DataStore<Preferences>): Flow<String> {
//    return dataStore.data.map {preferences ->
//        preferences[Keys.EVENT] ?: "Enter Event"
//    }
//}
//
//    suspend fun setEvent(event: String){
//        dataStore.edit {
//            preferences -> preferences[Keys.EVENT] = event
//        }
//    }
}



