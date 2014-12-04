package JavaSE.lab6.impl;

import JavaSE.lab6.Vector;

/**
 * Created by Antony on 20.08.2014.
 */
public class VectorFactory implements JavaSE.lab6.VectorFactory {

    public VectorFactory() {
    }

    @Override
    public Vector createVector(int size) {
        return new ArrayVector(size);
    }
}
