package com.example.nextprevimages.utils.api

class ApiHelper (private val apiInterface: ApiInterface) {
    suspend fun getAllResult() = apiInterface.getAllResult()
}