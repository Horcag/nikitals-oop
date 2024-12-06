package ru.ssau.tk.nikitals.oop.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ssau.tk.nikitals.oop.application.dto.PointDTO;
import ru.ssau.tk.nikitals.oop.application.mapper.PointMapper;
import ru.ssau.tk.nikitals.oop.core.models.PointEntity;
import ru.ssau.tk.nikitals.oop.core.service.PointService;

@RestController
@RequestMapping("/api/points")
@RequiredArgsConstructor
public class PointController {
    private final PointService pointService;
    private final PointMapper pointMapper;

    @GetMapping("/{pointId}")
    public ResponseEntity<PointDTO> getPoint(@PathVariable Long pointId) {
        PointEntity entity = pointService.getPoint(pointId);
        return ResponseEntity.ok(pointMapper.toDto(entity));
    }

    @PostMapping
    public ResponseEntity<PointDTO> addPoint(@RequestBody PointDTO dto) {
        PointEntity entity = pointService.addPoint(dto.getFunctionId(), dto.getX(), dto.getY());
        return ResponseEntity.ok(pointMapper.toDto(entity));
    }

    @PutMapping
    public ResponseEntity<PointDTO> updatePoint(@RequestBody PointDTO dto) {
        PointEntity entity = pointService.updatePoint(dto.getId(), dto.getY());
        return ResponseEntity.ok(pointMapper.toDto(entity));
    }

    @DeleteMapping("/{pointId}")
    public ResponseEntity<Void> deletePoint(@PathVariable Long pointId) {
        pointService.deletePoint(pointId);
        return ResponseEntity.noContent().build();
    }
}

