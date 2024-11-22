package dev.sunnat629.codeshrinking

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val name: String?,
    val email: String?,
    @SerialName("avatar_url") val profilePic: String,
    @SerialName("public_repos") val publicRepos: Int,
    @SerialName("public_gists") val publicGists: Int,
    val followers: Int,
    val following: Int
)