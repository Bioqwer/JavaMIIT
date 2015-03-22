package softwareSecurity;

import java.math.BigInteger;

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


    public static void main(String[] args) {
        symbolLejandra(563, 1433);
        symbolLejandra(118, 1433);
    }
}
