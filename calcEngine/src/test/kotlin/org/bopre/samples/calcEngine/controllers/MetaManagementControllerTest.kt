package org.bopre.samples.calcEngine.controllers

import org.bopre.samples.calcEngine.data.dto.Variable
import org.bopre.samples.calcEngine.service.MetaManagementService
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [MetaManagementController::class])
internal class MetaManagementControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc;

    @MockBean
    lateinit var managementService: MetaManagementService

    @Test
    fun cleanVariables() {
        mockMvc.perform(delete("/vars/clean"))
            .andExpect(status().isOk)

        Mockito.verify(
            managementService, Mockito.times(1)
                .description("expected remove variables method to be invoked")
        )
            .removeAllVariables()
    }

    @Test
    fun listVariables() {

        val expectedJson = "[\n" +
                "  {\n" +
                "      \"name\":\"a\",\n" +
                "      \"value\": 1.0\n" +
                "  },\n" +
                "  {\n" +
                "      \"name\":\"b\",\n" +
                "      \"value\": 0.5\n" +
                "  },\n" +
                "  {\n" +
                "      \"name\":\"c\",\n" +
                "      \"value\": 255.0\n" +
                "  },\n" +
                "  {\n" +
                "      \"name\":\"d\",\n" +
                "      \"value\": -1.0\n" +
                "  },\n" +
                "  {\n" +
                "      \"name\":\"e\",\n" +
                "      \"value\": 0.0\n" +
                "  }\n" +
                "]\n"

        Mockito.`when`(managementService.getAllVariables())
            .thenReturn(
                listOf(
                    Variable(name = "a", value = 1.0),
                    Variable(name = "b", value = 0.5),
                    Variable(name = "c", value = 255.0),
                    Variable(name = "d", value = -1.0),
                    Variable(name = "e", value = 0.0)
                )
            )

        mockMvc.perform(get("/vars"))
            .andExpect(status().isOk)
            .andExpect(content().json(expectedJson))
    }

}