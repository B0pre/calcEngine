package org.bopre.samples.calcEngine.service.interpreter.impl

import org.bopre.samples.calcEngine.service.interpreter.ExprLexer
import org.bopre.samples.calcEngine.service.interpreter.ExpressionCalculator
import org.bopre.samples.calcEngine.service.interpreter.PostfixCalculator
import org.bopre.samples.calcEngine.service.interpreter.PostfixCreator

class ExpressionCalculatorImpl(
    private val lexer: ExprLexer,
    private val postfixCreator: PostfixCreator,
    private val postfixCalculator: PostfixCalculator
) : ExpressionCalculator {

    override fun calculate(expr: String): Double {
        val tokens = lexer.analyse(expr)
        if (tokens !is ExprLexer.LexerResult.Success)
            throw RuntimeException("failed lexical analyse $tokens")

        val parsedPostfix = postfixCreator.createFromTokens(tokens.tokens);
        if (parsedPostfix !is PostfixCreator.Result.Success)
            throw RuntimeException("failed transform to postfix $parsedPostfix")

        val result = postfixCalculator.calculatePostfix(parsedPostfix.parts)
        if (result !is PostfixCalculator.CalcResult.Success)
            throw RuntimeException("failed calculation (postfix) $result")

        return result.result;
    }

}