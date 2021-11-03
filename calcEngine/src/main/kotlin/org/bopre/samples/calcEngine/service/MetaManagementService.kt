package org.bopre.samples.calcEngine.service

import org.bopre.samples.calcEngine.data.dto.Variable

interface MetaManagementService {
    //TODO: create implementation

    fun removeAllVariables();

    fun getAllVariables(): List<Variable>

}