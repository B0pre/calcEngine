package org.bopre.samples.calcEngine.service.interpreter.impl

import org.bopre.samples.calcEngine.service.interpreter.PostfixCalculator
import org.bopre.samples.calcEngine.service.interpreter.support.calc.BinaryOperation
import org.bopre.samples.calcEngine.service.interpreter.support.calc.CalcPart
import org.bopre.samples.calcEngine.service.interpreter.support.calc.CalcValue
import java.util.*

class PostfixCalculatorImpl : PostfixCalculator {

    override fun calculatePostfix(postfixExpr: List<CalcPart>): PostfixCalculator.CalcResult {
        val valueStack: Deque<CalcValue> = LinkedList()

        val expr = LinkedList(postfixExpr);

        while (!expr.isEmpty()) {
            val calcPart = expr.pop();
            if (calcPart is BinaryOperation) {
                val rightOperand = valueStack.pop();
                val leftOperand = valueStack.pop();
                valueStack.push(calcPart.calc(leftOperand, rightOperand))
                continue;
            }
            if (calcPart is CalcValue) {
                valueStack.push(calcPart)
                continue;
            }
            return PostfixCalculator.CalcResult.Fail("unsupported part: $calcPart")
        }
        val calcResult = valueStack.pop().getValue();
        if (calcResult is CalcValue.CalcResult.Success)
            return PostfixCalculator.CalcResult.Success(calcResult.value)
        return PostfixCalculator.CalcResult.Fail("failed calculate: $calcResult")
    }

}