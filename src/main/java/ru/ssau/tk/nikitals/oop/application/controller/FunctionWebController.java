//package ru.ssau.tk.nikitals.oop.application.controller;
//
//import lombok.RequiredArgsConstructor;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import ru.ssau.tk.nikitals.oop.core.models.TabulatedFunctionEntity;
//import ru.ssau.tk.nikitals.oop.core.service.TabulatedFunctionService;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Controller
//@RequestMapping("/functions")
//@RequiredArgsConstructor
//public class FunctionWebController {
//
//    private final TabulatedFunctionService service;
//    private static final Logger logger = LoggerFactory.getLogger(FunctionWebController.class);
//
//    @GetMapping
//    public String listFunctions(Model model) {
//        logger.info("Fetching list of functions");
//        List<TabulatedFunctionEntity> functions = service.getAllFunctions();
//        model.addAttribute("functions", functions);
//        return "functions/list";
//    }
//
//    @GetMapping("/{id}/edit")
//    public String showEditForm(@PathVariable Long id, Model model) {
//        TabulatedFunctionEntity function = service.getFunctionById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid function ID: " + id));
//        model.addAttribute("function", function);
//        model.addAttribute("xValues", service.getXValuesAsString(function));
//        model.addAttribute("yValues", service.getYValuesAsString(function));
//        return "functions/edit";
//    }
//    @PostMapping("/{id}/edit")
//    public String editFunction(@PathVariable Long id,
//                               @RequestParam String name,
//                               @RequestParam List<Double> xValues,
//                               @RequestParam List<Double> yValues) {
//        if (xValues.size() != yValues.size()) {
//            throw new IllegalArgumentException("xValues and yValues must have the same size.");
//        }
//
//        // Получаем существующую функцию и обновляем ее
//        TabulatedFunctionEntity function = service.getFunctionById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid function ID: " + id));
//
//        function.setName(name);
//        function.setPoints(new ArrayList<>());
//        for (int i = 0; i < xValues.size(); i++) {
//            function.getPoints().add(new TabulatedFunctionEntity.Point(xValues.get(i), yValues.get(i)));
//        }
//
//        service.saveFunction(function.getName(), xValues, yValues); // Обновляем функцию в базе данных
//        return "redirect:/functions";
//    }
//
//    @GetMapping("/create")
//    public String showCreateForm(Model model) {
//        logger.info("Displaying the create function form");
//        model.addAttribute("function", new TabulatedFunctionEntity());
//        return "functions/create";
//    }
//
//    @PostMapping("/create")
//    public String createFunction(@ModelAttribute TabulatedFunctionEntity function,
//                                 @RequestParam List<Double> xValues,
//                                 @RequestParam List<Double> yValues) {
//        if (xValues.size() != yValues.size()) {
//            logger.error("Size mismatch between xValues and yValues");
//            return "redirect:/functions/create?error=invalidData";
//        }
//        logger.info("Creating new function with name: {}", function.getName());
//        logger.debug("xValues: {}, yValues: {}", xValues, yValues);
//        service.saveFunction(function.getName(), xValues, yValues);
//        return "redirect:/functions";
//    }
//
//    @GetMapping("/{id}/delete")
//    public String deleteFunction(@PathVariable Long id) {
//        logger.info("Deleting function with id: {}", id);
//        service.deleteFunction(id);
//        return "redirect:/functions";
//    }
//}
