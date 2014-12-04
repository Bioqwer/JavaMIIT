package JavaSE.lab2;

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

    public int getMinElement() {
        int min = array[0];
        for (int i = 0; i < array.length; i++)
            if (array[i] < min)
                min = array[i];
        return min;
    }

    public int getMaxElement() {
        int max = array[0];
        for (int i = 0; i < array.length; i++)
            if (array[i] > max)
                max = array[i];
        return max;
    }

    public void sortUp()  //сортировка выбором
    {
        int min, temp;

        for (int index = 0; index < array.length - 1; index++) {
            min = index;
            for (int scan = index + 1; scan < array.length; scan++)
                if (array[scan] < array[min])
                    min = scan;

            // Swap the values
            temp = array[min];
            array[min] = array[index];
            array[index] = temp;
        }
    }

    public double euclideanNorm() {
        double result = 0;
        for (int i = 0; i < array.length; i++) {
            result += array[i] * array[i];
        }
        result = Math.sqrt(result);
        return result;
    }

    public void multiplicationToNumber(int number) {
        for (int i = 0; i < array.length; i++)
            array[i] *= number;
    }

    public Vector additionVector(Vector vector) {
        if (array.length != vector.getVectorSize())
            return new Vector(0);
        Vector result = new Vector(array.length);
        for (int i = 0; i < array.length; i++)
            result.setElement(i, array[i] + vector.getElement(i));
        return result;
    }

    public int getScalar(Vector vector) {
        if (array.length != vector.getVectorSize())
            return 0;
        int result = 0;
        for (int i = 0; i < array.length; i++)
            result += array[i] * vector.getElement(i);
        return result;
    }
}
