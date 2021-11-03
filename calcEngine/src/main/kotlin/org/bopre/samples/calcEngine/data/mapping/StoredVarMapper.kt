package org.bopre.samples.calcEngine.data.mapping

import org.bopre.samples.calcEngine.data.dto.Variable
import org.bopre.samples.calcEngine.data.entity.StoredVariable

interface StoredVarMapper {

    /**
     * Map stored variable entity to DTO
     */
    fun mapToDTO(storedVariable: StoredVariable): Variable
}