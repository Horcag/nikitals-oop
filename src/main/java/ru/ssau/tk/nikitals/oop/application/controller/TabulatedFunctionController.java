package ru.ssau.tk.nikitals.oop.application.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ssau.tk.nikitals.oop.application.dto.TabulatedFunctionDTO;
import ru.ssau.tk.nikitals.oop.application.mapper.TabulatedFunctionMapper;
import ru.ssau.tk.nikitals.oop.core.models.TabulatedFunctionEntity;
import ru.ssau.tk.nikitals.oop.core.models.TabulatedFunctionFilter;
import ru.ssau.tk.nikitals.oop.core.service.TabulatedFunctionService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tabulated-functions")
@Slf4j
public class TabulatedFunctionController {
    private final TabulatedFunctionService service;
    private final TabulatedFunctionMapper mapper;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody TabulatedFunctionDTO request) {
        try {
            TabulatedFunctionEntity entity = mapper.toEntity(request);
            service.save(entity);
            request = mapper.toDto(entity);
            return ResponseEntity.ok(request);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<TabulatedFunctionDTO> loadFunction(@PathVariable Long id) {
        TabulatedFunctionEntity functionEntity = service.getTabulatedFunction(id);
        TabulatedFunctionDTO dto = mapper.toDto(functionEntity);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<TabulatedFunctionDTO>> getAllFunctions() {
        return ResponseEntity.ok(service.getAllFunctions().stream()
                .map(mapper::toDto)
                .toList());
    }

    @PutMapping
    public ResponseEntity<TabulatedFunctionDTO> updateFunction(@RequestBody TabulatedFunctionDTO dto) {
        TabulatedFunctionEntity entity = mapper.toEntity(dto);
        log.debug("Updating function with ID: {}, name: {}", entity.getId(), entity.getName());
        service.save(entity);
        dto = mapper.toDto(entity);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFunction(@PathVariable Long id) {
        service.deleteFunction(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/apply/{id}")
    public ResponseEntity<Double> applyFunction(@PathVariable Long id, @RequestParam double x) {
        TabulatedFunctionEntity functionEntity = service.getTabulatedFunction(id);
        Double y = service.loadFunction(functionEntity).apply(x);
        log.debug("Applying function with ID: {}, x: {}, y: {}", id, x, y);
        return ResponseEntity.ok(y);
    }

    @PutMapping("/{id}/name")
    public ResponseEntity<Void> updateFunctionName(@PathVariable Long id, @RequestBody String newName) {
        service.updateFunctionName(id, newName);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<TabulatedFunctionDTO>> searchFunctions(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String type,
            @RequestParam(defaultValue = "false") boolean sortByName) {

        TabulatedFunctionFilter filter = TabulatedFunctionFilter.builder()
                .name(name.isEmpty() ? null : name)
                .factoryType(type.isEmpty() ? null : type)
                .sortByName(sortByName)
                .build();

        List<TabulatedFunctionEntity> results = service.search(filter);
        return ResponseEntity.ok(results.stream()
                .map(mapper::toDto)
                .toList());
    }

    @PostMapping("/save-to-file")
    public ResponseEntity<Void> saveToFile(@RequestBody TabulatedFunctionDTO dto, @RequestParam String fileName) {
        service.saveToFile(mapper.toEntity(dto), fileName);
        return ResponseEntity.ok().build();
    }
}
