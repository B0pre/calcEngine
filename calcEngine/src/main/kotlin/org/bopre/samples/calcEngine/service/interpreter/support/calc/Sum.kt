package org.bopre.samples.calcEngine.service.interpreter.support.calc

class Sum : BinaryMathOperation() {

    override fun calcMath(leftOperand: Double, rightOperand: Double): Double = leftOperand + rightOperand

    override fun hashCode(): Int = this::class.hashCode()

    override fun equals(other: Any?): Boolean = if (other == null) false else other::class == this::class;

    override fun toString(): String {
        return " + "
    }

}