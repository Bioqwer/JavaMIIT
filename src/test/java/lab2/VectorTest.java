package lab2;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.*;

public class VectorTest extends TestCase {

    public void testVectorConstructor() throws Exception {
        Vector vector;
        for (int i=-10;i<-1;i++) {
            vector = new Vector(i);
            assertEquals(0,vector.getVectorSize());
        }
    }
}