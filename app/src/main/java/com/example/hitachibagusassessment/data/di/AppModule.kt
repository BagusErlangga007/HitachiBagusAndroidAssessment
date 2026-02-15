package com.example.hitachibagusassessment.data.di

import com.example.hitachibagusassessment.data.remote.GitHubApiService
import com.example.hitachibagusassessment.data.repository.UserRepositoryImpl
import com.example.hitachibagusassessment.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import kotlin.jvm.java

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): GitHubApiService =
        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GitHubApiService::class.java)

    @Provides
    @Singleton
    fun provideRepository(
        api: GitHubApiService
    ): UserRepository =
        UserRepositoryImpl(api)
}