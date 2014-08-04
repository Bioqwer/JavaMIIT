package lab3;

/**
 * Created by Antony on 02.08.2014.
 */
public class Vectors {

    public static void multiplicationToNumber(ArrayVector arrayVector, int number) {
        for (int i = 0; i < arrayVector.getVectorSize(); i++)
            arrayVector.setElement(i, arrayVector.getElement(i) * number);
    }

    public static ArrayVector additionVector(ArrayVector vectorA, ArrayVector vectorB) {
        if (vectorA.getVectorSize() != vectorB.getVectorSize())
            return new ArrayVector(0);
        ArrayVector result = new ArrayVector(vectorA.getVectorSize());
        for (int i = 0; i < vectorA.getVectorSize(); i++)
            result.setElement(i, vectorA.getElement(i) + vectorB.getElement(i));
        return result;
    }

    public static int getScalar(ArrayVector vectorA, ArrayVector vectorB) {
        if (vectorA.getVectorSize() != vectorB.getVectorSize())
            return 0;
        int result = 0;
        for (int i = 0; i < vectorA.getVectorSize(); i++)
            result += vectorA.getElement(i) * vectorB.getElement(i);
        return result;
    }

}
