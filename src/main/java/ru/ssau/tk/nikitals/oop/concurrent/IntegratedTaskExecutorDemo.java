package ru.ssau.tk.nikitals.oop.concurrent;

import ru.ssau.tk.nikitals.oop.functions.api.MathFunction;
import ru.ssau.tk.nikitals.oop.functions.api.TabulatedFunction;
import ru.ssau.tk.nikitals.oop.functions.impl.ArrayTabulatedFunction;
import ru.ssau.tk.nikitals.oop.functions.impl.SqrFunction;

import java.util.concurrent.ExecutionException;

public class IntegratedTaskExecutorDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MathFunction f = new SqrFunction();
        TabulatedFunction function = new ArrayTabulatedFunction(f, 1,200, 10000); // Инициализация функции
        double a = 1;
        double b = 10;
        int threads = 8;
        int totalSteps = 10000000;

        ParallelIntegrator integrator = new ParallelIntegrator(threads, totalSteps);
        double result = integrator.integrate(function, a, b);

        System.out.println("Result: " + result);
        integrator.shutdown();
    }
}
