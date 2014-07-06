package lab2;


public class Vector {
    public static final int MIN_VECTOR_SIZE = 0;
    private int[] array;

    public Vector(int vectorSize) {
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
        }
        else
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

    public int getMinElement(){
        int min = array[0];
        for (int i=0;i<array.length;i++)
            if (array[i]<min)
                min = array[i];
        return min;
    }

    public int getMaxElement(){
        int max = array[0];
        for (int i=0;i<array.length;i++)
            if (array[i]>max)
                max  = array[i];
        return max;
    }
}
