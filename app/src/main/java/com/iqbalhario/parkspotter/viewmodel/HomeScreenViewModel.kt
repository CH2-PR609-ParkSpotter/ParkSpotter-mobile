package com.iqbalhario.parkspotter.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.iqbalhario.parkspotter.R
import com.iqbalhario.parkspotter.model.ParkirData
import java.io.InputStreamReader
import androidx.lifecycle.Lifecycle.State
import androidx.lifecycle.viewModelScope
import com.iqbalhario.parkspotter.model.ParkirItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


class HomeScreenViewModel : ViewModel() {
    private val _parkingData = MutableStateFlow<ParkirData?>(null)
    val parkingData: StateFlow<ParkirData?> = _parkingData

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    val filteredParkingData = searchQuery.map { query ->
        _parkingData.value?.parkir?.filter {
            it.name.contains(query, ignoreCase = true)
        } ?: emptyList()
    }

    fun getParkingData(context: Context) {
        viewModelScope.launch {
            val data = parseJsonData(context)
            _parkingData.emit(data)
        }
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    private fun parseJsonData(context: Context): ParkirData {
        val inputStream = context.resources.openRawResource(R.raw.parkir)
        val reader = InputStreamReader(inputStream)
        return Gson().fromJson(reader, ParkirData::class.java)
    }



}