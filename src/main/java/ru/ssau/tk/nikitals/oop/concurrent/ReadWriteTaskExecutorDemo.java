package ru.ssau.tk.nikitals.oop.concurrent;

import ru.ssau.tk.nikitals.oop.functions.api.TabulatedFunction;
import ru.ssau.tk.nikitals.oop.functions.impl.ConstantFunction;
import ru.ssau.tk.nikitals.oop.functions.impl.LinkedListTabulatedFunction;

public class ReadWriteTaskExecutorDemo {
    public static void main(String[] args) {
        TabulatedFunction function = new LinkedListTabulatedFunction(new ConstantFunction(-1), 1, 100, 1000);
        Thread readThread = new Thread(new ReadTask(function));
        Thread writeThread = new Thread(new WriteTask(function, 0.5));

        readThread.start();
        writeThread.start();
    }
}
