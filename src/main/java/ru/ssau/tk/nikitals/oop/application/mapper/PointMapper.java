package ru.ssau.tk.nikitals.oop.application.mapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.ssau.tk.nikitals.oop.application.dto.PointDTO;
import ru.ssau.tk.nikitals.oop.core.models.PointEntity;

@Component
@Slf4j
public class PointMapper {
    public PointDTO toDto(PointEntity entity) {
        return PointDTO.builder()
                .id(entity.getId())
                .x(entity.getX())
                .y(entity.getY())
                .functionId(entity.getFunction().getId())
                .build();
    }
}
