package lab6.impl;

import org.junit.Test;

import static org.junit.Assert.*;

public class Lab6Tests {

    @Test
    public void testEquals() throws Exception {
        ArrayVector arrayVector =new ArrayVector(3);
        for (int i = 0; i < 3; i++) {
            arrayVector.setElement(i,i);
        }
        ArrayVector arrayVector2 =new ArrayVector(3);
        for (int i = 0; i < 3; i++) {
            arrayVector2.setElement(i,i);
        }
        LinkedListVector listVector = new LinkedListVector(3);
        LinkedListVector listVector2 = new LinkedListVector(3);
        for (int i = 0; i < 3; i++) {
            listVector.setElement(i,i);
            listVector2.setElement(i, i);
        }
        System.out.println("Lab6Tests.testEquals arrayVector");
        System.out.println("arrayVector = " + arrayVector);
        System.out.println("arrayVector2 = " + arrayVector2);
        System.out.println("arrayVector.equals(arrayVector2) = " + arrayVector.equals(arrayVector2));
        assertEquals(true,arrayVector.equals(arrayVector2));   //одинаковые
        for (int i = 0; i < 3; i++) {
            arrayVector2.setElement(i,i+2);
        }
        System.out.println("arrayVector = " + arrayVector);
        System.out.println("arrayVector2 = " + arrayVector2);
        System.out.println("arrayVector.equals(arrayVector2) = " + arrayVector.equals(arrayVector2));
        assertEquals(false,arrayVector.equals(arrayVector2));   //разные
        System.out.println("arrayVector.equals(listVector) = " + arrayVector.equals(listVector));

        System.out.println("Lab6Tests.testEquals LinkedList");

        System.out.println("listVector = " + listVector);
        System.out.println("listVector2 = " + listVector2);
        System.out.println("listVector.equals(listVector2) = " + listVector.equals(listVector2));
        assertEquals(true,listVector.equals(listVector2));      //одинаковые
        for (int i = 0; i < 3; i++) {
            listVector2.setElement(1,3);
        }
        System.out.println("listVector = " + listVector);
        System.out.println("listVector2 = " + listVector2);
        System.out.println("listVector.equals(listVector2) = " + listVector.equals(listVector2));
        assertEquals(false,listVector.equals(listVector2));     //разные

    }

    @Test
    public void testHashCode() throws Exception {
        ArrayVector arrayVector =new ArrayVector(3);
        for (int i = 0; i < 3; i++) {
            arrayVector.setElement(i,i);
        }
        ArrayVector arrayVector2 =new ArrayVector(3);
        for (int i = 0; i < 3; i++) {
            arrayVector2.setElement(i,i);
        }
        LinkedListVector listVector = new LinkedListVector(3);
        LinkedListVector listVector2 = new LinkedListVector(3);
        for (int i = 0; i < 3; i++) {
            listVector.setElement(i,i);
            listVector2.setElement(i, i);
        }

        System.out.println("arrayVector = " + arrayVector + " arrayVector.hashCode() = " + arrayVector.hashCode());
        System.out.println("arrayVector2 = " + arrayVector2 + " arrayVector2.hashCode() = " + arrayVector2.hashCode());
        assertEquals(true, arrayVector.hashCode() == arrayVector2.hashCode());
        arrayVector2.setElement(1, 3);
        System.out.println("arrayVector = " + arrayVector+" arrayVector.hashCode() = " + arrayVector.hashCode());
        System.out.println("arrayVector2 = " + arrayVector2+" arrayVector2.hashCode() = " + arrayVector2.hashCode());
        assertEquals(false,arrayVector.hashCode()==arrayVector2.hashCode());

        System.out.println("listVector = " + listVector+" listVector.hashCode() = " + listVector.hashCode() );
        System.out.println("listVector2 = " + listVector2+" listVector2.hashCode() = " + listVector2.hashCode() );
        assertEquals(true,listVector.hashCode()==listVector2.hashCode());

        listVector2.setElement(1,5);
        System.out.println("listVector = " + listVector+" listVector.hashCode() = " + listVector.hashCode() );
        System.out.println("listVector2 = " + listVector2+" listVector2.hashCode() = " + listVector2.hashCode() );
        assertEquals(false,listVector.hashCode()==listVector2.hashCode());

    }

    @Test
    public void testClone() throws Exception {
        ArrayVector arrayVector =new ArrayVector(3);
        for (int i = 0; i < 3; i++) {
            arrayVector.setElement(i,i);
        }
        LinkedListVector listVector = new LinkedListVector(3);
        for (int i = 0; i < 3; i++) {
            listVector.setElement(i,i);
        }

        ArrayVector clone = (ArrayVector) arrayVector.clone();
        System.out.println("arrayVector = " + arrayVector + "\nclone = " + clone);
        arrayVector.setElement(1,2);
        System.out.println("arrayVector = " + arrayVector + "\nclone = " + clone);

        LinkedListVector cloneList = (LinkedListVector) listVector.clone();
        System.out.println("listVector = " + listVector+" cloneList = " + cloneList);
        listVector.setElement(1,4);
        System.out.println("listVector = " + listVector+" cloneList = " + cloneList);
    }
}