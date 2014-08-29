package lab5;

import lab5.impl.ArrayVector;
import lab5.impl.Vectors;

public class FactoryTest {

    public static void main(String[] args) {
        Vectors vectors = new Vectors();
        ArrayVector arrayVector = (ArrayVector) vectors.createInstance(2);
        System.out.println("arrayVector.getVectorSize() = " + arrayVector.getVectorSize());
        arrayVector = (ArrayVector) vectors.createInstance(5);
        System.out.println("arrayVector.getVectorSize() = " + arrayVector.getVectorSize());
    }

}