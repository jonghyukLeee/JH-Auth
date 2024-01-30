package com.jhlee.auth.api.token

import com.jhlee.auth.service.token.TokenService
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@RequestMapping("/api/token")
@RestController
class TokenController(
    private val tokenService: TokenService
) {

    @PostMapping
    fun generate(serverWebExchange: ServerWebExchange, @RequestBody request: TokenGenerateRequest): Mono<String> {
        return Mono.just(tokenService.generate(serverWebExchange, request.toClaim()))
    }

    @PostMapping("/refresh")
    fun refresh(serverWebExchange: ServerWebExchange, @RequestBody request: TokenRefreshRequest): Mono<String> {
        return Mono.just(
            tokenService.refresh(
                serverWebExchange = serverWebExchange,
                accessToken = request.accessToken,
                refreshToken = request.refreshToken
            )
        )
    }
    @GetMapping
    fun validate(@RequestParam token: String): Mono<Boolean> {
        return Mono.just(tokenService.validate(token))
    }
}