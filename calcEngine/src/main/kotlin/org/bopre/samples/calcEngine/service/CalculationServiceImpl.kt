package org.bopre.samples.calcEngine.service

import org.bopre.samples.calcEngine.data.dto.OutputResult
import org.springframework.stereotype.Component

@Component
class CalculationServiceImpl : CalculationService {

    override fun sumOperation(a: Double, b: Double): OutputResult = OutputResult(a + b)

    override fun diffOperation(a: Double, b: Double): OutputResult = OutputResult(a - b)

    override fun divOperation(a: Double, b: Double): OutputResult = OutputResult(a / b)

    override fun mulOperation(a: Double, b: Double): OutputResult = OutputResult(a * b)

}