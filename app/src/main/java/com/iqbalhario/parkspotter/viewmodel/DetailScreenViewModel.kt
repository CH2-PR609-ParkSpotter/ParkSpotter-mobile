package com.iqbalhario.parkspotter.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.iqbalhario.parkspotter.R
import com.iqbalhario.parkspotter.model.ParkirData
import com.iqbalhario.parkspotter.model.ParkirItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.InputStreamReader

class DetailScreenViewModel(private val context: Context) : ViewModel() {
    private val _parkirDetail = MutableStateFlow<ParkirItem?>(null)
    val parkirDetail: StateFlow<ParkirItem?> = _parkirDetail

    fun loadParkirDetail(parkirId: Int) {
        viewModelScope.launch {
            val data = parseJsonData(context)
            _parkirDetail.emit(data.parkir.find { it.id == parkirId })
        }
    }

    private fun parseJsonData(context: Context): ParkirData {
        val inputStream = context.resources.openRawResource(R.raw.parkir)
        val reader = InputStreamReader(inputStream)
        return Gson().fromJson(reader, ParkirData::class.java)
    }
}
