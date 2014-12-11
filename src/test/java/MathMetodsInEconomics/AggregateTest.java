package MathMetodsInEconomics;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;


public class AggregateTest {

    @Test
    public void testGetB() throws Exception {
        double[][] a = {{0.1078,0.1645,0.0004,0.0012,5.0E-4,0.0,0.0078},
        {0.1156,0.2311,0.0433,0.198,0.0035,0.0343,0.0439},
        {0.0683,0.098,0.4529,0.1935,0.3869,0.1435,0.0326},
        {0.0018,0.0011,0.0012,3.0E-4,0.0086,0.0026,0.1183},
        {0.0346,0.037,0.0647,0.0192,0.1673,0.1953,0.0236},
        {0.0376,0.044,0.0283,0.0612,0.0248,0.1125,0.0541},
        {0.0666,0.1246,0.1173,0.1231,0.0655,0.1431,0.1494}};
        int[][] I = {{0,3},{1},{2},{4},{5},{6}};
        double[] x = {70,20,25,35,50,75,10};
        assertEquals(Aggregate.getB(a,I,x,0,0),(a[0][0]*x[0]+a[0][3]*x[3]+a[3][0]*x[0]+a[3][3]*x[3])/(x[0]+x[3]));
        assertEquals(Aggregate.getB(a,I,x,2,2),(a[2][2]*x[2])/(x[2]));
        assertEquals(Aggregate.getB(a,I,x,0,2),(a[0][2]*x[2]+a[3][2]*x[2])/(x[2]));
        System.out.println("Aggregate.getB(a,I,x,0,2) = " + Aggregate.getB(a, I, x, 0, 2));
    }
}