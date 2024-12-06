package ru.ssau.tk.nikitals.oop.application.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ssau.tk.nikitals.oop.application.dto.FunctionOperandsDTO;
import ru.ssau.tk.nikitals.oop.application.dto.TabulatedFunctionDTO;
import ru.ssau.tk.nikitals.oop.application.mapper.TabulatedFunctionMapper;
import ru.ssau.tk.nikitals.oop.core.models.TabulatedFunctionEntity;
import ru.ssau.tk.nikitals.oop.core.service.FunctionOperationService;

@Slf4j
@RestController
@RequestMapping("/api/tabulated-functions")
@RequiredArgsConstructor
public class TabulatedFunctionOperationController {
    private final FunctionOperationService operationService;
    private final TabulatedFunctionMapper mapper;

    @PostMapping("add")
    public ResponseEntity<TabulatedFunctionDTO> addFunctions(@RequestBody FunctionOperandsDTO operands) {
        TabulatedFunctionEntity entity1 = mapper.toEntity(operands.getFunction1());
        TabulatedFunctionEntity entity2 = mapper.toEntity(operands.getFunction2());
        TabulatedFunctionDTO function = mapper.toDto(operationService.performOperation(entity1, entity2, operationService::add));
        return ResponseEntity.ok(function);
    }

    @PostMapping("subtract")
    public ResponseEntity<TabulatedFunctionDTO> subtractFunctions(@RequestBody FunctionOperandsDTO operands) {
        TabulatedFunctionEntity entity1 = mapper.toEntity(operands.getFunction1());
        TabulatedFunctionEntity entity2 = mapper.toEntity(operands.getFunction2());
        TabulatedFunctionDTO function = mapper.toDto(operationService.performOperation(entity1, entity2, operationService::subtract));
        return ResponseEntity.ok(function);
    }

    @PostMapping("multiply")
    public ResponseEntity<TabulatedFunctionDTO> multiplyFunctions(@RequestBody FunctionOperandsDTO operands) {
        TabulatedFunctionEntity entity1 = mapper.toEntity(operands.getFunction1());
        TabulatedFunctionEntity entity2 = mapper.toEntity(operands.getFunction2());
        TabulatedFunctionDTO function = mapper.toDto(operationService.performOperation(entity1, entity2, operationService::multiply));
        return ResponseEntity.ok(function);
    }

    @PostMapping("divide")
    public ResponseEntity<TabulatedFunctionDTO> divideFunctions(@RequestBody FunctionOperandsDTO operands) {
        TabulatedFunctionEntity entity1 = mapper.toEntity(operands.getFunction1());
        TabulatedFunctionEntity entity2 = mapper.toEntity(operands.getFunction2());
        TabulatedFunctionDTO function = mapper.toDto(operationService.performOperation(entity1, entity2, operationService::divide));
        return ResponseEntity.ok(function);
    }
}