package softwareSecurity;

import java.math.BigInteger;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Test;

import junit.framework.Assert;

import static softwareSecurity.RSA.algoritm;

// TODO: 27.12.15 test all labs
public class SoftwareSecurityTest {

    public void testName() throws Exception {
        BigInteger q = new BigInteger("314159265359");
        System.out.println("q = " + BigInteger.valueOf(4).multiply(q));
        final BigInteger q4add2 = BigInteger.valueOf(4).multiply(q).add(
                BigInteger.valueOf(2));
        System.out.println("Q = " + q4add2);
        BigInteger p = new BigInteger(q.bitLength(),
                                      0,
                                      ThreadLocalRandom.current());
        System.out.println("p = " + p);
        System.out.println(q4add2.compareTo(p));


    }

    @Test
    public void testBasicFunction() throws Exception {
        String word = "Дерево";
        Assert.assertEquals(word.toUpperCase(), algoritm(word));
        word = "alGoRitm";
        Assert.assertEquals(word.toUpperCase(), algoritm(word));
        word = "Ящик";
        Assert.assertEquals(word.toUpperCase(), algoritm(word));
    }
}