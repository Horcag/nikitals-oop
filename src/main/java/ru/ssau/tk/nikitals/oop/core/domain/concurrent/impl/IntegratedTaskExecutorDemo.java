package ru.ssau.tk.nikitals.oop.core.domain.concurrent.impl;

import ru.ssau.tk.nikitals.oop.core.domain.functions.api.MathFunction;
import ru.ssau.tk.nikitals.oop.core.domain.functions.api.TabulatedFunction;
import ru.ssau.tk.nikitals.oop.core.domain.functions.impl.ArrayTabulatedFunction;
import ru.ssau.tk.nikitals.oop.core.domain.functions.impl.SqrFunction;
import ru.ssau.tk.nikitals.oop.core.domain.tools.annotations.JaCoCoGenerated;

import java.util.concurrent.ExecutionException;

@JaCoCoGenerated
public class IntegratedTaskExecutorDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MathFunction f = new SqrFunction();
        TabulatedFunction function = new ArrayTabulatedFunction(f, 1,200, 10000); // Инициализация функции
        double a = 1;
        double b = 10;
        int threads = 16;
        int totalSteps = 1000000;

        ParallelIntegrator integrator = new ParallelIntegrator(threads, totalSteps);
        double result = integrator.integrate(function, a, b);

        System.out.println("Result: " + result);
        integrator.shutdown();
    }
}
