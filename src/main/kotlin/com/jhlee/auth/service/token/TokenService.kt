package com.jhlee.auth.service.token

import com.jhlee.auth.service.token.impl.JwtTokenManager
import org.springframework.http.ResponseCookie
import org.springframework.stereotype.Service
import org.springframework.web.server.ServerWebExchange

@Service
class TokenService(
    private val jwtTokenManager: JwtTokenManager
) {
    fun validate(token: String): Boolean {
        return jwtTokenManager.validate(token)
    }

    fun getToken(exchange: ServerWebExchange, claims: Map<String, String>): String {
        val accessToken = jwtTokenManager.generateAccessToken(claims)
        val refreshToken = jwtTokenManager.generateRefreshToken()

        // TODO JwtTokenManager로 옮길 지 고민해보기
        exchange.response.addCookie(
            ResponseCookie.from("refreshToken", refreshToken).build()
        )

        return accessToken
    }

    fun refresh(accessToken: String) {
        // TODO
    }
}