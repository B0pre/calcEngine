package org.bopre.samples.calcEngine.service

import org.bopre.samples.calcEngine.data.dto.InputExpression
import org.bopre.samples.calcEngine.data.dto.OutputResult

interface CalculationService {

    /**
     * calculates expression
     */
    fun expression(input: InputExpression): OutputResult;

}