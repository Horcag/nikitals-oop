package ru.ssau.tk.nikitals.oop.concurrent;

import ru.ssau.tk.nikitals.oop.functions.impl.LinkedListTabulatedFunction;
import ru.ssau.tk.nikitals.oop.functions.impl.UnitFunction;

import java.util.LinkedList;
import java.util.List;

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
