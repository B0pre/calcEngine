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

    override fun calculate(expr: String): ExpressionCalculator.Result {
        val tokens = lexer.analyse(expr)
        if (tokens !is ExprLexer.LexerResult.Success)
            return ExpressionCalculator.Result.Fail("failed lexical analyse $tokens")

        val parsedPostfix = postfixCreator.createFromTokens(tokens.tokens);
        if (parsedPostfix !is PostfixCreator.Result.Success)
            return ExpressionCalculator.Result.Fail("failed transform to postfix $parsedPostfix")

        val calcResult = postfixCalculator.calculatePostfix(parsedPostfix.parts)
        if (calcResult !is PostfixCalculator.CalcResult.Success)
            return ExpressionCalculator.Result.Fail("failed calculation (postfix) $calcResult")

        return ExpressionCalculator.Result.Success(calcResult.result);
    }

}