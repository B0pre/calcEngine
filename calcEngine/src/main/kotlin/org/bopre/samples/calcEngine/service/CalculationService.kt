package org.bopre.samples.calcEngine.service

import org.bopre.samples.calcEngine.data.dto.InputExpression
import org.bopre.samples.calcEngine.data.dto.OutputResult

interface CalculationService {

    /**
     * a + b
     */
    fun sumOperation(a: Double, b: Double): OutputResult;

    /**
     * a - b
     */
    fun diffOperation(a: Double, b: Double): OutputResult;

    /**
     * a / b
     */
    fun divOperation(a: Double, b: Double): OutputResult;

    /**
     * a * b
     */
    fun mulOperation(a: Double, b: Double): OutputResult;

    /**
     * calculates expression
     */
    fun expression(input: InputExpression): OutputResult;

}