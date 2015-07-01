package JavaSE.pingPongApp;

import java.math.BigInteger;

public class NumberKeeper {

    private static NumberKeeper ourInstance = new NumberKeeper();

    public static NumberKeeper getInstance() {
        return ourInstance;
    }

    private BigInteger pong = BigInteger.ZERO;

    public BigInteger getPong() {
        addPong();
        return pong;
    }

    public void addPong() {
        pong = pong.add(BigInteger.ONE);
    }

    private NumberKeeper() {

    }
}
