package org.bopre.samples.calcEngine.service.interpreter.impl

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class ExpressionCalculatorImplIntegrationTest {

    companion object {

        const val DOUBLE_DELTA = 0.00001

        @JvmStatic
        fun sources(): List<Arguments> =
            listOf(
                Arguments.of("0", 0.0),
                Arguments.of("2 + 2*4 - (4/2)", 8.0),
                Arguments.of("(1 + 1 + 1) * 2", 6.0),
                Arguments.of("(1 + 2 * 3) * 2", 14.0),
                Arguments.of("(1 * 2 + 3) * 2", 10.0)
            )
    }

    private val expressionCalculatorImpl = ExpressionCalculatorImpl(
        ExprLexerImpl(),
        PostfixCreatorImpl(),
        PostfixCalculatorImpl()
    )

    @ParameterizedTest
    @MethodSource("sources")
    fun `test expression calculation`(expr: String, expected: Double) {
        val actual = expressionCalculatorImpl.calculate(expr)

        assertEquals(expected, actual, DOUBLE_DELTA, "wrong result")
    }

}