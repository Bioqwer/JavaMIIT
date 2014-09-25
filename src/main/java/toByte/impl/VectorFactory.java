package toByte.impl;

import toByte.Vector;

/**
 * Created by Antony on 20.08.2014.
 */
public class VectorFactory implements toByte.VectorFactory {

    public VectorFactory() {
    }

    @Override
    public Vector createVector(int size) {
        return new ArrayVector(size);
    }
}
