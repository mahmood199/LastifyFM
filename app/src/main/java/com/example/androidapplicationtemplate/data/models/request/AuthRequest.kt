package com.example.androidapplicationtemplate.data.models.request

import com.example.androidapplicationtemplate.core.credentials.API_KEY
import com.google.gson.annotations.SerializedName

data class AuthRequest(
    @SerializedName("username")
    val userName: String = "mahmood_2000",
    @SerializedName("password")
    val password: String = "Mahmood@2000",
    @SerializedName("api_key")
    val apiKey: String = API_KEY,
    @SerializedName("api_sig")
    val apiSignature: String,
)
