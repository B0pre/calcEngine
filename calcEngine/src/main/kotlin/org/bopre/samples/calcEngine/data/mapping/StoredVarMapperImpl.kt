package org.bopre.samples.calcEngine.data.mapping

import org.bopre.samples.calcEngine.data.dto.Variable
import org.bopre.samples.calcEngine.data.entity.StoredVariable
import org.springframework.stereotype.Component

@Component
class StoredVarMapperImpl : StoredVarMapper {
    override fun mapToDTO(storedVariable: StoredVariable): Variable =
        Variable(
            name = storedVariable.name,
            value = storedVariable.condition
        )
}