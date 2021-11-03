package org.bopre.samples.calcEngine.service

import org.bopre.samples.calcEngine.data.dto.InputExpression
import org.bopre.samples.calcEngine.data.dto.OutputResult
import org.bopre.samples.calcEngine.service.interpreter.ExpressionCalculator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CalculationServiceImpl(@Autowired private val calculatorFactory: ExpressionCalculatorFactory) :
    CalculationService {

    override fun expression(input: InputExpression): OutputResult =
        with(calculatorFactory.getCalculator().calculate(input.expression))
        {
            if (this is ExpressionCalculator.Result.Success)
                return OutputResult(this.value);
            throw RuntimeException("failed calculate cause: $this")
        }

}