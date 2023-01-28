package com.example.androidapplicationtemplate.core.di

import com.example.androidapplicationtemplate.data.remote.remoteServices.AlbumServices
import com.example.androidapplicationtemplate.data.remote.remoteServices.ArtistServices
import com.example.androidapplicationtemplate.data.remote.remoteServices.TagServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

	@Provides
	fun provideTagService(retrofit: Retrofit): TagServices {
		return retrofit.create(TagServices::class.java)
	}

	@Provides
	fun provideAlbumServices(retrofit: Retrofit): AlbumServices {
		return retrofit.create(AlbumServices::class.java)
	}

	@Provides
	fun provideArtistServices(retrofit: Retrofit): ArtistServices {
		return retrofit.create(ArtistServices::class.java)
	}


}