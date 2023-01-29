package com.example.androidapplicationtemplate.domain.usecase

import com.example.androidapplicationtemplate.core.network.Resource
import com.example.androidapplicationtemplate.data.models.response.Tag
import com.example.androidapplicationtemplate.data.models.response.TrackListResponse
import com.example.androidapplicationtemplate.domain.repository.TrackRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetTopTracksByTagUseCase @Inject constructor(
    private val trackRepository: TrackRepository
) {

    suspend operator fun invoke(tag: Tag): Flow<Resource<TrackListResponse>> = flow {
        emit(Resource.Loading)
        val result = trackRepository.getTrackByTag(tag)
        emit(result)
    }.flowOn(Dispatchers.IO)


}