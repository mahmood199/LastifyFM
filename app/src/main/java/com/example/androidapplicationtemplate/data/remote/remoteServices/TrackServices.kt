package com.example.androidapplicationtemplate.data.remote.remoteServices

import com.example.androidapplicationtemplate.core.credentials.API_KEY
import com.example.androidapplicationtemplate.data.models.response.TrackListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TrackServices {

    @GET("/2.0/?method=tag.gettoptracks&api_key=${API_KEY}&format=json")
    suspend fun getTracksByTag(@Query("tag") tag: String): TrackListResponse

}