package ru.ssau.tk.nikitals.oop.concurrent;

import ru.ssau.tk.nikitals.oop.functions.api.TabulatedFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ParallelIntegrator {
    private final ExecutorService executorService;
    private final int threads;
    private final int totalSteps;

    public ParallelIntegrator(int threads, int totalSteps) {
        this.threads = threads;
        this.totalSteps = totalSteps % 2 == 0 ? totalSteps : totalSteps + 1;  // Увеличение шагов до четного
        this.executorService = Executors.newFixedThreadPool(threads);
    }

    public double integrate(TabulatedFunction function, double a, double b) throws InterruptedException, ExecutionException {
        double segmentLength = (b - a) / threads;
        int stepsPerThread = totalSteps / threads;
        List<Future<Double>> results = new ArrayList<>();

        // Создаем задачи для каждого потока
        for (int i = 0; i < threads; i++) {
            double start = a + i * segmentLength;
            double end = start + segmentLength;
            IntegratedTask task = new IntegratedTask(function, start, end, stepsPerThread);
            results.add(executorService.submit(task));
        }

        // Суммируем результаты
        double integral = 0;
        for (Future<Double> result : results) {
            integral += result.get();
        }

        return integral;
    }

    public void shutdown() {
        executorService.shutdown();
    }
}
