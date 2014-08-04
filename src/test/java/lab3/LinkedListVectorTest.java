package lab3;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.*;

public class LinkedListVectorTest extends TestCase {

    @Test
    public void testAddElement() throws Exception {
        LinkedListVector linkedListVector = new LinkedListVector();
        for(int i=0;i<8;i++)
            linkedListVector.addElement(i);
        assertEquals(8,linkedListVector.getVectorSize());
    }

    @Test
    public void testGetElement() throws Exception {
        LinkedListVector linkedListVector = new LinkedListVector();
        for(int i=0;i<8;i++)
            linkedListVector.addElement(i);
        for(int i=0;i<8;i++)
            assertEquals(i,linkedListVector.getElement(i));
    }

    @Test
    public void testSetElement() throws Exception {
        LinkedListVector linkedListVector = new LinkedListVector();
        for(int i=0;i<8;i++)
            linkedListVector.addElement(i);
        for(int i=0;i<8;i++)
            linkedListVector.setElement(i, i + 2);
        for(int i=0;i<8;i++)
            assertEquals(i+2,linkedListVector.getElement(i));
    }

    @Test
    public void testGetVectorSize() throws Exception {
        LinkedListVector linkedListVector = new LinkedListVector();
        for(int i=0;i<100;i++)
        {
            assertEquals(i,linkedListVector.getVectorSize());
            linkedListVector.addElement(i);
        }
    }

    @Test
    public void testEuclideanNorm() throws Exception {
        LinkedListVector linkedListVector = new LinkedListVector();
        for (int i = 0; i<5;i++)
            linkedListVector.addElement(i);
        assertEquals(Math.sqrt(30),linkedListVector.euclideanNorm());
    }

    public void testDeleteElement() throws Exception {
        LinkedListVector linkedListVector = new LinkedListVector();
        for (int i = 0; i<5;i++)
            linkedListVector.addElement(i);
        linkedListVector.deleteElement(0);
        assertEquals(1,linkedListVector.getElement(0));
        linkedListVector.deleteElement(1);
        assertEquals(3,linkedListVector.getElement(1));

        linkedListVector = new LinkedListVector();
        for (int i = 0; i<5;i++) {
            linkedListVector.addElement(i);
            System.out.println("linkedListVector.getElement("+i+") = " + linkedListVector.getElement(i));
        }
        linkedListVector.deleteElement(4);
        for (int i = 0; i<4;i++)
            System.out.println("linkedListVector.getElement("+i+") = " + linkedListVector.getElement(i));
        assertEquals(3,linkedListVector.getElement(3));
    }
}