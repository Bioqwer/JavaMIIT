package JavaSE.toByte.impl;

import JavaSE.toByte.Vector;

/**
 * Created by Antony on 20.08.2014.
 */
public class VectorFactory implements JavaSE.toByte.VectorFactory {

    public VectorFactory() {
    }

    @Override
    public Vector createVector(int size) {
        return new ArrayVector(size);
    }
}
