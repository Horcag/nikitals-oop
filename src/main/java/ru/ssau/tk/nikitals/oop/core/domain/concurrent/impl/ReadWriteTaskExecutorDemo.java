package ru.ssau.tk.nikitals.oop.core.domain.concurrent.impl;

import ru.ssau.tk.nikitals.oop.core.domain.functions.api.TabulatedFunction;
import ru.ssau.tk.nikitals.oop.core.domain.functions.impl.ConstantFunction;
import ru.ssau.tk.nikitals.oop.core.domain.functions.impl.LinkedListTabulatedFunction;
import ru.ssau.tk.nikitals.oop.core.domain.tools.annotations.JaCoCoGenerated;

@JaCoCoGenerated
public class ReadWriteTaskExecutorDemo {
    public static void main(String[] args) {
        TabulatedFunction function = new LinkedListTabulatedFunction(new ConstantFunction(-1), 1, 100, 1000);
        Thread readThread = new Thread(new ReadTask(function));
        Thread writeThread = new Thread(new WriteTask(function, 0.5));

        readThread.start();
        writeThread.start();
    }
}
