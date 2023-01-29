package com.example.androidapplicationtemplate.domain.usecase

import com.example.androidapplicationtemplate.core.network.Resource
import com.example.androidapplicationtemplate.data.models.response.AlbumListResponse
import com.example.androidapplicationtemplate.data.models.response.ArtistListResponse
import com.example.androidapplicationtemplate.data.models.response.Tag
import com.example.androidapplicationtemplate.domain.repository.ArtistRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetTopArtistsByTagUseCase @Inject constructor(
    private val artistRepository: ArtistRepository
) {

    suspend operator fun invoke(tag: Tag): Flow<Resource<ArtistListResponse>> = flow {
        emit(Resource.Loading)
        val result = artistRepository.getArtistByTag(tag)
        emit(result)
    }.flowOn(Dispatchers.IO)


}