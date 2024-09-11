package ru.ssau.tk.nikitals.oop.firstLab.myfirstpackage;

public class MySecondClass {
    private int firstVar;
    private int secondVar;

    public MySecondClass(int firstVar, int secondVar) {
        this.firstVar = firstVar;
        this.secondVar = secondVar;
    }

    public int getFirstVar() {
        return firstVar;
    }

    public int getSecondVar() {
        return secondVar;
    }

    public void setFirstVar(int firstVar) {
        this.firstVar = firstVar;
    }

    public void setSecondVar(int secondVar) {
        this.secondVar = secondVar;
    }

    public int sum() {
        return firstVar + secondVar;
    }


}
