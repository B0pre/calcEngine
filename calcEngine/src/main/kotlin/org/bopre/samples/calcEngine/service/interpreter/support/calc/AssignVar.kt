package org.bopre.samples.calcEngine.service.interpreter.support.calc

class AssignVar : BinaryOperation {
    override fun calc(leftOperand: CalcValue, rightOperand: CalcValue): CalcValue {
        if (leftOperand !is CalcValue.MutableValue)
            return object : CalcValue {
                override fun getValue(): CalcValue.CalcResult {
                    return CalcValue.CalcResult.Fail("illegal assign to rValue ($leftOperand=$rightOperand)")
                }
            }

        return object : CalcValue.MutableValue {
            var calculed = false;
            lateinit var assignedBy: CalcValue.CalcResult
            override fun setValue(value: Double) = leftOperand.setValue(value)

            override fun getValue(): CalcValue.CalcResult {
                if (!calculed) {
                    val calcResult = rightOperand.getValue()
                    if (calcResult !is CalcValue.CalcResult.Success)
                        return CalcValue.CalcResult.Fail("failed calculate assign by: $calcResult")
                    leftOperand.setValue(calcResult.value)
                    assignedBy = calcResult
                }
                calculed = true;
                return assignedBy
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

    override fun toString(): String {
        return " = "
    }

}