package lab2;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.*;
public class VectorTest extends TestCase {

    @Test
    public void testVectorConstructor() throws Exception {
        System.out.println("VectorTest.testVectorConstructor");
        Vector vector;
        for (int i=-10;i<-1;i++) {    //конструктор с отрицательной длиной
            vector = new Vector(i);
            assertEquals(0,vector.getVectorSize());
        }
        for (int i=0;i<20;i++) {      //с положительной длиной
            vector = new Vector(i);
            assertEquals(i,vector.getVectorSize());
        }
    }

    @Test
    public void testMinMax() throws Exception {
        System.out.println("VectorTest.testMinMax");
        Vector vector = new Vector(20);
        for (int i = 0; i<vector.getVectorSize();i++)
            vector.setElement(i,i*100);
        assertEquals(0,vector.getMinElement());
        assertEquals(1900,vector.getMaxElement());
    }

    @Test
    public void testSetGet() {
        System.out.println("VectorTest.testSetGet");
        Vector vector = new Vector(20);
        for (int i = 0; i<vector.getVectorSize();i++)
            vector.setElement(i,i);
        for (int i = 0; i<vector.getVectorSize();i++)
            assertEquals(i,vector.getElement(i));
    }
}