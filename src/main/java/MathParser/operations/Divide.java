package MathParser.operations;

import java.math.BigDecimal;

/**
 * The type Divide.
 */
public class Divide extends Operation {

    /**
     * Instantiates a new Divide.
     */
    public Divide() {
        super("/",1);
    }

    @Override
    public BigDecimal calcWithOperands(BigDecimal leftOperand, BigDecimal rightOperand) {
        return leftOperand.divide(rightOperand);
    }
}
