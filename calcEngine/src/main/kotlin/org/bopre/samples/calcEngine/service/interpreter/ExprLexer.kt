package org.bopre.samples.calcEngine.service.interpreter

import org.bopre.samples.calcEngine.service.interpreter.support.Token

interface ExprLexer {

    interface LexerResult {
        data class Success(val tokens: List<Token>) : LexerResult
        data class Fail(val message: String) : LexerResult
    }

    fun analyse(expr: String): LexerResult
}