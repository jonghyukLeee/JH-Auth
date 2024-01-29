package com.jhlee.auth.exception

import org.springframework.http.HttpStatus

class BaseException(
    val httpStatus: HttpStatus,
    override val message: String
): RuntimeException() {
    constructor(errorCode: ErrorCode): this(
        httpStatus = errorCode.httpStatus!!,
        message = errorCode.message
    )
}