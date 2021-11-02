package org.bopre.samples.calcEngine.service.interpreter.impl

import org.bopre.samples.calcEngine.service.interpreter.ExprLexer
import org.bopre.samples.calcEngine.service.interpreter.support.Token
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class ExprLexerImpl : ExprLexer {

    companion object {
        private const val EXPR_REGEX_PARSER = "[-+*/()]|[A-Za-z_][A-Za-z0-9_]*|[0-9]*(\\.?[0-9]+)"
    }

    override fun analyse(expr: String): ExprLexer.LexerResult {
        val tokens: MutableList<Token> = LinkedList()
        val pattern: Pattern = Pattern.compile(EXPR_REGEX_PARSER);
        val matcher: Matcher = pattern.matcher(expr);

        while (matcher.find()) {
            val tokenStr = matcher.group()
            val result = Token.identify(matcher.group())
            if (result is Token.Companion.TransformResult.Success)
                tokens.add(result.token)
            else
                return ExprLexer.LexerResult.Fail("failed identify token $tokenStr")
        }
        return ExprLexer.LexerResult.Success(tokens);
    }

}