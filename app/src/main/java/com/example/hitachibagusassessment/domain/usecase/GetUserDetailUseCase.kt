package com.example.hitachibagusassessment.domain.usecase

import android.util.Log
import com.example.hitachibagusassessment.domain.model.UserDetail
import com.example.hitachibagusassessment.domain.repository.UserRepository
import javax.inject.Inject

class GetUserDetailUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(username: String): UserDetail {
        Log.d("DETAIL_UC", "UseCase called: $username")
        return repository.getUserDetail(username)
    }
}