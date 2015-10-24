package MathParser.operations;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Operation.
 */
public abstract class Operation {

    /**
     * The constant OPERATIONS.
     */
    public static final Map<String, Operation> OPERATIONS;

    static {
        OPERATIONS = new HashMap<>();
        OPERATIONS.put(new Multiply().getToken(), new Multiply());
        OPERATIONS.put(new Divide().getToken(),  new Divide());
        OPERATIONS.put(new Plus().getToken(),  new Plus());
        OPERATIONS.put(new Minus().getToken(),  new Minus());
        OPERATIONS.put(new Power().getToken(),  new Power());
    }

    /**
     * Instantiates a new Operation.
     *
     * @param token    the token
     * @param priority the priority
     */
    public Operation(String token, int priority) {
        this.token = token;
        this.priority = priority;
    }

    private String token;
    private int priority;

    /**
     * Gets priority.
     *
     * @return the priority
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Sets priority.
     *
     * @param priority the priority
     */
    protected void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * Gets operations.
     *
     * @return the operations
     */
    public static Map<String, Integer> getOperations() {
        HashMap<String, Integer> result = new HashMap<>();
        for (Map.Entry<String, Operation> entry : OPERATIONS.entrySet())
        {
            result.put(entry.getKey(),entry.getValue().getPriority());
        }
        return result;
    }

    /**
     * Gets token.
     *
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets token.
     *
     * @param token the token
     */
    protected void setToken(String token) {
        this.token = token;
    }

    /**
     * Do calc big decimal.
     *
     * @param token    the token
     * @param operand1 the operand 1
     * @param operand2 the operand 2
     * @return the big decimal
     */
    public static BigDecimal doCalc(String token,BigDecimal operand1, BigDecimal operand2 ){
        Operation operation = OPERATIONS.get(token);
        return operation.calcWithOperands(operand1, operand2);
    }

    /**
     * Calc with operands big decimal.
     *
     * @param leftOperand  the left operand
     * @param rightOperand the right operand
     * @return the big decimal
     */
    public abstract BigDecimal calcWithOperands(BigDecimal leftOperand, BigDecimal rightOperand);

}
