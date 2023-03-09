package com.garamgaebi.garamgaebi.common

import android.content.Context
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

class DataStoreModule(private val context: Context) {
    private val Context.dataStore by preferencesDataStore(name = "data_store")

    private val stringKey = stringPreferencesKey("key_name") // String 타입 저장 키값
    private val intKey = intPreferencesKey("key_name") // Int 타입 저장 키값
}