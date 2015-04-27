package MathParser.operations;

import java.math.BigDecimal;

/**
 * Created by Antony on 27.04.2015.
 */
public class Power extends Operation {

    public Power() {
        setPriority(1);
        setToken("^");
    }

    @Override
    public BigDecimal calcWithOperands(BigDecimal operand1, BigDecimal operand2) {
        return BigDecimal.valueOf(Math.pow(operand1.doubleValue(), operand2.doubleValue()));
    }
}
