package com.example.androidapplicationtemplate.core.di

import com.example.androidapplicationtemplate.data.remote.remoteServices.TagServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

	@Provides
	fun provideTagService(retrofit: Retrofit): TagServices {
		return retrofit.create(TagServices::class.java)
	}

}