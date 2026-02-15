package com.example.hitachibagusassessment.data.remote

import com.example.hitachibagusassessment.domain.model.SearchResponse
import com.example.hitachibagusassessment.domain.model.UserDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface GitHubApiService {

    @GET("search/users")
    suspend fun searchUsers(
        @Query("q") query: String
    ): SearchResponse

    @GET("users/{username}")
    suspend fun getUserDetail(
        @Path("username") username: String
    ): UserDetailResponse
}