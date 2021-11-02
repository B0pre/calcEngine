package org.bopre.samples.calcEngine.service.interpreter.impl

import org.bopre.samples.calcEngine.service.interpreter.PostfixCreator
import org.bopre.samples.calcEngine.service.interpreter.support.Token
import org.bopre.samples.calcEngine.service.interpreter.support.TokenType
import org.bopre.samples.calcEngine.service.interpreter.support.calc.*
import java.util.*

class PostfixCreatorImpl : PostfixCreator {
    override fun createFromTokens(originTokens: List<Token>): PostfixCreator.Result {

        val postfix: Deque<CalcPart> = LinkedList();
        val opers: Deque<BinaryOperation> = LinkedList();
        val tokens: Deque<Token> = LinkedList(originTokens);

        val delimiter = object : BinaryOperation {
            override fun calc(leftOperand: CalcValue, rightOperand: CalcValue): CalcValue {
                return object : CalcValue {
                    override fun getValue(): CalcValue.CalcResult {
                        return CalcValue.CalcResult.Fail("not supported calculation for delimiter")
                    }
                }
            }
        }

        while (!tokens.isEmpty()) {
            when (tokens.peek().type) {
                TokenType.BRACKET_LEFT -> {
                    tokens.pop();
                    opers.push(delimiter)
                }
                TokenType.BRACKET_RIGHT -> {
                    tokens.pop()
                    while (!opers.isEmpty() && opers.peek() != delimiter) {
                        postfix.add(opers.pop());
                    }
                    if (!opers.isEmpty() && opers.peek() != delimiter)
                        return PostfixCreator.Result.Fail("illegal expression (check parenthesis)")

                    if (!opers.isEmpty())
                        opers.pop();
                }
                TokenType.MUL,
                TokenType.DIV,
                TokenType.PLUS,
                TokenType.MINUS -> {
                    val operationToken = tokens.peek()
                    val operation: BinaryOperation = buildBinaryOperation(operationToken, delimiter)
                    if (operation == delimiter)
                        return PostfixCreator.Result.Fail("not binary operation: $operationToken")

                    tokens.pop();
                    val priority = getPriority(operation)
                    while (!opers.isEmpty() && priority <= getPriority(opers.peek())) {
                        if (opers.peek() == delimiter)
                            return PostfixCreator.Result.Fail("illegal expression presented")
                        postfix.add(opers.pop());
                    }
                    opers.push(operation)
                }
                TokenType.VALUE -> {
                    val token = tokens.pop();
                    postfix.add(ConstValue(token.value.toDouble()))
                }
            }
        }

        while (!opers.isEmpty()) {
            postfix.add(opers.pop());
        }

        val result: MutableList<CalcPart> = LinkedList();
        for (calc in postfix) {
            if (calc == delimiter)
                return PostfixCreator.Result.Fail("illegal expression presented")
            result.add(calc)
        }
        return PostfixCreator.Result.Success(result)
    }

    private fun getPriority(binaryOperation: BinaryOperation): Int =
        when (binaryOperation::class) {
            Sum::class, Diff::class -> {
                1
            }
            Div::class, Mul::class -> {
                10
            }
            else -> -1;
        }

    private fun buildBinaryOperation(token: Token, default: BinaryOperation): BinaryOperation =
        when (token.type) {
            TokenType.PLUS -> Sum()
            TokenType.MINUS -> Diff()
            TokenType.MUL -> Mul()
            TokenType.DIV -> Div()
            else -> default
        }

}