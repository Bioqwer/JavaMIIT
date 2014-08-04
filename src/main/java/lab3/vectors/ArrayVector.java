package lab3.vectors;

/**
 * Created by Antony on 02.08.2014.
 */
public class ArrayVector implements Vector{
    public static final int MIN_VECTOR_SIZE = 0;
    private int[] array;

    public ArrayVector(int vectorSize) {
        setVectorSize(vectorSize);
    }

    public int getElement(int number) {
        if (number < MIN_VECTOR_SIZE || number >= array.length)
            return 0;
        else
            return array[number];
    }

    public void setElement(int number, int element) {
        if (number < MIN_VECTOR_SIZE || number >= array.length) {
            array[number] = 0;
        } else
            array[number] = element;
    }

    public int getVectorSize() {
        return array.length;
    }

    public void setVectorSize(int vectorSize) {
        if (vectorSize > 0) {
            array = new int[vectorSize];
        } else
            array = new int[MIN_VECTOR_SIZE];
    }


    public double euclideanNorm() {
        double result = 0;
        for (int i = 0; i < array.length; i++) {
            result += array[i] * array[i];
        }
        result = Math.sqrt(result);
        return result;
    }
}
