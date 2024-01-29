package com.jhlee.auth.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/token")
@RestController
class TokenController {

    @GetMapping
    fun validateToken(@RequestParam token: String) {

    }
}