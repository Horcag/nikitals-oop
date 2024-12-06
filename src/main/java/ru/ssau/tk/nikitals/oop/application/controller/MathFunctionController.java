package ru.ssau.tk.nikitals.oop.application.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ssau.tk.nikitals.oop.core.service.MathFunctionRegistry;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/functions")
@Slf4j
public class MathFunctionController {
    private final MathFunctionRegistry registry;

    @GetMapping("/available")
    public ResponseEntity<List<String>> getAvailableFunctions() {
        List<String> functions = registry.getAvailableFunctions();
        log.debug("Available functions: {}", functions);
        return ResponseEntity.ok(functions);
    }

}
