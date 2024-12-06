package ru.ssau.tk.nikitals.oop.application.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.ssau.tk.nikitals.oop.application.dto.TabulatedFunctionDTO;
import ru.ssau.tk.nikitals.oop.application.mapper.TabulatedFunctionMapper;
import ru.ssau.tk.nikitals.oop.core.models.TabulatedFunctionEntity;
import ru.ssau.tk.nikitals.oop.core.service.TabulatedFunctionService;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TabulatedFunctionController.class)
@WithMockUser
public class TabulatedFunctionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TabulatedFunctionService service;

    @MockBean
    private TabulatedFunctionMapper mapper;

    @Test
    public void testSaveFunction() throws Exception {
        TabulatedFunctionDTO dto = new TabulatedFunctionDTO();
        TabulatedFunctionEntity entity = new TabulatedFunctionEntity();

        when(mapper.toEntity(dto)).thenReturn(entity);
        when(service.save(entity)).thenReturn(entity);
        when(mapper.toDto(entity)).thenReturn(dto);

        mockMvc.perform(post("/api/tabulated-functions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(content().json("{}"));
    }

    @Test
    public void testLoadFunction() throws Exception {
        TabulatedFunctionDTO dto = new TabulatedFunctionDTO();
        TabulatedFunctionEntity entity = new TabulatedFunctionEntity();

        when(service.getTabulatedFunction(1L)).thenReturn(entity);
        when(mapper.toDto(entity)).thenReturn(dto);

        mockMvc.perform(get("/api/tabulated-functions/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}"));
    }

    @Test
    public void testGetAllFunctions() throws Exception {
        TabulatedFunctionDTO dto = new TabulatedFunctionDTO();
        TabulatedFunctionEntity entity = new TabulatedFunctionEntity();

        when(service.getAllFunctions()).thenReturn(Collections.singletonList(entity));
        when(mapper.toDto(entity)).thenReturn(dto);

        mockMvc.perform(get("/api/tabulated-functions")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{}]"));
    }

    @Test
    public void testUpdateFunction() throws Exception {
        TabulatedFunctionDTO dto = new TabulatedFunctionDTO();
        TabulatedFunctionEntity entity = new TabulatedFunctionEntity();

        when(mapper.toEntity(dto)).thenReturn(entity);
        when(service.save(entity)).thenReturn(entity);
        when(mapper.toDto(entity)).thenReturn(dto);

        mockMvc.perform(put("/api/tabulated-functions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(content().json("{}"));
    }

    @Test
    public void testDeleteFunction() throws Exception {
        mockMvc.perform(delete("/api/tabulated-functions/1")
                        .with(csrf()))
                .andExpect(status().isNoContent());
    }


    @Test
    public void testSearchFunctions() throws Exception {
        when(service.search(Mockito.any())).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/tabulated-functions/search")
                        .param("name", "")
                        .param("type", "")
                        .param("sortByName", "false"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }


}