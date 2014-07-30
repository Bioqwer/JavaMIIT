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

    @Test
    public void testSortUp() throws Exception {
        System.out.println("VectorTest.testSortUp");
        Vector vector = new Vector(10);
        for (int i = 0; i<vector.getVectorSize();i++)
            vector.setElement(9-i,9-i);
        for (int i = 0; i<vector.getVectorSize();i++) {
            System.out.println("vector = " + vector.getElement(i));
            assertEquals(i, vector.getElement(i));
        }
    }

    @Test
    public void testEuclideanNorm() throws Exception {
        System.out.println("VectorTest.testEuclideanNorm");
        Vector vector = new Vector(5);
        for (int i = 0; i<vector.getVectorSize();i++)
            vector.setElement(i,i);
        assertEquals(Math.sqrt(30),vector.euclideanNorm());
    }

    @Test
    public void testMultiplicationToNumber() throws Exception {
        Vector vector = new Vector(10);
        for (int i = 0; i<vector.getVectorSize();i++)
            vector.setElement(i,i);
        vector.multiplicationToNumber(5);
        for (int i = 0; i<vector.getVectorSize();i++)
            assertEquals(i*5,vector.getElement(i));
    }

    @Test
    public void testAdditionVector() {
        Vector vector1 = new Vector(5);
        Vector vector2 = new Vector(5);
        assertEquals(new Vector(0).getVectorSize(),vector1.additionVector(new Vector(3)).getVectorSize());

        for (int i = 0; i<vector1.getVectorSize();i++)
        {
            vector1.setElement(i,i);
            vector2.setElement(i,2*i);
        }
        Vector result = vector1.additionVector(vector2);
        for (int i = 0; i<vector1.getVectorSize();i++)
            assertEquals(2*i+i,result.getElement(i));
    }

    public void testGetScalar() throws Exception {
        Vector vector1 = new Vector(5);
        Vector vector2 = new Vector(5);
        assertEquals(0,vector1.getScalar(new Vector(3)));

        int res=0;
        for (int i = 0; i<vector1.getVectorSize();i++)
        {
            vector1.setElement(i,i);
            vector2.setElement(i,2*i);
            res+=2*i*i;
        }
        assertEquals(res,vector1.getScalar(vector2));

    }
}