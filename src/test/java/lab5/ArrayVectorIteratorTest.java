package lab5;

import lab5.impl.ArrayVector;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;

public class ArrayVectorIteratorTest {

    @Test
    public void testIterator() throws Exception {
        int size = 8;
        ArrayVector arrayVector = new ArrayVector(size);
        for (int i = 0; i < size; i++)
            arrayVector.setElement(i, i);
        Iterator iterator = arrayVector.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            Object element = iterator.next();
            assertEquals(element, arrayVector.getElement(i));
            System.out.print(element + " ");
            i++;
        }
        System.out.println("\nremote last");
        iterator.remove();
        iterator = arrayVector.iterator();
        while (iterator.hasNext()) {
            Object element = iterator.next();
            System.out.print(element + " ");
        }
        System.out.println("\nremote first");
        iterator = arrayVector.iterator();
        iterator.remove();
        while (iterator.hasNext()) {
            Object element = iterator.next();
            System.out.print(element + " ");
        }
        System.out.println("\nremote middle = 2 ");
        iterator = arrayVector.iterator();
        System.out.print(iterator.next() + " ");
        iterator.next();
        iterator.remove();
        while (iterator.hasNext()) {
            Object element = iterator.next();
            System.out.print(element + " ");
        }
    }
}