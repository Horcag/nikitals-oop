package ru.ssau.tk.nikitals.oop.concurrent;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.nikitals.oop.functions.api.MathFunction;
import ru.ssau.tk.nikitals.oop.functions.api.TabulatedFunction;
import ru.ssau.tk.nikitals.oop.functions.impl.ArrayTabulatedFunction;
import ru.ssau.tk.nikitals.oop.functions.impl.SqrFunction;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

class ParallelIntegratorTest {
    MathFunction f = new SqrFunction();
    TabulatedFunction function = new ArrayTabulatedFunction(f, 1, 10, 1000); // Инициализация функции
    double a = 1;
    double b = 10;
    int threads = 8;
    int totalSteps = 10000000;

    @Test
    void integrate() throws ExecutionException, InterruptedException {
        ParallelIntegrator integrator = new ParallelIntegrator(threads, totalSteps);
        double result = integrator.integrate(function, a, b);

        assertEquals(333, result, 1e-3);
        integrator.shutdown();
    }
    @Test
    void integrateV2() throws ExecutionException, InterruptedException {
        ParallelIntegrator integrator = new ParallelIntegrator(threads, totalSteps+1);
        double result = integrator.integrate(function, 5, 8);

        assertEquals(129, result, 1e-3);
        integrator.shutdown();
    }


}