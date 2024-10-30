package ru.ssau.tk.nikitals.oop.concurrent;

import ru.ssau.tk.nikitals.oop.functions.api.TabulatedFunction;

public class MultiplyingTask implements Runnable {
    private final TabulatedFunction function;

    public MultiplyingTask(TabulatedFunction function) {
        this.function = function;
    }

    @Override
    public void run() {
        for (int i = 0; i < function.getCount(); i++) {
            synchronized (function) {
                function.setY(i, function.getY(i) * 2);
            }
        }
        System.out.println("Current thread completed execution of the task: " + Thread.currentThread().getName());
    }
}
