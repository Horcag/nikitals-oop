package ru.ssau.tk.nikitals.oop.core.domain.concurrent.impl;

import ru.ssau.tk.nikitals.oop.core.domain.functions.impl.LinkedListTabulatedFunction;
import ru.ssau.tk.nikitals.oop.core.domain.functions.impl.UnitFunction;
import ru.ssau.tk.nikitals.oop.core.domain.tools.annotations.JaCoCoGenerated;

import java.util.LinkedList;
import java.util.List;

@JaCoCoGenerated
public class MultiplyingTaskExecutorDemo {
    public static void main(String[] args) {
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(new UnitFunction(), 1, 1000, 1000);
        List<Thread> threads = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(new MultiplyingTask(function)));
        }
        for (Thread thread : threads) {
            thread.start();
        }
        System.out.println(threads.size());
        while (!threads.isEmpty()) {
            for (int i = threads.size() - 1; i >= 0; i--) {
                if (!threads.get(i).isAlive()) {
                    threads.remove(i);
                }
            }
        }
        System.out.println(function);
    }
}
