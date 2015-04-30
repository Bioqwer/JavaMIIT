package MathParser.simbols;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by Antony on 30.04.2015.
 */
public class Simbol {

    public static final Map<String, Integer> RIMSK_SIMBOLS;

    static {
        RIMSK_SIMBOLS = new HashMap<>();
        RIMSK_SIMBOLS.put("I", 1);
        RIMSK_SIMBOLS.put("V", 5);
        RIMSK_SIMBOLS.put("X", 10);
        RIMSK_SIMBOLS.put("L", 50);
        RIMSK_SIMBOLS.put("C", 100);
        RIMSK_SIMBOLS.put("M", 1000);
    }

    public static BigDecimal interpret(String variable) {
        if (checkForRimskie(variable))
            return rimskSimbolRule(variable);
        return new BigDecimal(variable);
    }

    private static boolean checkForRimskie(String variable) {
        for (int i = 0; i < variable.length(); i++)
            if (RIMSK_SIMBOLS.keySet().contains(variable.substring(i, i + 1)))
                return true;
        return false;
    }

    public static BigDecimal rimskSimbolRule(String variable) {
        int result = 0;
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < variable.length(); i++) {
            stack.push(variable.substring(i, i + 1));
        }
        while (!stack.empty()) {
            int varLast = RIMSK_SIMBOLS.get(stack.pop());
            Stack<Integer> littleStack = new Stack<>();
            if (!stack.empty()) {
                while (varLast > RIMSK_SIMBOLS.get(stack.lastElement())) {
                    littleStack.push(RIMSK_SIMBOLS.get(stack.pop()));
                    if (stack.empty())
                        break;
                }
            }
            result = result + varLast;
            while (!littleStack.empty())
                result = result - littleStack.pop();
        }
        return BigDecimal.valueOf(result);
    }

}
