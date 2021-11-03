package org.bopre.samples.calcEngine.service.interpreter.impl

import org.bopre.samples.calcEngine.service.interpreter.ExprLexer
import org.bopre.samples.calcEngine.service.interpreter.ExpressionCalculator
import org.bopre.samples.calcEngine.service.interpreter.PostfixCalculator
import org.bopre.samples.calcEngine.service.interpreter.PostfixCreator
import org.bopre.samples.calcEngine.service.interpreter.support.Token
import org.bopre.samples.calcEngine.service.interpreter.support.calc.CalcPart
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class ExpressionCalculatorImplTest {

    companion object {
        val DOUBLE_DELTA = 0.000001
    }

    lateinit var expressionCalculator: ExpressionCalculator
    lateinit var lexer: ExprLexer
    lateinit var postfixCreator: PostfixCreator
    lateinit var postfixCalculator: PostfixCalculator

    @BeforeEach
    fun beforeEach() {
        lexer = Mockito.mock(ExprLexer::class.java)
        postfixCreator = Mockito.mock(PostfixCreator::class.java)
        postfixCalculator = Mockito.mock(PostfixCalculator::class.java)

        expressionCalculator = ExpressionCalculatorImpl(
            lexer,
            postfixCreator,
            postfixCalculator
        );
    }

    @Test
    fun simpleTest() {
        val expr = "2 + 2 + 2";
        val tokensList: List<Token> = Mockito.mock(List::class.java) as List<Token>;
        val calcs: List<CalcPart> = Mockito.mock(List::class.java) as List<CalcPart>;
        val expected = 6.0;

        Mockito.`when`(lexer.analyse(expr)).thenReturn(ExprLexer.LexerResult.Success(tokensList))
        Mockito.`when`(postfixCreator.createFromTokens(tokensList)).thenReturn(PostfixCreator.Result.Success(calcs))
        Mockito.`when`(postfixCalculator.calculatePostfix(calcs))
            .thenReturn(PostfixCalculator.CalcResult.Success(expected));

        val actual = expressionCalculator.calculate(expr);

        if (actual is ExpressionCalculator.Result.Success)
            assertEquals(expected, actual.value, DOUBLE_DELTA, "wrong result")
        else
            fail("expected success but was $actual")
    }

    @Test
    fun `failed tokens analyse`() {
        val expr = "2 + 2 + 2";
        val tokensList: List<Token> = Mockito.mock(List::class.java) as List<Token>;
        val calcs: List<CalcPart> = Mockito.mock(List::class.java) as List<CalcPart>;
        val expected = 6.0;

        Mockito.`when`(lexer.analyse(Mockito.anyString())).thenReturn(ExprLexer.LexerResult.Fail("fail"))
        Mockito.`when`(postfixCreator.createFromTokens(Mockito.anyList()))
            .thenReturn(PostfixCreator.Result.Success(calcs))
        Mockito.`when`(postfixCalculator.calculatePostfix(Mockito.anyList()))
            .thenReturn(PostfixCalculator.CalcResult.Success(expected));

        val actual = expressionCalculator.calculate(expr);
        assertTrue(actual is ExpressionCalculator.Result.Fail, "expected fail but was")
    }

    @Test
    fun `failed build postfix`() {
        val expr = "2 + 2 + 2";
        val tokensList: List<Token> = Mockito.mock(List::class.java) as List<Token>;
        val calcs: List<CalcPart> = Mockito.mock(List::class.java) as List<CalcPart>;
        val expected = 6.0;

        Mockito.`when`(lexer.analyse(Mockito.anyString())).thenReturn(ExprLexer.LexerResult.Success(tokensList))
        Mockito.`when`(postfixCreator.createFromTokens(Mockito.anyList()))
            .thenReturn(PostfixCreator.Result.Fail("fail"))
        Mockito.`when`(postfixCalculator.calculatePostfix(Mockito.anyList()))
            .thenReturn(PostfixCalculator.CalcResult.Success(expected));

        val actual = expressionCalculator.calculate(expr);
        assertTrue(actual is ExpressionCalculator.Result.Fail, "expected fail but was")
    }

    @Test
    fun `failed calculate postfix`() {
        val expr = "2 + 2 + 2";
        val tokensList: List<Token> = Mockito.mock(List::class.java) as List<Token>;
        val calcs: List<CalcPart> = Mockito.mock(List::class.java) as List<CalcPart>;
        val expected = 6.0;

        Mockito.`when`(lexer.analyse(Mockito.anyString())).thenReturn(ExprLexer.LexerResult.Success(tokensList))
        Mockito.`when`(postfixCreator.createFromTokens(Mockito.anyList()))
            .thenReturn(PostfixCreator.Result.Success(calcs))
        Mockito.`when`(postfixCalculator.calculatePostfix(Mockito.anyList()))
            .thenReturn(PostfixCalculator.CalcResult.Fail("fail"));

        val actual = expressionCalculator.calculate(expr);
        assertTrue(actual is ExpressionCalculator.Result.Fail, "expected fail but was")
    }

}
