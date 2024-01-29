package com.jhlee.auth.service.token.impl

import com.jhlee.auth.config.JwtProperties
import com.jhlee.auth.exception.BaseException
import com.jhlee.auth.exception.ErrorCode
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey

@Component
class JwtTokenManager(
    private val jwtProperties: JwtProperties
) {
    private fun getBaseSecretKey(): SecretKey {
        return Keys.hmacShaKeyFor(jwtProperties.key.toByteArray())
    }
    fun generateAccessToken(claims: Map<String, String>): String {
        val nowDate = Date()
        val expiryDate = Date(nowDate.time + jwtProperties.expiry)

        return Jwts.builder()
            .setIssuedAt(nowDate)
            .setExpiration(expiryDate)
            .setClaims(claims)
            .signWith(getBaseSecretKey())
            .compact()
    }

    fun generateRefreshToken(): String {
        val nowDate = Date()
        val expiryDate = Date(nowDate.time + (jwtProperties.expiry * 24))

        return Jwts.builder()
            .setIssuedAt(nowDate)
            .setExpiration(expiryDate)
            .signWith(getBaseSecretKey())
            .compact()
    }

    fun validate(token: String): Boolean {
        try {
            Jwts.parserBuilder()
                .setSigningKey(getBaseSecretKey())
                .build()
                .parseClaimsJws(token)
            return true
        } catch (e: ExpiredJwtException) {
            throw BaseException(ErrorCode.EXPIRED_TOKEN)
        } catch (e: Exception) {
            throw BaseException(ErrorCode.INVALID_TOKEN)
        }
    }
}