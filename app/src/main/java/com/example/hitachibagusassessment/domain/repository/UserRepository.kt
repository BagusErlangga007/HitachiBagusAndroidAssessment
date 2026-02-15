package com.example.hitachibagusassessment.domain.repository

import com.example.hitachibagusassessment.domain.model.User
import com.example.hitachibagusassessment.domain.model.UserDetail
import com.example.hitachibagusassessment.data.remote.GitHubApiService

interface UserRepository {
    suspend fun searchUsers(query: String): List<User>
    suspend fun getUserDetail(username: String): UserDetail
}