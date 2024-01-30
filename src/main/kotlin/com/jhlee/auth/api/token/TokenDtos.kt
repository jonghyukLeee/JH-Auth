package com.jhlee.auth.api.token

data class TokenGenerateRequest(
    val userId: String
) {
    fun toClaim(): Map<String, String> {
        return mapOf("userId" to this.userId)
    }
}

data class TokenRefreshRequest(
    val accessToken: String,
    val refreshToken: String
)