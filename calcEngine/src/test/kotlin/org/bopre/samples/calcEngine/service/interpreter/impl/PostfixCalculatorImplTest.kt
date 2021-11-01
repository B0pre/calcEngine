package org.bopre.samples.calcEngine.service.interpreter.impl

import org.bopre.samples.calcEngine.service.interpreter.PostfixCalculator
import org.bopre.samples.calcEngine.service.interpreter.support.calc.CalcPart
import org.bopre.samples.calcEngine.service.interpreter.support.calc.ConstValue
import org.bopre.samples.calcEngine.service.interpreter.support.calc.Mul
import org.bopre.samples.calcEngine.service.interpreter.support.calc.Sum
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class PostfixCalculatorImplTest {

    lateinit var postfixCalculator: PostfixCalculator

    @BeforeEach
    fun beforeEach() {
        postfixCalculator = PostfixCalculatorImpl()
    }

    @Test
    fun `calculate postfix 23+45+* == 45`() {
        val postfixExpr = listOf(
            ConstValue(2.0),
            ConstValue(3.0),
            Sum(),
            ConstValue(4.0),
            ConstValue(5.0),
            Sum(),
            Mul()
        )
        val expected = 45.0

        val actual = postfixCalculator.calculatePostfix(postfixExpr)

        if (actual is PostfixCalculator.CalcResult.Success)
            assertEquals(expected, actual.result, "wrong calculation")
        else
            fail("failed calculation: $actual")
    }

    class UnsupportedCalc : CalcPart

    @Test
    fun `calculate postfix with illegal operation`() {
        //23+45+* == 45
        val postfixExpr = listOf(
            ConstValue(2.0),
            ConstValue(3.0),
            UnsupportedCalc(),
            Sum(),
            ConstValue(4.0),
            ConstValue(5.0),
            Sum(),
            Mul()
        )

        val actual = postfixCalculator.calculatePostfix(postfixExpr);

        assertTrue(actual is PostfixCalculator.CalcResult.Fail, "expected fail but was $actual")
    }

}