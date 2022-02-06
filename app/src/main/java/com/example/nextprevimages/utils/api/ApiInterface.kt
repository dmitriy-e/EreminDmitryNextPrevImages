package com.example.nextprevimages.utils.api

import com.example.nextprevimages.models.RecordPOJO
import retrofit2.http.GET

interface ApiInterface {
    @GET("/random?json=true")
    suspend fun getAllResult(): RecordPOJO
}