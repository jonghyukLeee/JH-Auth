package com.jhlee.auth.service.token

import com.jhlee.auth.service.token.impl.JwtTokenManager
import org.springframework.stereotype.Service
import org.springframework.web.server.ServerWebExchange

@Service
class TokenService(
    private val jwtTokenManager: JwtTokenManager
) {
    fun generate(serverWebExchange: ServerWebExchange, claims: Map<String, String>): String {
        val accessToken = jwtTokenManager.generateAccessToken(claims)
        jwtTokenManager.generateRefreshToken(serverWebExchange)

        return accessToken
    }

    fun refresh(serverWebExchange: ServerWebExchange, accessToken: String, refreshToken: String): String {
        val userId = jwtTokenManager.getClaims(accessToken)["userId"] as String

        val newAccessToken = jwtTokenManager.generateAccessToken(mapOf("userId" to userId))
        jwtTokenManager.generateRefreshToken(serverWebExchange)

        return newAccessToken
    }

    fun validate(token: String): Boolean {
        return jwtTokenManager.validate(token)
    }


}