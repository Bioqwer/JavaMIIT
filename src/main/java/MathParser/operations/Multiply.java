package MathParser.operations;

import java.math.BigDecimal;

/**
 * Created by Antony on 27.04.2015.
 */
public class Multiply extends Operation {

    /*public Multiply() {
        setToken("*");
        setPriority(1);
    }*/

    public Multiply() {
        super("*", 1);
    }

    @Override
    public BigDecimal calcWithOperands(BigDecimal operand1, BigDecimal operand2) {
        return operand1.multiply(operand2);
    }
}
