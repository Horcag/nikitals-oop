package ru.ssau.tk.nikitals.oop.application.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.ssau.tk.nikitals.oop.application.dto.FunctionFileDTO;
import ru.ssau.tk.nikitals.oop.application.dto.TabulatedFunctionDTO;
import ru.ssau.tk.nikitals.oop.application.mapper.TabulatedFunctionMapper;
import ru.ssau.tk.nikitals.oop.core.domain.functions.api.TabulatedFunction;
import ru.ssau.tk.nikitals.oop.core.domain.functions.factory.impl.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.nikitals.oop.core.domain.functions.impl.ArrayTabulatedFunction;
import ru.ssau.tk.nikitals.oop.core.models.TabulatedFunctionEntity;
import ru.ssau.tk.nikitals.oop.core.ports.io.FunctionsIO;
import ru.ssau.tk.nikitals.oop.core.service.TabulatedFunctionService;

import java.io.*;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
@Slf4j
public class FileController {
    private final TabulatedFunctionService functionService;
    private final TabulatedFunctionMapper mapper;

    @PostMapping("/upload")
    public ResponseEntity<TabulatedFunctionDTO> uploadFunction(
            @RequestParam("file") MultipartFile file,
            @RequestParam("format") String format) {
        try (BufferedInputStream inputStream = new BufferedInputStream(file.getInputStream())) {
            TabulatedFunction function;
            switch (format) {
                case "binary":
                    function = FunctionsIO.readTabulatedFunction(inputStream, new ArrayTabulatedFunctionFactory());
                    break;
                case "text":
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                        function = FunctionsIO.readTabulatedFunction(reader, new ArrayTabulatedFunctionFactory());
                    }
                    break;
                case "json":
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                        function = FunctionsIO.deserializeJson(reader, ArrayTabulatedFunction.class);
                    }
                    break;
                case "xml":
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                        function = FunctionsIO.deserializeXml(reader);
                    } catch (Exception e) {
                        function = null;
                        log.error(e.getMessage());
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported format: " + format);
            }
            TabulatedFunctionEntity entity = functionService.createEntity(function, file.getOriginalFilename(), "Массив");
            return ResponseEntity.ok(mapper.toDto(entity));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/download")
    public void downloadFunction(@RequestBody FunctionFileDTO functionFileDTO,
                                 HttpServletResponse response) throws IOException {
        TabulatedFunctionDTO functionDTO = functionFileDTO.getTabulatedFunction();
        functionDTO.setFactoryType("Массив");
        TabulatedFunctionEntity entity = mapper.toEntity(functionDTO);

        TabulatedFunction function = functionService.loadFunction(entity);
        String format = functionFileDTO.getFormat();
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=function." + format);
        try (OutputStream outputStream = response.getOutputStream()) {
            switch (format) {
                case "binary":
                    FunctionsIO.writeTabulatedFunction(new BufferedOutputStream(outputStream), function);
                    break;
                case "text":
                    try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream))) {
                        FunctionsIO.writeTabulatedFunction(writer, function);
                    }
                    break;
                case "json":
                    try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream))) {
                        FunctionsIO.serializeJson(writer, function);
                    }
                    break;
                case "xml":
                    try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream))) {
                        FunctionsIO.serializeXml(writer, (ArrayTabulatedFunction) function);
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported format: " + format);
            }
        }
    }
}
