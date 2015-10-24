package MathParser.operations;

import java.math.BigDecimal;

/**
 * The type Minus.
 */
public class Minus extends Operation {

    /**
     * Instantiates a new Minus.
     */
    public Minus() {
        super("-",2);
    }

    @Override
    public BigDecimal calcWithOperands(BigDecimal leftOperand, BigDecimal rightOperand) {
        return leftOperand.subtract(rightOperand);
    }
}
