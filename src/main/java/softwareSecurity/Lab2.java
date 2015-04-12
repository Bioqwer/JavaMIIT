package softwareSecurity;

import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Antony on 12.04.2015.
 */
public class Lab2 {

    public static boolean rabinMillerTest(BigInteger p,int rounds)
    {
        // Find a and m such that m is odd and this == 1 + 2**a * m
        BigInteger thisMinusOne = p.subtract(BigInteger.ONE);
        BigInteger m = thisMinusOne;
        int a = m.getLowestSetBit();
        m = m.shiftRight(a);

        Random rnd = ThreadLocalRandom.current();
        // Do the tests
        for (int i=0; i < rounds; i++) {
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

    public static BigInteger generateFrom(BigInteger q)
    {
        BigInteger p = new BigInteger(q.bitLength(),0,ThreadLocalRandom.current());
        //if 4*q+2>p   generate new
        while (BigInteger.valueOf(4).multiply(q).add(BigInteger.valueOf(2)).compareTo(p)==-1)
        {
            p = new BigInteger(q.bitLength(),0,ThreadLocalRandom.current());
        }
        System.out.println("Generated r = " + p);
        return p.multiply(q).add(BigInteger.ONE);
    }

    public static BigInteger metodMourera(BigInteger q)
    {
        //step 1 generate
        BigInteger p = generateFrom(q);
        int[] primesTo100 = {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97};
        //step 2  Compare with little primes
        for (int elements : primesTo100) {
            //return p If prime
            if (p.mod(BigInteger.valueOf(elements)).compareTo(BigInteger.ZERO) == 0)
                continue;
        }
        if(!rabinMillerTest(p, 100))
            p = generateFrom(q);
        for (int i = 2; i < p.longValue()-1; i++) {
            BigInteger a = BigInteger.valueOf(i);
            if(a.modPow(p.subtract(BigInteger.ONE),p).compareTo(BigInteger.ONE)!=0)
                return p;
        }
        return metodMourera(p);
    }

    public static void main(String[] args) {
        BigInteger q = new BigInteger("314159265359");
        System.out.println("rabinMillerTest(q,5) = " + rabinMillerTest(q, 5));
        BigInteger p = new BigInteger(q.bitLength(),0,ThreadLocalRandom.current()).add(q);
        System.out.println("q = " + q);
        System.out.println("p = " + p);
        System.out.println("p.compareTo(q) = " + p.compareTo(q));
        System.out.println("q.compareTo(p) = " + q.compareTo(p));
        System.out.println("q.compareTo(q) = " + q.compareTo(q));
        System.out.println("q = " + q);
        BigInteger qwe=  metodMourera(q);
        System.out.println("m = " + qwe);
        System.out.println("qwe.isProbablePrime(100) = " + qwe.isProbablePrime(111));
    }
}
