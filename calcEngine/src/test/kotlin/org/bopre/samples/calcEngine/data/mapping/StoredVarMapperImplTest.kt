package org.bopre.samples.calcEngine.data.mapping

import org.bopre.samples.calcEngine.data.dto.Variable
import org.bopre.samples.calcEngine.data.entity.StoredVariable
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*

class StoredVarMapperImplTest {

    lateinit var mapper: StoredVarMapper

    @BeforeEach
    fun beforeEach() {
        mapper = StoredVarMapperImpl()
    }

    @Test
    fun mapToDTO() {
        val expected = Variable(
            name = "a",
            value = 0.5
        )

        val storedVariable = StoredVariable(
            id = UUID.fromString("54ea86c9-a0ef-4d31-852d-ae8ac3e0accb"),
            name = "a",
            condition = 0.5
        )

        val actual = mapper.mapToDTO(storedVariable)

        assertEquals(expected, actual, "wrong mapping")
    }

}