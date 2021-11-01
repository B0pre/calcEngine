package org.bopre.samples.calcEngine.service.interpreter.support.calc

class ConstValue(override val value: Double) : CalcValue.ImmutableValue {
    override fun toString(): String {
        return "$value"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ConstValue

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }


}