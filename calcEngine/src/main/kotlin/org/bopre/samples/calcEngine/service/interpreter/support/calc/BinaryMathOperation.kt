package org.bopre.samples.calcEngine.service.interpreter.support.calc

abstract class BinaryMathOperation : BinaryOperation {
    final override fun calc(leftOperand: CalcValue, rightOperand: CalcValue): CalcValue {
        val leftValue = leftOperand.getValue();
        val rightValue = rightOperand.getValue();
        if (leftValue !is CalcValue.CalcResult.Success)
            return errorValue("left operand of $this failed: $leftValue")
        if (rightValue !is CalcValue.CalcResult.Success)
            return errorValue("right operand of $this failed: $rightValue")

        val operationResult = calcMath(leftValue.value, rightValue.value);
        return ConstValue(operationResult);
    }

    private fun errorValue(message: String): CalcValue {
        return object : CalcValue {
            override fun getValue(): CalcValue.CalcResult {
                return CalcValue.CalcResult.Fail(message)
            }
        }
    }

    protected abstract fun calcMath(leftOperand: Double, rightOperand: Double): Double;
}