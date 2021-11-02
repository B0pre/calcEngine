package org.bopre.samples.calcEngine.service.interpreter.support

import java.util.regex.Pattern

data class Token(val value: String, val type: TokenType) {

    companion object {
        fun of(value: String, type: TokenType): Token {
            return Token(value, type);
        }

        interface TransformResult {
            data class Success(val token: Token) : TransformResult
            data class NoSuchToken(val expr: String) : TransformResult
        }

        fun identify(expr: String): TransformResult {
            for (tokenType in TokenType.values()) {
                val pattern: Pattern = Pattern.compile(tokenType.regex)
                if (pattern.matcher(expr).matches())
                    return TransformResult.Success(of(expr, tokenType));
            }
            return TransformResult.NoSuchToken(expr);
        }
    }

}
