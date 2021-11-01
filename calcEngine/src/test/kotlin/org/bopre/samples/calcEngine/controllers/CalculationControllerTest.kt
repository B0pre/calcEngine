package org.bopre.samples.calcEngine.controllers

import org.bopre.samples.calcEngine.data.dto.OutputResult
import org.bopre.samples.calcEngine.service.CalculationService
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [CalculationController::class])
class CalculationControllerTest(@Autowired val mockMvc: MockMvc) {

    @MockBean
    lateinit var calculationService: CalculationService

    @Test
    fun `sum rest request`() {
        val expectedJson = "{\n" +
                "\"result\": 15\n" +
                "}\n";

        Mockito.`when`(calculationService.sumOperation(5.0, 10.0))
            .thenReturn(OutputResult(15.0))

        mockMvc.perform(get("/sum?a=5&b=10"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(expectedJson))
    }

    @Test
    fun `diff rest request`() {
        val expectedJson = "{\n" +
                "\"result\": -5\n" +
                "}\n";

        Mockito.`when`(calculationService.diffOperation(5.0, 10.0))
            .thenReturn(OutputResult(-5.0))

        mockMvc.perform(get("/diff?a=5&b=10"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(expectedJson))
    }

    @Test
    fun `div rest request`() {
        val expectedJson = "{\n" +
                "\"result\": 0.5\n" +
                "}\n";

        Mockito.`when`(calculationService.divOperation(5.0, 10.0))
            .thenReturn(OutputResult(0.5))

        mockMvc.perform(get("/div?a=5&b=10"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(expectedJson))
    }

    @Test
    fun `mul rest request`() {
        val expectedJson = "{\n" +
                "\"result\": 50\n" +
                "}\n";

        Mockito.`when`(calculationService.mulOperation(5.0, 10.0))
            .thenReturn(OutputResult(50.0))

        mockMvc.perform(get("/mul?a=5&b=10"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(expectedJson))
    }

}