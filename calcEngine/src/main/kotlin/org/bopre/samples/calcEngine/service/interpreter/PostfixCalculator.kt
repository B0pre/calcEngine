package org.bopre.samples.calcEngine.service.interpreter

import org.bopre.samples.calcEngine.service.interpreter.support.calc.CalcPart

interface PostfixCalculator {

    interface CalcResult {
        data class Success(val result: Double) : CalcResult
        data class Fail(val message: String) : CalcResult
    }

    fun calculatePostfix(postfixExpr: List<CalcPart>): CalcResult;
}