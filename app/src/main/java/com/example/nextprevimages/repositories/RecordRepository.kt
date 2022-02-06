package com.example.nextprevimages.repositories

import com.example.nextprevimages.utils.api.ApiHelper

class RecordRepository(private val apiHelper: ApiHelper) {
    suspend fun getRecord() = apiHelper.getAllResult()
}