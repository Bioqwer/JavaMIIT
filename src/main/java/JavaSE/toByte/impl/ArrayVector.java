package JavaSE.toByte.impl;

import JavaSE.toByte.Vector;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by Antony on 02.08.2014.
 */
public class ArrayVector implements Vector, Serializable, Cloneable {
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

    @Override
    public String toString() {
        return "ArrayVector{" +
                "array=" + Arrays.toString(array) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArrayVector)) return false;

        ArrayVector that = (ArrayVector) o;

        if (!Arrays.equals(array, that.array)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        ArrayVector clone = new ArrayVector(getVectorSize());
        for (int i = 0; i < getVectorSize(); i++) {
            clone.setElement(i, this.getElement(i));
        }
        return clone;
    }

    @Override
    public Iterator iterator() {
        return new ArrayVectorIterator();
    }

    private class ArrayVectorIterator implements Iterator {
        private int index = 0;

        @Override
        public boolean hasNext() {
            if (index < getVectorSize())
                return true;
            else return false;
        }

        @Override
        public Object next() {
            return getElement(index++);
        }

        @Override
        public void remove() {
            if (index != 0)
                index--;
            int[] temp = new int[getVectorSize() - 1];
            int j = 0;
            for (int i = 0; i < index && i < temp.length; i++)
                temp[j++] = getElement(i);
            for (int i = index + 1; i < getVectorSize(); i++)
                temp[j++] = getElement(i);

            array = temp;
        }
    }
}
