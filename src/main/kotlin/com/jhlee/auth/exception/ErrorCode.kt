package com.jhlee.auth.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(val httpStatus:HttpStatus? = HttpStatus.BAD_REQUEST, val message: String) {
    INVALID_TOKEN(message = "Invalid token"),
    EXPIRED_TOKEN(message = "Expired token")
}