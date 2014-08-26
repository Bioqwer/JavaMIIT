package lab6.impl;

import lab6.Vector;

/**
 * Created by Antony on 20.08.2014.
 */
public class VectorFactory implements lab6.VectorFactory {

    public VectorFactory() {
    }

    @Override
    public Vector createVector(int size) {
        return new ArrayVector(size);
    }
}
