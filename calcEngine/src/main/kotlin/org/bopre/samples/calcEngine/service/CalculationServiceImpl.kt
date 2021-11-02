package org.bopre.samples.calcEngine.service

import org.bopre.samples.calcEngine.data.dto.InputExpression
import org.bopre.samples.calcEngine.data.dto.OutputResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CalculationServiceImpl(@Autowired private val calculatorFactory: ExpressionCalculatorFactory) :
    CalculationService {

    override fun sumOperation(a: Double, b: Double): OutputResult = OutputResult(a + b)

    override fun diffOperation(a: Double, b: Double): OutputResult = OutputResult(a - b)

    override fun divOperation(a: Double, b: Double): OutputResult = OutputResult(a / b)

    override fun mulOperation(a: Double, b: Double): OutputResult = OutputResult(a * b)

    override fun expression(input: InputExpression): OutputResult {
        return OutputResult(calculatorFactory.getCalculator().calculate(input.expr))
    }

}