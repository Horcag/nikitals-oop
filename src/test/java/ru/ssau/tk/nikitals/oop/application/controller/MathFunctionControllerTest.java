package ru.ssau.tk.nikitals.oop.application.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.ssau.tk.nikitals.oop.core.service.MathFunctionRegistry;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MathFunctionController.class)
@WithMockUser
public class MathFunctionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MathFunctionRegistry registry;

    @Test
    public void testGetAvailableFunctions() throws Exception {
        when(registry.getAvailableFunctions()).thenReturn(Arrays.asList("Function1", "Function2"));

        mockMvc.perform(get("/api/functions/available")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[\"Function1\", \"Function2\"]"));
    }
}