package JavaSE.lab5.impl;

import JavaSE.lab5.Vector;

/**
 * Created by Antony on 20.08.2014.
 */
public class VectorFactory implements JavaSE.lab5.VectorFactory {

    public VectorFactory() {
    }

    @Override
    public Vector createVector(int size) {
        return new ArrayVector(size);
    }
}
