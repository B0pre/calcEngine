package org.bopre.samples.calcEngine.service.interpreter.impl

import org.bopre.samples.calcEngine.service.interpreter.ExprLexer
import org.bopre.samples.calcEngine.service.interpreter.support.Token
import org.bopre.samples.calcEngine.service.interpreter.support.TokenType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class ExprLexerImplTest {

    lateinit var lexer: ExprLexer

    @BeforeEach
    fun beforeEach() {
        lexer = ExprLexerImpl()
    }

    @Test
    fun `analyse simple sum`() {
        val expr = "5 + 3"
        val expected = listOf(
            Token.of("5", TokenType.VALUE),
            Token.of("+", TokenType.PLUS),
            Token.of("3", TokenType.VALUE)
        )

        val actual = lexer.analyse(expr)

        if (actual is ExprLexer.LexerResult.Success)
            assertEquals(expected, actual.tokens, "wrong token analyse");
        else
            fail("failed analyse $expr: $actual")
    }

    @Test
    fun `analyse complex expression`() {
        val expr = "(2 + 1)*1 - 4/2"
        val expected = listOf(
            Token.of("(", TokenType.BRACKET_LEFT),
            Token.of("2", TokenType.VALUE),
            Token.of("+", TokenType.PLUS),
            Token.of("1", TokenType.VALUE),
            Token.of(")", TokenType.BRACKET_RIGHT),
            Token.of("*", TokenType.MUL),
            Token.of("1", TokenType.VALUE),
            Token.of("-", TokenType.MINUS),
            Token.of("4", TokenType.VALUE),
            Token.of("/", TokenType.DIV),
            Token.of("2", TokenType.VALUE),
        )

        val actual = lexer.analyse(expr)

        if (actual is ExprLexer.LexerResult.Success)
            assertEquals(expected, actual.tokens, "wrong token analyse");
        else
            fail("failed analyse $expr: $actual")
    }

}