package ru.ssau.tk.nikitals.oop.core.domain.concurrent.api;

import ru.ssau.tk.nikitals.oop.core.domain.functions.api.TabulatedFunction;

import java.util.concurrent.ExecutionException;

@FunctionalInterface
public interface Integrator {
    double integrate(TabulatedFunction function, double a, double b) throws InterruptedException, ExecutionException;
}
