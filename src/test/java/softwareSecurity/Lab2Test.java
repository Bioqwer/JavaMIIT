package softwareSecurity;

import junit.framework.TestCase;

import java.math.BigInteger;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by aleksandrov_a on 25.04.2015.
 */
public class Lab2Test extends TestCase {

    public void testName() throws Exception {
        BigInteger q = new BigInteger("314159265359");
        System.out.println("q = " + BigInteger.valueOf(4).multiply(q));
        final BigInteger q4add2 = BigInteger.valueOf(4).multiply(q).add(BigInteger.valueOf(2));
        System.out.println("Q = " + q4add2);
        BigInteger p = new BigInteger(q.bitLength(), 0, ThreadLocalRandom.current());
        System.out.println("p = " + p);
        System.out.println(q4add2.compareTo(p));
    }
}