package org.bopre.samples.calcEngine.service

import org.bopre.samples.calcEngine.data.dto.InputExpression
import org.bopre.samples.calcEngine.data.dto.OutputResult
import org.bopre.samples.calcEngine.service.interpreter.ExpressionCalculator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class CalculationServiceImplTest {

    companion object {
        const val DOUBLE_DELTA = 0.00000001;
    }

    lateinit var calculationService: CalculationService
    lateinit var calculatorFactory: ExpressionCalculatorFactory

    @BeforeEach
    fun beforeEach() {
        calculatorFactory = Mockito.mock(ExpressionCalculatorFactory::class.java)
        calculationService = CalculationServiceImpl(calculatorFactory)
    }

    @Test
    fun sumOperation() {
        val a = 5.0
        val b = 10.0
        val expected = 15.0

        val actual = calculationService.sumOperation(a, b)

        assertEquals(expected, actual.result, DOUBLE_DELTA, "wrong result")
    }

    @Test
    fun diffOperation() {
        val a = 5.0
        val b = 10.0
        val expected = -5.0;

        val actual = calculationService.diffOperation(a, b)

        assertEquals(expected, actual.result, DOUBLE_DELTA, "wrong result")
    }

    @Test
    fun divOperation() {
        val a = 5.0
        val b = 10.0
        val expected = 0.5

        val actual = calculationService.divOperation(a, b)

        assertEquals(expected, actual.result, DOUBLE_DELTA, "wrong result")
    }

    @Test
    fun mulOperation() {
        val a = 5.0
        val b = 10.0
        val expected = 50.0

        val actual = calculationService.mulOperation(a, b)

        assertEquals(expected, actual.result, DOUBLE_DELTA, "wrong result")
    }

    @Test
    fun expression() {
        val exprStr = "a + b"
        val expr = InputExpression(exprStr)

        val expected = OutputResult(1234.0)

        val calculator = Mockito.mock(ExpressionCalculator::class.java)
        Mockito.`when`(calculator.calculate(exprStr)).thenReturn(expected.result)
        Mockito.`when`(calculatorFactory.getCalculator()).thenReturn(calculator)

        val actual = calculationService.expression(expr)

        assertEquals(expected, actual, "wrong result")
    }

}