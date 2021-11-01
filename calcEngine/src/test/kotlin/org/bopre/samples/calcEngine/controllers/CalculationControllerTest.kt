package org.bopre.samples.calcEngine.controllers

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [CalculationController::class])
class CalculationControllerTest(@Autowired val mockMvc: MockMvc) {

    @Test
    fun `sum rest request`() {
        val expectedJson = "{\n" +
                "\"result\": 15\n" +
                "}\n";
        mockMvc.perform(get("/sum?a=5&b=10"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(expectedJson))
    }

}