package org.bopre.samples.calcEngine.controllers

import org.bopre.samples.calcEngine.data.dto.InputExpression
import org.bopre.samples.calcEngine.data.dto.OutputResult
import org.bopre.samples.calcEngine.service.CalculationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class CalculationController(@Autowired val calculationService: CalculationService) : CalculateExpressionsApi {

    override fun expressionCalc(inputExpression: InputExpression?): ResponseEntity<OutputResult> {
        return ResponseEntity.ok(calculationService.expression(inputExpression!!))
    }

}