package ru.ssau.tk.nikitals.oop.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ssau.tk.nikitals.oop.core.domain.functions.api.TabulatedFunction;
import ru.ssau.tk.nikitals.oop.core.domain.operations.impl.TabulatedFunctionOperationService;
import ru.ssau.tk.nikitals.oop.core.models.TabulatedFunctionEntity;

import java.util.Optional;
import java.util.function.BiFunction;

@Service
@RequiredArgsConstructor
public class FunctionOperationService {
    private final TabulatedFunctionOperationService operationService = new TabulatedFunctionOperationService();
    private final TabulatedFunctionService tabulatedService;


    public TabulatedFunction add(TabulatedFunction f1, TabulatedFunction f2) {
        return operationService.sum(f1, f2);
    }

    public TabulatedFunction subtract(TabulatedFunction f1, TabulatedFunction f2) {
        return operationService.subtract(f1, f2);
    }

    public TabulatedFunction multiply(TabulatedFunction f1, TabulatedFunction f2) {
        return operationService.multiply(f1, f2);
    }

    public TabulatedFunction divide(TabulatedFunction f1, TabulatedFunction f2) {
        return operationService.divide(f1, f2);
    }

    public TabulatedFunction performOperation(TabulatedFunctionEntity fn1, TabulatedFunctionEntity fn2,
                                              BiFunction<TabulatedFunction, TabulatedFunction, TabulatedFunction> operation) {
        fn1.setFactoryType(Optional.ofNullable(fn1.getFactoryType()).orElse("Массив"));
        fn2.setFactoryType(Optional.ofNullable(fn2.getFactoryType()).orElse("Массив"));
        TabulatedFunction f1 = tabulatedService.loadFunction(fn1);
        TabulatedFunction f2 = tabulatedService.loadFunction(fn2);
        return operation.apply(f1, f2);
    }

}
