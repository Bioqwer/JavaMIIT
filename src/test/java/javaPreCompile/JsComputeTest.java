package javaPreCompile;

import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;
import preCompile.JsCompute;

public class JsComputeTest {

  private final JsCompute jsCompute = new JsCompute();

  @Test
  public void evaluate() throws Exception {
    Map<String, String> map = new HashMap<>();
    map.put("a", "2");
    map.put("b", "3");
    map.put("e", "10");
    map.put("d", "10");

    Number evaluate = jsCompute.evaluate(map, "c = a + b^2", "c");
    Assert.assertEquals(7, evaluate.intValue());
  }



}