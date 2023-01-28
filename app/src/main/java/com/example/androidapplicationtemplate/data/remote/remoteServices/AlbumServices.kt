package com.example.androidapplicationtemplate.data.remote.remoteServices

import com.example.androidapplicationtemplate.core.credentials.API_KEY
import com.example.androidapplicationtemplate.data.models.response.AlbumListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface AlbumServices {

    @GET("/2.0/?method=tag.gettopalbums&api_key=${API_KEY}&format=json")
    suspend fun getAlbums(@Query("tag") tag: String): AlbumListResponse

}