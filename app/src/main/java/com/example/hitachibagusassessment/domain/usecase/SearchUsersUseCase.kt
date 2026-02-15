package com.example.hitachibagusassessment.domain.usecase

import com.example.hitachibagusassessment.domain.repository.UserRepository
import javax.inject.Inject

class SearchUsersUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(query: String) =
        repository.searchUsers(query)
}
