package lab3;

import lab3.vectors.LinkedListVector;
import org.junit.Test;

/**
 * Created by Antony on 04.08.2014.
 */
public class ExceptionsTest {

    @Test(expected = VectorIndexOutOfBoundsException.class)
    public void testVectorIndexOutOfBoundsException() {
        LinkedListVector linkedListVector = new LinkedListVector();

        linkedListVector.addElement(1);
        linkedListVector.getElement(10);
    }


}
