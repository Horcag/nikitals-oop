package ru.ssau.tk.nikitals.oop.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.ssau.tk.nikitals.oop.application.dto.FunctionOperandsDTO;
import ru.ssau.tk.nikitals.oop.application.dto.PointDTO;
import ru.ssau.tk.nikitals.oop.application.dto.TabulatedFunctionDTO;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.closeTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
public class TabulatedFunctionOperationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private TabulatedFunctionDTO function1;
    private TabulatedFunctionDTO function2;

    @BeforeEach
    void setUp() {
        // Табулированная функция 1
        List<PointDTO> points1 = Arrays.asList(
                PointDTO.builder()
                        .x(1.0)
                        .y(2.0)
                        .build(),
                PointDTO.builder()
                        .x(2.0)
                        .y(4.0)
                        .build(),
                PointDTO.builder()
                        .x(3.0)
                        .y(6.0)
                        .build()
        )
                ;
        function1 = TabulatedFunctionDTO.builder()
                .name("Function1")
                .factoryType("Массив")
                .points(points1)
                .build();

        // Табулированная функция 2
        List<PointDTO> points2 = Arrays.asList(
                PointDTO.builder()
                        .x(1.0)
                        .y(1.0)
                        .build(),
                PointDTO.builder()
                        .x(2.0)
                        .y(2.0)
                        .build(),
                PointDTO.builder()
                        .x(3.0)
                        .y(3.0)
                        .build())
        ;
        function2 = TabulatedFunctionDTO.builder()
                .name("Function2")
                .factoryType("Массив")
                .points(points2)
                .build();
    }

    @Test
    void testAddFunctions() throws Exception {
        FunctionOperandsDTO operands = new FunctionOperandsDTO(function1, function2);

        mockMvc.perform(post("/api/tabulated-functions/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(operands)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.points[0].y", is(3.0)))  // 2.0 + 1.0 = 3.0
                .andExpect(jsonPath("$.points[1].y", is(6.0)))  // 4.0 + 2.0 = 6.0
                .andExpect(jsonPath("$.points[2].y", is(9.0))); // 6.0 + 3.0 = 9.0
    }

    @Test
    void testSubtractFunctions() throws Exception {
        FunctionOperandsDTO operands = new FunctionOperandsDTO(function1, function2);

        mockMvc.perform(post("/api/tabulated-functions/subtract")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(operands)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.points[0].y", is(1.0)))  // 2.0 - 1.0 = 1.0
                .andExpect(jsonPath("$.points[1].y", is(2.0)))  // 4.0 - 2.0 = 2.0
                .andExpect(jsonPath("$.points[2].y", is(3.0))); // 6.0 - 3.0 = 3.0
    }

    @Test
    void testMultiplyFunctions() throws Exception {
        FunctionOperandsDTO operands = new FunctionOperandsDTO(function1, function2);

        mockMvc.perform(post("/api/tabulated-functions/multiply")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(operands)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.points[0].y", is(2.0)))  // 2.0 * 1.0 = 2.0
                .andExpect(jsonPath("$.points[1].y", is(8.0)))  // 4.0 * 2.0 = 8.0
                .andExpect(jsonPath("$.points[2].y", is(18.0))); // 6.0 * 3.0 = 18.0
    }

    @Test
    void testDivideFunctions() throws Exception {
        FunctionOperandsDTO operands = new FunctionOperandsDTO(function1, function2);

        mockMvc.perform(post("/api/tabulated-functions/divide")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(operands)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.points[0].y", is(closeTo(2.0, 0.01))))  // 2.0 / 1.0 = 2.0
                .andExpect(jsonPath("$.points[1].y", is(closeTo(2.0, 0.01))))  // 4.0 / 2.0 = 2.0
                .andExpect(jsonPath("$.points[2].y", is(closeTo(2.0, 0.01)))); // 6.0 / 3.0 = 2.0
    }
}
