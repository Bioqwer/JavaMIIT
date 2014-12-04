package JavaSE.lab7;

import JavaSE.lab6.Vector;
import JavaSE.lab6.impl.ArrayVector;

import java.util.Random;

/**
 * Created by Antony on 26.08.2014.
 */
public class ThreadExample {

    public static void write(Vector vector, int index) {
        Runnable runnable = () -> {
            Random ran = new Random();
            int x = ran.nextInt(10) + 1;
            if (index < vector.getVectorSize()) {
                vector.setElement(index, x);
                System.out.println("Write: " + vector.getElement(index) + " to position " + index);
            } else
                return;
        };
        new Thread(runnable).start();
    }

    public static void read(Vector vector, int index) {
        Runnable runnable = () -> {
            if (index < vector.getVectorSize())
                System.out.println("Read: " + vector.getElement(index) + " from position " + index);
            else
                return;
        };
        new Thread(runnable).start();
    }

    public static void main(String[] args) {
        int size = 10;
        Vector vector = new ArrayVector(size);
        for (int i = 0; i < vector.getVectorSize(); i++) {
            vector.setElement(i, 0);
        }
        for (int i = 0; i < size; i++) {
            write(vector, i);
            read(vector, i);
        }
    }
}
