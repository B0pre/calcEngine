package org.bopre.samples.calcEngine.service.interpreter.impl

import org.bopre.samples.calcEngine.service.interpreter.PostfixCreator
import org.bopre.samples.calcEngine.service.interpreter.support.Token
import org.bopre.samples.calcEngine.service.interpreter.support.TokenType
import org.bopre.samples.calcEngine.service.interpreter.support.calc.ConstValue
import org.bopre.samples.calcEngine.service.interpreter.support.calc.Diff
import org.bopre.samples.calcEngine.service.interpreter.support.calc.Mul
import org.bopre.samples.calcEngine.service.interpreter.support.calc.Sum
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PostfixCreatorImplTest {
    lateinit var postfixCreator: PostfixCreator

    @BeforeEach
    fun beforeEach() {
        postfixCreator = PostfixCreatorImpl()
    }

    @Test
    fun `simple translate 2 + 2  ==  2 2 +`() {
        val tokens = listOf(
            Token.of("2", TokenType.VALUE),
            Token.of("+", TokenType.PLUS),
            Token.of("2", TokenType.VALUE)
        )

        val expected = listOf(
            ConstValue(2.0),
            ConstValue(2.0),
            Sum()
        )

        val actual = postfixCreator.createFromTokens(tokens)

        if (actual is PostfixCreator.Result.Success)
            assertEquals(expected, actual.parts, "wrong postfix expression")
        else
            fail("failed create postfix $actual")
    }

    @Test
    fun `complex translate 4 * (2 + 3) - 22 * 2 - 1  ==  4 2 3 + * 22 2 * - 1 -`() {
        val tokens = listOf(
            Token.of("4", TokenType.VALUE),
            Token.of("*", TokenType.MUL),
            Token.of("(", TokenType.BRACKET_LEFT),
            Token.of("2", TokenType.VALUE),
            Token.of("+", TokenType.PLUS),
            Token.of("3", TokenType.VALUE),
            Token.of(")", TokenType.BRACKET_RIGHT),
            Token.of("-", TokenType.MINUS),
            Token.of("22", TokenType.VALUE),
            Token.of("*", TokenType.MUL),
            Token.of("2", TokenType.VALUE),
            Token.of("-", TokenType.MINUS),
            Token.of("1", TokenType.VALUE)
        )

        val expected = listOf(
            ConstValue(4.0),
            ConstValue(2.0),
            ConstValue(3.0),
            Sum(),
            Mul(),
            ConstValue(22.0),
            ConstValue(2.0),
            Mul(),
            Diff(),
            ConstValue(1.0),
            Diff(),
        )

        val actual = postfixCreator.createFromTokens(tokens)

        if (actual is PostfixCreator.Result.Success)
            assertEquals(expected, actual.parts, "wrong postfix expression")
        else
            fail("failed create postfix $actual")
    }

    @Test
    fun `invalid expression ( 1 + 3`() {
        val tokens = listOf(
            Token.of("(", TokenType.BRACKET_LEFT),
            Token.of("1", TokenType.VALUE),
            Token.of("+", TokenType.PLUS),
            Token.of("3", TokenType.VALUE),
        )

        val actual = postfixCreator.createFromTokens(tokens)

        assertTrue(actual is PostfixCreator.Result.Fail, "expected fail but was $actual")
    }

}