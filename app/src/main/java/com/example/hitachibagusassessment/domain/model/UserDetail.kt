package com.example.hitachibagusassessment.domain.model

data class UserDetail(
    val username: String,
    val name: String?,
    val avatarUrl: String,
    val followers: Int,
    val following: Int
)
