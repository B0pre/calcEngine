package org.bopre.samples.calcEngine.service.interpreter.support.calc

class Mul : BinaryOperation {
    override fun calc(leftOperand: CalcValue, rightOperand: CalcValue): CalcValue {
        return ConstValue(leftOperand.value * rightOperand.value)
    }
}