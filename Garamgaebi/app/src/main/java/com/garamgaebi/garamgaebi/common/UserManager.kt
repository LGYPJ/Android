package com.garamgaebi.garamgaebi.common

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserManager(
    private val dataStore: DataStore<Preferences>
) {
    companion object {
        val USER_AGE_KEY = intPreferencesKey("USER_AGE")
        val USER_FIRST_NAME_KEY = stringPreferencesKey("USER_FIRST_NAME")
        val USER_LAST_NAME_KEY = stringPreferencesKey("USER_LAST_NAME")
        val USER_GENDER_KEY = booleanPreferencesKey("USER_GENDER")
    }

    suspend fun storeUser(
        age: Int,
        frontName: String,
        lastName: String,
        isMale: Boolean
    ) {
        dataStore.edit {
            it[USER_AGE_KEY] = age
            it[USER_FIRST_NAME_KEY] = frontName
            it[USER_LAST_NAME_KEY] = lastName
            it[USER_GENDER_KEY] = isMale
        }
    }

    val userAgeFlow: Flow<Int?> = dataStore.data.map {
        it[USER_AGE_KEY]
    }

    val userFirstNameFlow: Flow<String?> = dataStore.data.map {
        it[USER_FIRST_NAME_KEY]
    }

    val userLastNameFlow: Flow<String?> = dataStore.data.map {
        it[USER_LAST_NAME_KEY]
    }

    val userGenderFlow: Flow<Boolean?> = dataStore.data.map {
        it[USER_GENDER_KEY]
    }

}