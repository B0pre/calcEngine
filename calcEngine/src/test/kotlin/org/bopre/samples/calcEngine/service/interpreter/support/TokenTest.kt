package org.bopre.samples.calcEngine.service.interpreter.support

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class TokenTest {

    @ParameterizedTest
    @MethodSource("successTokens")
    fun `test token identification`(expr: String, tokenType: TokenType) {
        val expected = Token.of(expr, tokenType)

        val actual = Token.identify(expr)

        if (actual is Token.Companion.TransformResult.Success)
            assertEquals(expected, actual.token, "wrong token identification");
        else
            fail("$expr not mapped to $tokenType")
    }

    @Test
    fun `all tokens are tested`() {
        val allTokens = TokenType.values()
            .toSet()
        val testedTokens = successTokens()
            .map { arg -> arg.get()[1] }
            .toSet()
        assertEquals(allTokens, testedTokens, "not all tokens were tested");
    }

    @ParameterizedTest
    @MethodSource("illegalTokens")
    fun `illegal tokens not identified`(illegalToken: String) {
        val actual = Token.identify(illegalToken)

        if (actual is Token.Companion.TransformResult.NoSuchToken)
            assertEquals(illegalToken, actual.expr, "wrong token identification");
        else
            fail("$illegalToken should not mapped to $actual")
    }

    companion object {
        @JvmStatic
        fun successTokens() = listOf(
            Arguments.of("+", TokenType.PLUS),
            Arguments.of("-", TokenType.MINUS),
            Arguments.of("*", TokenType.MUL),
            Arguments.of("/", TokenType.DIV),
            Arguments.of("(", TokenType.BRACKET_LEFT),
            Arguments.of(")", TokenType.BRACKET_RIGHT),
            Arguments.of("=", TokenType.ASSIGN),
            Arguments.of("241", TokenType.VALUE),
            Arguments.of("241.0", TokenType.VALUE),
            Arguments.of("241.1", TokenType.VALUE),
            Arguments.of("a", TokenType.VARIABLE),
            Arguments.of("long_name", TokenType.VARIABLE)
        )

        @JvmStatic
        fun illegalTokens() = listOf(
            Arguments.of("$#1c2"),
            Arguments.of("(2)")
        )
    }

}