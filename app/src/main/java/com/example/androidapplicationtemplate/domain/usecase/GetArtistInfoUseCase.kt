package com.example.androidapplicationtemplate.domain.usecase

import com.example.androidapplicationtemplate.core.network.Resource
import com.example.androidapplicationtemplate.data.models.response.*
import com.example.androidapplicationtemplate.domain.repository.ArtistRepository
import com.example.androidapplicationtemplate.domain.repository.TagRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetArtistInfoUseCase @Inject constructor(
    private val artistRepository: ArtistRepository
) {

    suspend operator fun invoke(artist: Artist): Flow<Resource<ArtistDetailResponse>> = flow {
        emit(Resource.Loading)
        val result = artistRepository.getArtistDetails(artist)
        emit(result)
    }.flowOn(Dispatchers.IO)


}