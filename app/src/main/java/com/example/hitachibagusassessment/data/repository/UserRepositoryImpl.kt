package com.example.hitachibagusassessment.data.repository

import com.example.hitachibagusassessment.data.remote.GitHubApiService
import com.example.hitachibagusassessment.data.remote.dto.toDomain
import com.example.hitachibagusassessment.domain.model.User
import com.example.hitachibagusassessment.domain.model.UserDetail
import com.example.hitachibagusassessment.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: GitHubApiService
) : UserRepository {

    override suspend fun searchUsers(query: String): List<User> {
        return api.searchUsers(query)
            .items
            .map { it.toDomain() }
    }
    override suspend fun getUserDetail(username: String): UserDetail {
        return api.getUserDetail(username).toDomain()
    }
}