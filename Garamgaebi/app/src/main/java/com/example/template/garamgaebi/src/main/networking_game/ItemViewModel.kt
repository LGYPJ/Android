package com.example.template.garamgaebi.src.main.networking_game

import android.content.ClipData
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ItemViewModel: ViewModel() {
    private val ice = MutableLiveData<String>()
    val _ice : LiveData<String>
    get() = ice

    fun selectItem(item: String) {
        ice.value = item
    }
}