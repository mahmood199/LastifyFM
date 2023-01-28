package com.example.androidapplicationtemplate.ui.tag_list

import com.example.androidapplicationtemplate.data.models.response.Tag

sealed class TagState {
    object Idle : TagState()
    object Loading : TagState()
    data class ResponseReceived(val tags: List<Tag>) : TagState()
    data class ShowMoreTags(val tags: MutableList<Tag>) : TagState()
    object Error : TagState()
}