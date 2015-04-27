package MathParser.operations;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Antony on 27.04.2015.
 */
public abstract class Operation {

    public static final Map<String, Operation> OPERATIONS;

    static {
        OPERATIONS = new HashMap<>();
        OPERATIONS.put(new Multiply().getToken(), new Multiply());
        OPERATIONS.put(new Divide().getToken(),  new Divide());
        OPERATIONS.put(new Plus().getToken(),  new Plus());
        OPERATIONS.put(new Minus().getToken(),  new Minus());
        OPERATIONS.put(new Power().getToken(),  new Power());
    }

    private String token;
    private int priority;

    public int getPriority() {
        return priority;
    }

    protected void setPriority(int priority) {
        this.priority = priority;
    }

    public static Map<String, Integer> getOperations() {
        HashMap<String, Integer> result = new HashMap<String, Integer>();
        for (Map.Entry<String, Operation> entry : OPERATIONS.entrySet())
        {
            result.put(entry.getKey(),entry.getValue().getPriority());
        }
        return result;
    }

    public String getToken() {
        return token;
    }

    protected void setToken(String token) {
        this.token = token;
    }

    public static BigDecimal doCalc(String token,BigDecimal operand1, BigDecimal operand2 ){
        Operation operation = OPERATIONS.get(token);
        return operation.calcWithOperands(operand1, operand2);
    }

    public abstract BigDecimal calcWithOperands(BigDecimal operand1, BigDecimal operand2);

}
