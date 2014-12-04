package JavaSE.lab7;

import JavaSE.lab6.Vector;
import JavaSE.lab6.impl.ArrayVector;

import java.util.Random;

/**
 * Created by Antony on 27.08.2014.
 */
public class Task2 {

    public static void main(String[] args) {
        int size = 10;
        Vector vector = new ArrayVector(size);
        for (int i = 0; i < vector.getVectorSize(); i++) {
            vector.setElement(i, 0);
        }
        for (int i = 0; i < size; i++) {
            Writer writer = new Writer(vector, i);
            Thread threadWriter = new Thread(writer);
            threadWriter.start();
            Reader reader = new Reader(vector, i);
            Thread threadReader = new Thread(reader);
            threadReader.start();

        }

    }

}

class Writer implements Runnable {

    Vector vector;
    int index;

    Writer(Vector vector, int index) {
        this.vector = vector;
        this.index = index;
    }

    @Override
    public void run() {
        Random ran = new Random();
        int x = ran.nextInt(10) + 1;
        if (index < vector.getVectorSize()) {
            vector.setElement(index, x);
            System.out.println("Write: " + vector.getElement(index) + " to position " + index);
        } else
            return;
    }
}

class Reader implements Runnable {

    Vector vector;
    int index;

    Reader(Vector vector, int index) {
        this.vector = vector;
        this.index = index;
    }

    @Override
    public void run() {
        if (index < vector.getVectorSize())
            System.out.println("Read: " + vector.getElement(index) + " from position " + index);
        else
            return;

    }
}
