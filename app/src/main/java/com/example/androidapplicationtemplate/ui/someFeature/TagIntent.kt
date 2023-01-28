package com.example.androidapplicationtemplate.ui.someFeature

sealed class TagIntent {
    object GetTags : TagIntent()
    object Intent2 : TagIntent()
    object Intent3 : TagIntent()
    object Intent4 : TagIntent()
}