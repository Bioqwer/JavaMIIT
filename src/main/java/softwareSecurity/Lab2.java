package softwareSecurity;

import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Antony on 12.04.2015.
 */
public class Lab2 {

    public static boolean rabinMillerTest(BigInteger p, int rounds) {
        // Find a and m such that m is odd and this == 1 + 2**a * m
        BigInteger thisMinusOne = p.subtract(BigInteger.ONE);
        BigInteger m = thisMinusOne;
        int a = m.getLowestSetBit();
        m = m.shiftRight(a);

        Random rnd = ThreadLocalRandom.current();
        // Do the tests
        for (int i = 0; i < rounds; i++) {
            // Generate a uniform random on (1, this)
            BigInteger b;
            do {
                b = new BigInteger(p.bitLength(), rnd);
            } while (b.compareTo(BigInteger.ONE) <= 0 || b.compareTo(p) >= 0);

            int j = 0;
            BigInteger z = b.modPow(m, p);
            while (!((j == 0 && z.equals(BigInteger.ONE)) || z.equals(thisMinusOne))) {
                if (j > 0 && z.equals(BigInteger.ONE) || ++j == a)
                    return false;
                z = z.modPow(BigInteger.valueOf(2), p);
            }
        }
        return true;
    }

    public static boolean simple(BigInteger p) {
        int[] primesTo100 = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};
        //step 2  Compare with little primes
        for (int elements : primesTo100) {
            //return p If prime
            if (p.remainder(BigInteger.valueOf(elements)).equals(BigInteger.ZERO)) {
                System.out.println(p.remainder(BigInteger.valueOf(elements)).equals(BigInteger.ZERO));
                System.out.println("p = " + p);
                System.out.println("Sostavnoe with = " + elements);
                return false;
            }
        }
        return true;
    }

    public static BigInteger generateFrom(BigInteger q) {
        long ql = q.longValue();
        long r = (new Random().nextLong() % (4 * ql + 2 - ql)) + ql;
        if (r < 0) {
            r = -r;
        }
        BigInteger p = BigInteger.valueOf(r);
        //if 4*q+2>p   generate new
        final BigInteger q2add2 = BigInteger.valueOf(2).multiply(q).add(BigInteger.valueOf(2));
        boolean found = false;
        while (!found) {
            // ( r >= q && r <= 2 * q + 2 && r % 2 == 0)
            boolean expes = p.compareTo(q) == 1;
            expes = expes && (p.compareTo(q2add2) < 1);
            expes = expes && (p.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO));
            if (!expes)
                found = true;
            else {
                ql = q.longValue();
                r = (new Random().nextLong() % (4 * ql + 2 - ql)) + ql;
                if (r < 0) {
                    r = -r;
                }
                p = BigInteger.valueOf(r);
            }
        }
        p = p.multiply(q).add(BigInteger.ONE);
        //System.out.println("Generated r = " + p);
        return p;
    }

    public static long randLong(long min, long max) {
        long a = (new java.util.Random().nextLong() % (max - min)) + min;
        boolean found = false;
        while (!found) {
            if (a >= min && a <= max) {
                found = true;
            } else {
                a = (new java.util.Random().nextLong() % (max - min)) + min;
            }
        }
        return a;
    }

    public static BigInteger metodMourera(BigInteger q) {
        BigInteger p = generateFrom(q);
        //step 1 generate
        boolean found = false;
        while (!found) {
            p = generateFrom(q);
            // step 2 check for Prime with 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97
            while (!simple(p)) {
                p = generateFrom(q);
            }
            // Rabin miller test
            if (rabinMillerTest(p, 100)) {
                found = true;
                // step 3   check for   à^(ð-1)?1 mod p, (a^R-1, p)=1
                long a = randLong(2, p.subtract(BigInteger.ONE).longValue());
                BigInteger a1 = BigInteger.valueOf(a);
                if (a1.modPow(p.subtract(BigInteger.ONE), p).compareTo(BigInteger.ONE) == 0) {
                    //System.out.println("q =" + q);
                    System.out.println("p = " + p);
                    System.out.println("JAVA TEST = " + p.isProbablePrime(100));
                } else {
                    System.out.println("not prime");
                    System.out.println("JAVA TEST =" + p.isProbablePrime(100));
                }
            }
        }
        return p;
    }

    public static void main(String[] args) {
        BigInteger q = new BigInteger("314159265359");
        BigInteger result = metodMourera(q);
        System.out.println("result = " + result);
    }
}
