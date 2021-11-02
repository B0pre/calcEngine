package org.bopre.samples.calcEngine.service.interpreter

interface ExpressionCalculator {

    /**
     * Calculate expression
     * ex.: 2 + 3 * 4
     */
    fun calculate(expr: String): Double;
}