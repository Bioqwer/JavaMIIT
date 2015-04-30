package MathParser;

import org.junit.Test;

import java.math.BigDecimal;

import static MathParser.Expression.calculateExpression;
import static org.junit.Assert.assertEquals;

/**
 * Created by Antony on 27.04.2015.
 */
public class ExpressionTest {

    @Test
    public void testCalculateExpression() throws Exception {
        String expression = "3 + 4 * 2 / (1 - 5) + 2";
        Expression.aboutExp(expression);
        assertEquals(BigDecimal.valueOf(3), calculateExpression(expression));

        expression = "3 + 4 *(( 2 / (1 - 5))) + 2.5";
        Expression.aboutExp(expression);
        assertEquals(BigDecimal.valueOf(3.5), calculateExpression(expression));

        expression = "2";
        Expression.aboutExp(expression);
        assertEquals(BigDecimal.valueOf(2), calculateExpression(expression));

        expression = "200+123";
        Expression.aboutExp(expression);
        assertEquals(BigDecimal.valueOf(323), calculateExpression(expression));

        expression = "2^3";
        Expression.aboutExp(expression);
        assertEquals(BigDecimal.valueOf(8.0), calculateExpression(expression));
    }

    @Test
    public void testRimskie() throws Exception {
        String expression = "X+X";
        Expression.aboutExp(expression);
        assertEquals(BigDecimal.valueOf(20), calculateExpression(expression));

        expression = "X/X+200";
        Expression.aboutExp(expression);
        assertEquals(BigDecimal.valueOf(201), calculateExpression(expression));

    }
}