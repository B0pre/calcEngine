package org.bopre.samples.calcEngine.controllers

import org.bopre.samples.calcEngine.data.dto.InputExpression
import org.bopre.samples.calcEngine.data.dto.OutputResult
import org.bopre.samples.calcEngine.service.CalculationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class CalculationController(@Autowired val calculationService: CalculationService) {

    @GetMapping("/sum")
    fun sumOperation(@RequestParam("a") a: Double, @RequestParam("b") b: Double): OutputResult {
        return calculationService.sumOperation(a, b);
    }

    @GetMapping("/diff")
    fun diffOperation(@RequestParam("a") a: Double, @RequestParam("b") b: Double): OutputResult {
        return calculationService.diffOperation(a, b);
    }

    @GetMapping("/div")
    fun divOperation(@RequestParam("a") a: Double, @RequestParam("b") b: Double): OutputResult {
        return calculationService.divOperation(a, b);
    }

    @GetMapping("/mul")
    fun mulOperation(@RequestParam("a") a: Double, @RequestParam("b") b: Double): OutputResult {
        return calculationService.mulOperation(a, b);
    }

    @PostMapping(value = ["/expr"], produces = ["application/json"])
    fun expressionCal(@RequestBody expr: InputExpression): OutputResult {
        return calculationService.expression(expr)
    }

}