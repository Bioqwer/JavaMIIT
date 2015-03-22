package softwareSecurity;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by Antony on 22.03.2015.
 */
public class Lab1 {

    public static int symbolLejandra(int a, int p){
        System.out.println("{");
        //Convert to BigInteger
        BigInteger bigA =  new BigInteger(Integer.toString(a));
        BigInteger exp = new BigInteger(Integer.toString((p-1)/2));
        BigInteger bigP = new BigInteger(Integer.toString(p));
        //Use Modular exponentiation
        int res = bigA.modPow(exp, bigP).intValue();
        System.out.println("\t"+bigA+"^"+exp+" mod "+ bigP+ " = "+res );
        if(res>(p-1)/2)
            res=res - p;
        System.out.println("\t(" + a + "/" + p + ") = " + res);
        System.out.println("}");
        return res;
    }

    public static int gausLemma(int a, int p)
    {
        ArrayList<Integer> ints = new ArrayList<>();
        int res = 0;
        for (int i = 0; i < (p - 1) / 2; i++) {
            ints.add(a * (i + 1));
            ints.set(i,ints.get(i)%p);
            if(ints.get(i)>(p-1)/2)
                ints.set(i,ints.get(i)-p);
            if(ints.get(i)<0)
                res++;
        }
        if (res%2!=0)
            return -1;
        else
            return 1;
    }


    public static void main(String[] args) {
        symbolLejandra(563, 1433);
        symbolLejandra(118, 1433);
        System.out.println("gausLemma(563, 1433) = " + gausLemma(563, 1433));
        System.out.println("gausLemma(118,1433) = " + gausLemma(118, 1433));
    }
}
