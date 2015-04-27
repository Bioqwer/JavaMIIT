package MathParser.operations;

import java.math.BigDecimal;

/**
 * Created by Antony on 27.04.2015.
 */
public class Plus extends Operation {

    public Plus() {
        setToken("+");
        setPriority(2);
    }


    @Override
    public BigDecimal calcWithOperands(BigDecimal operand1, BigDecimal operand2) {
        return operand1.add(operand2);
    }
}
