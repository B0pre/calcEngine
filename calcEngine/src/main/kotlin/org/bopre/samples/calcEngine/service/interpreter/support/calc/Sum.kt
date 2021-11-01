package org.bopre.samples.calcEngine.service.interpreter.support.calc

class Sum : BinaryOperation {
    override fun calc(leftOperand: CalcValue, rightOperand: CalcValue): CalcValue {
        return ConstValue(leftOperand.value + rightOperand.value)
    }

    override fun hashCode(): Int = this::class.hashCode()

    override fun equals(other: Any?): Boolean = if (other == null) false else other::class == this::class;

    override fun toString(): String {
        return " + "
    }

}