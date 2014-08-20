package lab5.impl;

import lab5.Vector;

/**
 * Created by Antony on 20.08.2014.
 */
public class VectorFactory implements lab5.VectorFactory {

    public VectorFactory() {
    }

    @Override
    public Vector createVector(int size) {
        return new ArrayVector(size);
    }
}
