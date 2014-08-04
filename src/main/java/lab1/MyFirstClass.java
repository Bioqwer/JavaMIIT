package lab1;

import lab1.MyFirstPackage.MySecondClass;

public class MyFirstClass {

    public static void main(String[] args) {
        MySecondClass o = new MySecondClass(0, 0);
        int i, j;
        for (i = 1; i <= 8; i++) {
            for (j = 1; j <= 8; j++) {
                o.setFirstVar(i);
                o.setSecondVar(j);
                System.out.println(o.getFirstVar() + " + " + o.getSecondVar() + " = " + +o.plus());
                System.out.println(o.getFirstVar() + " * " + o.getSecondVar() + " = " + +o.multiply());
            }
            System.out.println();
        }

    }
}


