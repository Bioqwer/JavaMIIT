package javaPreCompile;

import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;
import preCompile.JsCompute;

public class JsComputeTest {

  private final JsCompute jsCompute = new JsCompute();

  @Test
  public void checkPow() throws Exception {
    Map<String, Number> map = new HashMap<>();
    map.put("a", 2);
    map.put("b", 3);

    Number evaluate = jsCompute.evaluate(map, "c = a + Math.pow(b,2)", "c");
    Assert.assertEquals(11, evaluate.intValue());
  }

  @Test
  public void taskFormula() throws Exception {
    Map<String, Number> map = new HashMap<>();
    map.put("a", 2);
    map.put("b", 3);
    map.put("e", 10);
    map.put("d", 10);
    map.put("z", 2);

    String formula = "var res = 0;"
        + "for(var k= 0;k<=3;k++)"
        + " res += (a+b)/(e*d) * Math.pow(k, z)";
    Number evaluate = jsCompute.evaluate(map, formula, "res");
    Assert.assertEquals(0.7, evaluate.doubleValue(), 0);
  }

  @Test
  public void forCheck() throws Exception {
    Map<String, Number> map = new HashMap<>();
    map.put("a", 2);

    String formula = "var res = 0;"
        + "for(var k=0;k<=4;k++){"
        + " res += k"
        + "}";
    Number evaluate = jsCompute.evaluate(map, formula, "res");
    Assert.assertEquals(10, evaluate.intValue());
  }

}