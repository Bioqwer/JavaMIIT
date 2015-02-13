package JavaSE.lab5;

import JavaSE.lab5.impl.ArrayVector;
import JavaSE.lab5.impl.Vectors;

public class FactoryTest {

    public static void main(String[] args) {
        Vectors vectors = new Vectors();
        ArrayVector arrayVector = (ArrayVector) Vectors.createInstance(2);
        System.out.println("arrayVector.getVectorSize() = " + arrayVector.getVectorSize());
        arrayVector = (ArrayVector) Vectors.createInstance(5);
        System.out.println("arrayVector.getVectorSize() = " + arrayVector.getVectorSize());
    }

}