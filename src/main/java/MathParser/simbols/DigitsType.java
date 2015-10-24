package MathParser.simbols;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Digits type.
 */
public abstract class DigitsType {

    /**
     * The constant TYPES.
     */
    public static final Map<String, DigitsType> TYPES;

    static {
        TYPES = new HashMap<>();
        TYPES.put("Rimskie", new Rimskie());
    }

    /**
     * Instantiates a new Digits type.
     */
    public DigitsType() {
    }

    /**
     * Interpret big decimal.
     *
     * @param variable the variable
     * @return the big decimal
     */
    public static BigDecimal interpret(String variable) {
        for (DigitsType simbolsType : TYPES.values())
            if (simbolsType.check(variable))
                return simbolsType.simbolRule(variable);
        return new BigDecimal(variable);
    }

    /**
     * Check boolean.
     *
     * @param variable the variable
     * @return the boolean
     */
    protected abstract boolean check(String variable);

    /**
     * Simbol rule big decimal.
     *
     * @param variable the variable
     * @return the big decimal
     */
    public abstract BigDecimal simbolRule(String variable);

}
