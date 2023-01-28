package com.example.androidapplicationtemplate.domain.usecase

import com.example.androidapplicationtemplate.core.network.Resource
import com.example.androidapplicationtemplate.data.models.response.AlbumListResponse
import com.example.androidapplicationtemplate.data.models.response.Tag
import com.example.androidapplicationtemplate.domain.repository.AlbumRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetTopArtistsByTagUseCase @Inject constructor(
    private val albumRepository: AlbumRepository
) {

    suspend operator fun invoke(tag: Tag): Flow<Resource<AlbumListResponse>> = flow {
        emit(Resource.Loading)
        val result = albumRepository.getAlbumsByTag(tag)
        emit(result)
    }.flowOn(Dispatchers.IO)


}