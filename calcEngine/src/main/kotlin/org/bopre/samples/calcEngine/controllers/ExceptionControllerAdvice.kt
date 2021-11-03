package org.bopre.samples.calcEngine.controllers

import org.bopre.samples.calcEngine.data.dto.ResponseError
import org.bopre.samples.calcEngine.data.exceptions.CalculationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionControllerAdvice {

    @ExceptionHandler(CalculationException::class)
    fun calculationException(err: CalculationException): ResponseEntity<ResponseError> {
        val error = ResponseError(message = "calculation exception ${err.message}")

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(error)
    }

    @ExceptionHandler(Exception::class)
    fun handleGeneralException(err: Exception): ResponseEntity<ResponseError> {
        val error = ResponseError(message = "undefined server error")

        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(error)
    }

}