package MathParser.operations;

import java.math.BigDecimal;

/**
 * The type Multiply.
 */
public class Multiply extends Operation {

    /**
     * Instantiates a new Multiply.
     */
    public Multiply() {
        super("*", 1);
    }

    @Override
    public BigDecimal calcWithOperands(BigDecimal leftOperand, BigDecimal rightOperand) {
        return leftOperand.multiply(rightOperand);
    }
}
