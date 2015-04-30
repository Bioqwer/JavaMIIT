package MathParser.simbols;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Antony on 30.04.2015.
 */
public abstract class DigitsType {

    public static final Map<String, DigitsType> TYPES;

    static {
        TYPES = new HashMap<>();
        TYPES.put("Rimskie", new Rimskie());
    }

    public DigitsType() {
    }

    public static BigDecimal interpret(String variable) {
        for (DigitsType simbolsType : TYPES.values())
            if (simbolsType.check(variable))
                return simbolsType.simbolRule(variable);
        return new BigDecimal(variable);
    }

    protected abstract boolean check(String variable);

    public abstract BigDecimal simbolRule(String variable);

}
