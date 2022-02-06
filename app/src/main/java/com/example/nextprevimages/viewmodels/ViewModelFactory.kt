package com.example.nextprevimages.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nextprevimages.repositories.RecordRepository
import com.example.nextprevimages.utils.api.ApiHelper

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecordViewModel::class.java)) {
            return RecordViewModel(RecordRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Class not found")
    }
}