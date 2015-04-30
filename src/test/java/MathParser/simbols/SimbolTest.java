package MathParser.simbols;

import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Antony on 30.04.2015.
 */
public class SimbolTest {

    @Test
    public void testInterpret() throws Exception {
        Simbol.rimskSimbolRule("LXIXIIIII");
        System.out.println(Simbol.rimskSimbolRule("IXIII"));
        assertEquals(new BigDecimal("69"), Simbol.rimskSimbolRule("LXIX"));
        assertEquals(new BigDecimal("74"), Simbol.rimskSimbolRule("LXIXIIIII"));
        assertEquals(new BigDecimal("74"), Simbol.rimskSimbolRule("LXIXIIIII"));
    }
}