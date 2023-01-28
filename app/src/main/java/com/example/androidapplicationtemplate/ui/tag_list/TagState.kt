package com.example.androidapplicationtemplate.ui.tag_list

import com.example.androidapplicationtemplate.data.models.response.TagListResponse

sealed class TagState {
    object Idle : TagState()
    object Loading : TagState()
    data class ResponseReceived(val tagListResponse: TagListResponse) : TagState()
    object Error : TagState()
    object State4 : TagState()

}