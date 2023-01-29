package com.example.androidapplicationtemplate.data.remote.remoteServices

import com.example.androidapplicationtemplate.core.credentials.API_KEY
import com.example.androidapplicationtemplate.data.models.response.Tag
import com.example.androidapplicationtemplate.data.models.response.TagListResponse
import com.example.androidapplicationtemplate.data.models.response.TagResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TagServices {

    @GET("/2.0/?method=tag.gettoptags&api_key=${API_KEY}&format=json")
    suspend fun getAllTags(): TagListResponse

    @GET("/2.0/?method=tag.getinfo&api_key=${API_KEY}&format=json")
    suspend fun getTagInfo(@Query("tag") tag: String) : TagResponse

}