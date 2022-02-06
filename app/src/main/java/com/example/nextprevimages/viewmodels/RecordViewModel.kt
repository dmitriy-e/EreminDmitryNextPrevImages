package com.example.nextprevimages.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.nextprevimages.models.Record
import com.example.nextprevimages.models.Resource
import com.example.nextprevimages.repositories.RecordRepository

class RecordViewModel(private val mRecordRepository: RecordRepository) : ViewModel() {
    var listImg = mutableListOf<Record>()
    private var index = 0

    fun getCurrentIndex(): Int {
        return index
    }

    fun incIndex(): Int {
        return ++index
    }

    fun decIndex(): Int {
        return --index
    }

    fun addNewRecord(el: Record) {
        listImg.add(el)
        index = listImg.lastIndex
    }

    fun getAllRecord() = liveData {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(mRecordRepository.getRecord()))
        } catch (e: Exception) {
            emit(Resource.error(null, e.message.toString()))
        }
    }
}