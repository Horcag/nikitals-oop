package ru.ssau.tk.nikitals.oop.secondLab.functions;

import static java.lang.Math.pow;

public class SqrFunction implements MathFunction {
    public double apply(double x) {
        return pow(x, 2);
    }
}