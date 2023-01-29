package com.example.androidapplicationtemplate.domain.usecase

import com.example.androidapplicationtemplate.core.network.Resource
import com.example.androidapplicationtemplate.data.models.response.AlbumListResponse
import com.example.androidapplicationtemplate.data.models.response.Tag
import com.example.androidapplicationtemplate.domain.repository.TagRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetTagInfoUseCase @Inject constructor(
    private val tagRepository: TagRepository
) {

    suspend operator fun invoke(tag: String): Flow<Resource<Tag>> = flow {
        emit(Resource.Loading)
        val result = tagRepository.getTagInfo(tag)
        emit(result)
    }.flowOn(Dispatchers.IO)


}