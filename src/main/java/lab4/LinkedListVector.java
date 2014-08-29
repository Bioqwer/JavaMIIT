package lab4;

import lab3.VectorIndexOutOfBoundsException;

import java.io.Serializable;

public class LinkedListVector implements Vector, Serializable {
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
        if (number >= getVectorSize() || number < MIN_VECTOR_SIZE)
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
        Element current = head;
        for (int i = 0; i < getVectorSize(); i++)
            result += this.getElement(i) + " ";
        return result += "}";
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
}
