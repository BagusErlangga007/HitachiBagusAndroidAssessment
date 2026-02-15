package com.example.hitachibagusassessment.data.remote.dto

import com.example.hitachibagusassessment.domain.model.User
import com.example.hitachibagusassessment.domain.model.UserDetail
import com.example.hitachibagusassessment.domain.model.UserDetailResponse
import com.example.hitachibagusassessment.domain.model.UserDto

fun UserDto.toDomain(): User {
    return User(
        username = login,
        avatarUrl = avatarUrl
    )
}
fun UserDetailResponse.toDomain(): UserDetail {
    return UserDetail(
        username = login,
        name = name,
        avatarUrl = avatarUrl,
        followers = followers,
        following = following
    )
}