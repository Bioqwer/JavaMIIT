package JavaSE.toByte.impl;

import JavaSE.lab3.VectorIndexOutOfBoundsException;
import JavaSE.toByte.Vector;

import java.io.Serializable;
import java.util.Iterator;

public class LinkedListVector implements Vector, Serializable, Cloneable {
    public static final int MIN_VECTOR_SIZE = 0;
    private Element head;
    private Element tail;

    public LinkedListVector() {
        head = null;
        tail = null;
    }

    public LinkedListVector(int size) {
        this();
        for (int i = 0; i < size; i++)
            addElement(0);
    }

    public void addElement(int data) {
        if (head == null) {
            head = new Element(data, null, null);
            tail = head;
        }
        if (tail != null) {
            tail = new Element(data, tail, null);
            tail.getPrev().setNext(tail);
        }
    }

    public int getElement(int number) {
        if (number >= getVectorSize() || number < MIN_VECTOR_SIZE)
            throw new VectorIndexOutOfBoundsException();
        Element current = head;
        for (int i = -1; i < number; i++)
            current = current.getNext();
        return current.getField();
    }

    public void setElement(int number, int data) {
        if (number >= getVectorSize() || number < MIN_VECTOR_SIZE)
            throw new VectorIndexOutOfBoundsException();
        Element current = head;
        for (int i = -1; i < number; i++)
            current = current.getNext();
        current.setField(data);
    }

    public int getVectorSize() {
        int result = 0;
        Element current = head;
        while (current != tail) {
            current = current.getNext();
            result++;
        }
        return result;
    }

    public void deleteElement(int number) {
        if (number > getVectorSize() && number < MIN_VECTOR_SIZE)
            throw new VectorIndexOutOfBoundsException();
        Element current = head;
        for (int i = -1; i < number; i++)
            current = current.getNext();
        if (current == head) {
            head = current.getNext();
            head.setPrev(null);
        } else if (current == tail) {
            tail = current.getPrev();
            tail.setNext(null);
        } else {
            current.getPrev().setNext(current.getNext());
            current.getNext().setPrev(current.getPrev());
        }
    }

    public double euclideanNorm() {
        double result = 0;
        Element current = head;
        while (current != null) {
            result += current.getField() * current.getField();
            current = current.getNext();
        }
        result = Math.sqrt(result);
        return result;
    }

    @Override
    public String toString() {
        String result = "LinkedListVector{";
        for (int i = 0; i < getVectorSize(); i++)
            result += this.getElement(i) + " ";
        return result += "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LinkedListVector)) return false;

        LinkedListVector that = (LinkedListVector) o;

        if (this.getVectorSize() != that.getVectorSize())
            return false;

        for (int i = 0; i < getVectorSize(); i++) {
            if (this.getElement(i) != that.getElement(i))
                return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
        for (int i = 0; i < getVectorSize(); i++) {
            result = 31 * result + getElement(i);
        }
        return result;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        LinkedListVector clone = new LinkedListVector(getVectorSize());
        for (int i = 0; i < getVectorSize(); i++) {
            clone.setElement(i, this.getElement(i));
        }
        return clone;
    }

    @Override
    public Iterator iterator() {
        return new LinkedListVectorIterator();
    }

    class Element implements Serializable {
        private int field;
        private Element prev;
        private Element next;

        Element(int field, Element prev, Element next) {
            this.field = field;
            this.prev = prev;
            this.next = next;
        }

        public int getField() {
            return field;
        }

        public void setField(int field) {
            this.field = field;
        }

        public Element getPrev() {
            return prev;
        }

        public void setPrev(Element prev) {
            this.prev = prev;
        }

        public Element getNext() {
            return next;
        }

        public void setNext(Element next) {
            this.next = next;
        }
    }

    private class LinkedListVectorIterator implements Iterator {
        int index = 0;

        @Override
        public Object next() {
            return getElement(index++);
        }

        @Override
        public boolean hasNext() {
            if (index < getVectorSize())
                return true;
            else
                return false;
        }

        @Override
        public void remove() {
            if (index != 0)
                index--;
            if (index >= 0 && index < getVectorSize()) {
                deleteElement(index);
            }
        }

    }
}
