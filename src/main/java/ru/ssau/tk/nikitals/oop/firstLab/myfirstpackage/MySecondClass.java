package ru.ssau.tk.nikitals.oop.firstLab.myfirstpackage;

public class MySecondClass {
    private int firstVar;
    private int secondVar;

    public MySecondClass(int firstVar, int secondVar) {
        this.firstVar = firstVar;
        this.secondVar = secondVar;
    }

    protected int getFirstVar() {
        return firstVar;
    }

    protected int getSecondVar() {
        return secondVar;
    }

    protected void setFirstVar(int firstVar) {
        this.firstVar = firstVar;
    }

    protected void setSecondVar(int secondVar) {
        this.secondVar = secondVar;
    }

    protected int sum() {
        return firstVar + secondVar;
    }


}
