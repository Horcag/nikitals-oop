package ru.ssau.tk.nikitals.oop.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ssau.tk.nikitals.oop.core.models.MathFunctionEntity;
import ru.ssau.tk.nikitals.oop.core.service.MathFunctionService;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/functions")
public class MathFunctionController {
    private final MathFunctionService service;
    private final Logger logger = Logger.getLogger(MathFunctionController.class.getName());

    @PostMapping
    public ResponseEntity<MathFunctionEntity> createFunction(@RequestParam String name,
                                                             @RequestParam String type,
                                                             @RequestParam String parameters) {
        MathFunctionEntity function = service.createFunction(name, type, parameters);
        return ResponseEntity.ok(function);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MathFunctionEntity> getFunctionById(@PathVariable Long id) {

        return service.getFunctionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<MathFunctionEntity>> getAllFunctions() {
        return ResponseEntity.ok(service.getAllFunctions());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFunction(@PathVariable Long id) {
        service.deleteFunction(id);
        return ResponseEntity.noContent().build();
    }
}
