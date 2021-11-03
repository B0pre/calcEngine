package org.bopre.samples.calcEngine.controllers

import org.bopre.samples.calcEngine.data.dto.Variable
import org.bopre.samples.calcEngine.service.MetaManagementService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class MetaManagementController(
    @Autowired
    private val managementService: MetaManagementService
) : MetaManagementApi {

    override fun cleanVariables(): ResponseEntity<Unit> {
        managementService.removeAllVariables()
        return ResponseEntity.ok(Unit)
    }

    override fun listVariables(): ResponseEntity<List<Variable>> {
        val variables = managementService.getAllVariables();
        return ResponseEntity.ok(variables)
    }

}