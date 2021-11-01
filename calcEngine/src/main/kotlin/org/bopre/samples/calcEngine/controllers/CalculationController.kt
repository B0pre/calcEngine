package org.bopre.samples.calcEngine.controllers

import org.bopre.samples.calcEngine.data.dto.OutputResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class CalculationController {

    @GetMapping("/sum")
    fun sumOperation(@RequestParam("a") a: Double, @RequestParam("b") b: Double): OutputResult {
        val result = a + b;
        return OutputResult(result)
    }

    @GetMapping("/diff")
    fun diffOperation(@RequestParam("a") a: Double, @RequestParam("b") b: Double): OutputResult {
        val result = a - b;
        return OutputResult(result)
    }

}