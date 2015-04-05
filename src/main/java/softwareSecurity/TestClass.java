package softwareSecurity;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Antony on 05.04.2015.
 */
public class TestClass
{
    public static long randLong(long min, long max) {
        return (new java.util.Random().nextLong() % (max - min)) + min;
    }

    public static void main(String[] args) {
        BigInteger test = new BigInteger("314159265359");
        long a = 314159265359L;
        a=a+1;
        System.out.println("a = " + a);
        a=a/2;
        System.out.println("a = " + a);
        Random random = new Random(a);
        random.nextLong();
        System.out.println("random " + Math.random());
        System.out.println("a*a = " + a * a);
        System.out.println("randLong(a, 4*a+2) = " + randLong(a, 4*a+2));
        String b=Long.toBinaryString(a);
        System.out.println("b = " + b);
        System.out.println("b.chars() = " + Arrays.toString(b.chars().toArray()));
        byte binary[] = new byte[b.chars().toArray().length];
        for (int i = b.chars().toArray().length - 1; i >= 0; i--) {
            if(b.charAt( b.chars().toArray().length - 1-i)==48)
                binary[i] = 0;
            else
                binary[i] =1;
        }
        System.out.println("binary = " + Arrays.toString(binary));
    }
}
