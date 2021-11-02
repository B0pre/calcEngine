package org.bopre.samples.calcEngine.controllers

import org.bopre.samples.calcEngine.data.dto.InputExpression
import org.bopre.samples.calcEngine.data.dto.OutputResult
import org.bopre.samples.calcEngine.service.CalculationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class CalculationController(@Autowired val calculationService: CalculationService) : CalculateExpressionsApi {

    @GetMapping("/sum")
    @Deprecated(message = "to be removed", replaceWith = ReplaceWith("nothing"))
    fun sumOperation(@RequestParam("a") a: Double, @RequestParam("b") b: Double): OutputResult {
        return calculationService.sumOperation(a, b);
    }

    @GetMapping("/diff")
    @Deprecated(message = "to be removed", replaceWith = ReplaceWith("nothing"))
    fun diffOperation(@RequestParam("a") a: Double, @RequestParam("b") b: Double): OutputResult {
        return calculationService.diffOperation(a, b);
    }

    @GetMapping("/div")
    @Deprecated(message = "to be removed", replaceWith = ReplaceWith("nothing"))
    fun divOperation(@RequestParam("a") a: Double, @RequestParam("b") b: Double): OutputResult {
        return calculationService.divOperation(a, b);
    }

    @GetMapping("/mul")
    @Deprecated(message = "to be removed", replaceWith = ReplaceWith("nothing"))
    fun mulOperation(@RequestParam("a") a: Double, @RequestParam("b") b: Double): OutputResult {
        return calculationService.mulOperation(a, b);
    }

    override fun expressionCalc(inputExpression: InputExpression?): ResponseEntity<OutputResult> {
        return ResponseEntity.ok(calculationService.expression(inputExpression!!))
    }

}