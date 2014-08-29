package lab5;

import lab5.impl.LinkedListVector;

import java.util.Iterator;

public class LinkedListVectorIteratorTest {

    public static void main(String[] args) {
        LinkedListVector listVector = new LinkedListVector();
        Iterator iterator = listVector.iterator();
        for (int i = 0; i < 8; i++)
            listVector.addElement(i);
        System.out.println("listVector.getVectorSize() = " + listVector.getVectorSize());
        while (iterator.hasNext()) {
            Object element = iterator.next();
            System.out.print(element + " ");
        }
        System.out.println("\nremote last");
        System.out.println(listVector);
        iterator.remove();
        iterator = listVector.iterator();
        while (iterator.hasNext()) {
            Object element = iterator.next();
            System.out.print(element + " ");
        }
        System.out.println("\nremote first");
        iterator = listVector.iterator();
        iterator.remove();
        while (iterator.hasNext()) {
            Object element = iterator.next();
            System.out.print(element + " ");
        }
        System.out.println("\nremote middle = 2 ");
        iterator = listVector.iterator();
        System.out.print(iterator.next() + " ");
        iterator.next();
        iterator.remove();
        while (iterator.hasNext()) {
            Object element = iterator.next();
            System.out.print(element + " ");
        }

    }

}