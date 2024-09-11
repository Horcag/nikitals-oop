package ru.ssau.tk.nikitals.oop.firstLab;

import ru.ssau.tk.nikitals.oop.firstLab.myfirstpackage.MySecondClass;

class MyFirstClass {
    public static void main(String[] args) {
//        System.out.println("Hello World!!!");
//        for (int i = 0; i < args.length; i++) {
//            System.out.println(args[i]);
//        }
        MySecondClass obj = new MySecondClass(2, 4);
        System.out.println(obj.sum());
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                obj.setFirstVar(i);
                obj.setSecondVar(j);
                System.out.print(obj.sum());
                System.out.print(" ");
            }

            System.out.println();
        }
    }
}



