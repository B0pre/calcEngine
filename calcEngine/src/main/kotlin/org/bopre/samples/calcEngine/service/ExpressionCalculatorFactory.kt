package org.bopre.samples.calcEngine.service

import org.bopre.samples.calcEngine.service.interpreter.ExpressionCalculator

interface ExpressionCalculatorFactory {
    fun getCalculator(): ExpressionCalculator
}