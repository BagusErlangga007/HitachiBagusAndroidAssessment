package com.example.hitachibagusassessment.domain.model

import com.google.gson.annotations.SerializedName

data class UserDetailResponse(
    @SerializedName("login")
    val login: String,

    @SerializedName("name")
    val name: String?,

    @SerializedName("avatar_url")
    val avatarUrl: String,

    @SerializedName("followers")
    val followers: Int,

    @SerializedName("following")
    val following: Int
)
