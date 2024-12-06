package ru.ssau.tk.nikitals.oop.application.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.ssau.tk.nikitals.oop.application.dto.PointDTO;
import ru.ssau.tk.nikitals.oop.application.mapper.PointMapper;
import ru.ssau.tk.nikitals.oop.core.models.PointEntity;
import ru.ssau.tk.nikitals.oop.core.service.PointService;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PointController.class)
@WithMockUser
public class PointControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PointService pointService;

    @MockBean
    private PointMapper pointMapper;

    @Test
    public void testGetPoint() throws Exception {
        PointEntity pointEntity = new PointEntity();
        pointEntity.setId(1L);
        pointEntity.setX(1.0);
        pointEntity.setY(2.0);

        PointDTO pointDTO = new PointDTO();
        pointDTO.setId(1L);
        pointDTO.setX(1.0);
        pointDTO.setY(2.0);

        when(pointService.getPoint(1L)).thenReturn(pointEntity);
        when(pointMapper.toDto(pointEntity)).thenReturn(pointDTO);

        mockMvc.perform(get("/api/points/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"x\":1.0,\"y\":2.0}"));
    }

    @Test
    public void testAddPoint() throws Exception {
        PointDTO pointDTO = new PointDTO();
        pointDTO.setId(1L);
        pointDTO.setFunctionId(1L);
        pointDTO.setX(1.0);
        pointDTO.setY(2.0);

        PointEntity pointEntity = new PointEntity();
        pointEntity.setId(1L);
        pointEntity.setX(1.0);
        pointEntity.setY(2.0);

        when(pointService.addPoint(1L, 1.0, 2.0)).thenReturn(pointEntity);
        when(pointMapper.toDto(pointEntity)).thenReturn(pointDTO);

        mockMvc.perform(post("/api/points")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"functionId\":1,\"x\":1.0,\"y\":2.0}")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"x\":1.0,\"y\":2.0}"));
    }

    @Test
    public void testUpdatePoint() throws Exception {
        PointDTO pointDTO = new PointDTO();
        pointDTO.setId(1L);
        pointDTO.setY(3.0);

        PointEntity pointEntity = new PointEntity();
        pointEntity.setId(1L);
        pointEntity.setY(3.0);

        when(pointService.updatePoint(1L, 3.0)).thenReturn(pointEntity);
        when(pointMapper.toDto(pointEntity)).thenReturn(pointDTO);

        mockMvc.perform(put("/api/points")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"y\":3.0}")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"y\":3.0}"));
    }

    @Test
    public void testDeletePoint() throws Exception {
        mockMvc.perform(delete("/api/points/1")
                        .with(csrf()))
                .andExpect(status().isNoContent());
    }
}