package org.bopre.samples.calcEngine.controllers

import org.bopre.samples.calcEngine.data.dto.InputExpression
import org.bopre.samples.calcEngine.data.dto.OutputResult
import org.bopre.samples.calcEngine.service.CalculationService
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [CalculationController::class])
class CalculationControllerTest(@Autowired val mockMvc: MockMvc) {

    @MockBean
    lateinit var calculationService: CalculationService

    @Test
    fun `expression rest request`() {
        val expectedJson = "{\n" +
                "\"result\": 50\n" +
                "}\n";

        val inputJson = "{\n" +
                "\"expression\": \"a + b\"\n" +
                "}\n"

        val expectedArg = InputExpression("a + b")

        Mockito.`when`(calculationService.expression(expectedArg))
            .thenReturn(OutputResult(50.0))

        mockMvc.perform(
            post("/expr")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(expectedJson))
    }

}