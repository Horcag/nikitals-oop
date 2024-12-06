package ru.ssau.tk.nikitals.oop.application.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.ssau.tk.nikitals.oop.application.dto.TabulatedFunctionDTO;


@Controller
@RequestMapping("/functions")
@RequiredArgsConstructor
@Slf4j
public class FunctionWebController {
    private final String URL = "http://localhost:8080/api/tabulated-functions";

    private final RestTemplate restTemplate;

    /// Переход на главную страницу
    @GetMapping("/main")
    public String showMainPage(Model model) {
        String url = "http://localhost:8080/api/tabulated-functions";
        TabulatedFunctionDTO[] functions = restTemplate.getForObject(url, TabulatedFunctionDTO[].class);
        model.addAttribute("functions", functions); // Передаём список функций
        return "main"; // Возвращает главный шаблон
    }


    @GetMapping("/modal/new")
    public String newFunctionForm(Model model) {
        model.addAttribute("function", new TabulatedFunctionDTO());
        return "fragments/function-form :: functionForm";
    }

    @PostMapping
    public ResponseEntity<?> createFunction(@RequestBody TabulatedFunctionDTO request) {
        try {
            restTemplate.postForObject(URL, request, TabulatedFunctionDTO.class);
            return ResponseEntity.ok("Function created successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping
    public ResponseEntity<?> updateFunction(@RequestBody TabulatedFunctionDTO request) {
        try {
            restTemplate.put(URL, request);
            return ResponseEntity.ok("Function updated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public String deleteFunction(@PathVariable Long id) {
        log.debug("Deleting function with ID: {}", id);
        restTemplate.delete(URL + "/" + id);
        return "Deleted";
    }
}