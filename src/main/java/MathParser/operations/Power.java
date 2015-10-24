package MathParser.operations;

import java.math.BigDecimal;

/**
 * The type Power.
 */
public class Power extends Operation {

    /**
     * Instantiates a new Power.
     */
    public Power() {
        super("^",1);
    }

    @Override
    public BigDecimal calcWithOperands(BigDecimal leftOperand, BigDecimal rightOperand) {
        return BigDecimal.valueOf(Math.pow(leftOperand.doubleValue(), rightOperand.doubleValue()));
    }
}
