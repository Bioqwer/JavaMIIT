package softwareSecurity;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Simple Rsa implementation.
 */
public class RSA {

    private static Map<Character, Integer> keysByChar = new HashMap<>();
    private static Map<Integer, Character> keysByInteger = new HashMap<>();

    static {
        String s = "ЙЦУКЕНГШЩЗХФЫВАПРОЛДЖЭЁЯЧСМИТЬБЮQWERTYUIOPASDFGHJKLZXCVBNM";
        for (int i = 0; i < s.length(); i++) {
            keysByChar.put(s.charAt(i), i);
            keysByInteger.put(i, s.charAt(i));
        }

        System.out.println("keysByChar = " + keysByChar);
        System.out.println("keysByInteger = " + keysByInteger);
    }

    /**
     * Translit int.
     *
     * @param simbol the simbol
     * @return the int
     */
    public static int translit(char simbol) {
        String ch = String.valueOf(simbol);
        ch = ch.toUpperCase();
        simbol = ch.charAt(0);
        return keysByChar.get(simbol);
    }

    /**
     * Translit char.
     *
     * @param digit the digit
     * @return the char
     */
    public static char translit(int digit) {
        return keysByInteger.get(digit);
    }

    private static int[] strToInt(String str) {
        int[] result = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            result[i] = translit(str.charAt(i));
        }
        return result;
    }

    /**
     * Generate e long.
     *
     * @param min the min
     * @param max the max
     * @param fi  the fi
     * @return the long
     */
    public static long generateE(long min,
                                 long max,
                                 BigInteger fi) {
        long r = (new java.util.Random().nextLong() % (max - min)) + min;
        boolean found = false;
        while (!found) {
            if (r >= min && r <= max && NOD(fi, BigInteger.valueOf(r))) {
                found = true;
            } else {
                r = (new java.util.Random().nextLong() % (max - min)) + min;
            }
        }
        return r;
    }

    /**
     * Nod boolean.
     *
     * @param a the a
     * @param b the b
     * @return the boolean
     */
    public static boolean NOD(BigInteger a,
                              BigInteger b) {
        BigInteger r = BigInteger.TEN;

        while (r.compareTo(BigInteger.ONE) != 0) {
            r = a.remainder(b);
            if (r.compareTo(BigInteger.ZERO) == 0) {
                return false;
            }
            a = b;
            b = r;
        }
        return true;
    }

    /**
     * Evklid big integer.
     *
     * @param a the a
     * @param b the b
     * @return the big integer
     */
    public static BigInteger Evklid(BigInteger a,
                                    BigInteger b) {

        BigInteger[][] E = new BigInteger[2][2];
        for (int i = 0; i < E.length; i++)
            for (int j = 0; j < E[i].length; j++) {
                if (i == j) E[i][j] = BigInteger.ONE;
                else E[i][j] = BigInteger.ZERO;
            }

        BigInteger q = a.divide(b);
        BigInteger r = a.remainder(b);
        BigInteger[][] A = new BigInteger[2][2];
        BigInteger[][] A1 = new BigInteger[2][2];

        A[0][0] = BigInteger.ZERO;
        A[0][1] = BigInteger.ONE;
        A[1][0] = BigInteger.ZERO;
        A[1][1] = q.negate();

        while (r.compareTo(BigInteger.ZERO) != 0) {
            q = a.divide(b);
            r = a.remainder(b);
            if (r.compareTo(BigInteger.ZERO) == 0) {
                //System.out.println("Arrays.deepToString(A1) = " + Arrays.deepToString(A1));
                return A1[1][1];
            } else {
                for (int i = 0; i < E.length; i++)
                    for (int j = 0; j < E[i].length; j++) {
                        A1[i][j] = BigInteger.ZERO;
                    }
                A[0][0] = BigInteger.ZERO;
                A[0][1] = BigInteger.ONE;
                A[1][0] = BigInteger.ONE;
                A[1][1] = q.negate();
                for (int i = 0; i < E.length; i++)
                    for (int j = 0; j < E[i].length; j++) {
                        for (int k = 0; k < E.length; k++) {
                            A1[i][j] = A1[i][j].add(E[i][k].multiply(A[k][j]));
                        }
                    }
                E[0][0] = A1[0][0];
                E[0][1] = A1[0][1];
                E[1][0] = A1[1][0];
                E[1][1] = A1[1][1];
                a = b;
                b = r;
            }
        }
        return A1[1][1];
    }

    /**
     * Write x big integer.
     *
     * @param word the word
     * @return the big integer
     */
    public static BigInteger writeX(String word) {
        word = word.toUpperCase();
        byte[] a = new byte[word.length()];
        for (int i = 0; i < a.length; i++) {
            a[i] = (byte) translit(word.charAt(i));
        }
        BigInteger x = BigInteger.ZERO;
        BigInteger y;
        int j = 2 * a.length - 2;
        for (int i = 0; i < a.length; i++) {
            y = BigInteger.valueOf(a[i]);
            y = y.multiply(BigInteger.valueOf(10).pow(j));
            j = j - 2;
            x = x.add(y);
        }
        return x;
    }

    /**
     * Dewrite x char [ ].
     *
     * @param a the a
     * @param n the n
     * @return the char [ ]
     */
    public static char[] dewriteX(BigInteger a,
                                  int n) {
        int x[] = new int[n];
        int xobr[] = new int[n];
        BigInteger y;
        for (int i = 0; i < x.length; i++) {
            y = a.divide(BigInteger.valueOf(100));
            x[i] = a.subtract(y.multiply(BigInteger.valueOf(100))).intValueExact();
            a = a.subtract(BigInteger.valueOf(x[i])).divide(BigInteger.valueOf(
                    100));
        }
        for (int i = 0; i < x.length; i++) {
            xobr[i] = x[x.length - 1 - i];
        }
        char[] result = new char[xobr.length];
        for (int i = 0; i < xobr.length; i++) {
            result[i] = translit(xobr[i]);
        }
        return result;
    }

    /**
     * Algoritm.
     *
     * @param word the word
     */
    public static String algoritm(String word) {
        int[] a = strToInt(word);
        System.out.println("word = " + word);
        System.out.println("a = " + Arrays.toString(a));

        BigInteger x;
        x = writeX(word);
        System.out.println("x = " + writeX(word));

        BigInteger q = new BigInteger("77733343");
        BigInteger p = new BigInteger("98764579");

        BigInteger Qminus1 = q.subtract(BigInteger.ONE);
        BigInteger Pminus1 = p.subtract(BigInteger.ONE);
        System.out.println("pq = " + p.multiply(q));
        System.out.println("(p-1)(q-1) = " + Qminus1.multiply(Pminus1));

        BigInteger e = BigInteger.valueOf(generateE(1000,
                                                    10000,
                                                    Qminus1.multiply(Pminus1)));
        System.out.println("e = " + e);
        BigInteger y = x.modPow(e, p.multiply(q));

        System.out.println("code = " + y);
        BigInteger d = Evklid(Qminus1.multiply(Pminus1), e);
        if (d.compareTo(BigInteger.ZERO) < 0) {
            d = Qminus1.multiply(Pminus1).add(d);
        }

        System.out.println("d = " + d);
        BigInteger dec = y.modPow(d, p.multiply(q));
        System.out.println("decode = " + dec);
        System.out.println("de = " + d.multiply(e).mod(Qminus1.multiply(Pminus1)));

        String result = String.valueOf(dewriteX(dec,
                                                 word.length()));
        System.out.println("dewriteX() = " + result);
        return result;
    }
}