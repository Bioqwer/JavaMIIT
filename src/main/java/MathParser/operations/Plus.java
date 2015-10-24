package MathParser.operations;

import java.math.BigDecimal;

/**
 * The type Plus.
 */
public class Plus extends Operation {

    /**
     * Instantiates a new Plus.
     */
    public Plus() {
        super("+",2);
    }


    @Override
    public BigDecimal calcWithOperands(BigDecimal leftOperand, BigDecimal rightOperand) {
        return leftOperand.add(rightOperand);
    }
}
