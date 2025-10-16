package com.example.nasabahcompose.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore("pickup_data")

class DataStoreManager(private val context: Context) {

    companion object {
        val JENIS_SAMPAH = stringPreferencesKey("jenis_sampah")
        val BERAT_SAMPAH = stringPreferencesKey("berat_sampah")
    }

    suspend fun savePickupData(jenis: String, berat: String) {
        context.dataStore.edit { prefs ->
            prefs[JENIS_SAMPAH] = jenis
            prefs[BERAT_SAMPAH] = berat
        }
    }

    val pickupData: Flow<Pair<String, String>> = context.dataStore.data.map { prefs ->
        val jenis = prefs[JENIS_SAMPAH] ?: ""
        val berat = prefs[BERAT_SAMPAH] ?: ""
        Pair(jenis, berat)
    }
}
