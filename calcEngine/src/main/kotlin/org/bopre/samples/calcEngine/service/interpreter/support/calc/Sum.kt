package org.bopre.samples.calcEngine.service.interpreter.support.calc

class Sum : BinaryOperation {
    override fun calc(leftOperand: CalcValue, rightOperand: CalcValue): CalcValue {
        return ConstValue(leftOperand.value + rightOperand.value)
    }
}