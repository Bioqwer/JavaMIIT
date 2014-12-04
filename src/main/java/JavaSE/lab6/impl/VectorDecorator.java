package JavaSE.lab6.impl;

import JavaSE.lab6.Vector;

import java.util.Iterator;

/**
 * Created by Antony on 26.08.2014.
 */
public abstract class VectorDecorator implements Vector {
    protected Vector vector;

    public VectorDecorator(Vector vector) {
        this.vector = vector;
    }

    @Override
    public int getElement(int number) {
        return vector.getElement(number);
    }

    @Override
    public void setElement(int number, int element) {
        throw new UnsupportedOperationException("UnsupportedOperationException");
    }

    @Override
    public int getVectorSize() {
        return vector.getVectorSize();
    }

    @Override
    public double euclideanNorm() {
        return vector.euclideanNorm();
    }

    @Override
    public Iterator iterator() {
        return vector.iterator();
    }
}
