package com.example.androidapplicationtemplate.core.di

import com.example.androidapplicationtemplate.domain.repository.TagRepository
import com.example.androidapplicationtemplate.domain.usecase.GetTagsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

	@Provides
	fun provideSomeUseCase(
        tagRepository: TagRepository,
	): GetTagsUseCase = GetTagsUseCase(tagRepository)


}