package com.example.androidapplicationtemplate.ui.genreDetails.intent

import android.os.Bundle
import com.example.androidapplicationtemplate.data.models.response.Tag

sealed class TracksIntent {
    object GetTopTracksByTag : TracksIntent()
    data class GetArgs(val arguments: Bundle?) : TracksIntent()
}
