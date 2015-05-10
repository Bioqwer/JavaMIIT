package softwareSecurity;

import junit.framework.TestCase;

import java.math.BigInteger;
import java.util.Arrays;
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

        String asd = "ﬂ¿…÷” ≈Õ√ÿŸ«’‘€¬¿œ–ŒÀƒ∆›®ﬂ◊—Ã»“‹¡ﬁ";
        System.out.println("asd.getBytes() = " + Arrays.toString(asd.getBytes()));
        String lower = "¿ﬂ";
        lower = lower.toUpperCase();
        System.out.println("lower.getBytes() = " + Arrays.toString(lower.getBytes()));
        asd = "ALGORITM";
        System.out.println("asd.getBytes() = " + Arrays.toString(asd.getBytes()));
        lower = "az";
        lower = lower.toUpperCase();
        System.out.println("lower.getBytes() = " + Arrays.toString(lower.getBytes()));
        int a = 78;
        System.out.println();
    }
}