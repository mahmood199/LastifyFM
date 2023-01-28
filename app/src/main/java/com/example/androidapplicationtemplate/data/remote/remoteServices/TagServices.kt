package com.example.androidapplicationtemplate.data.remote.remoteServices

import com.example.androidapplicationtemplate.core.credentials.API_KEY
import com.example.androidapplicationtemplate.data.models.response.Response
import com.example.androidapplicationtemplate.data.models.response.TagListResponse
import retrofit2.http.GET

interface TagServices {

    @GET("/2.0/?method=tag.gettoptags&api_key=${API_KEY}&format=json")
    suspend fun getTags(): TagListResponse

}