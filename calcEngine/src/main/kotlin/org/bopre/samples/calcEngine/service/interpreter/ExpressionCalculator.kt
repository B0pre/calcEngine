package org.bopre.samples.calcEngine.service.interpreter

interface ExpressionCalculator {

    interface Result {
        data class Success(val value: Double) : Result
        data class Fail(val message: String) : Result
    }

    /**
     * Calculate expression
     * ex.: 2 + 3 * 4
     */
    fun calculate(expr: String): Result;
}