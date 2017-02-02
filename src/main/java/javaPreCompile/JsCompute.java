package javaPreCompile;

import java.util.HashMap;
import java.util.Map;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class JsCompute implements Compute<String, String> {


  private static ScriptEngineManager manager = new ScriptEngineManager();
  private ScriptEngine engine = manager.getEngineByName("JavaScript");


  @Override
  public String evaluate(Map<String, String> variables, String formula) {
    StringBuilder stringBuilder = new StringBuilder();
    variables.forEach((name, value) -> {
      stringBuilder.append(String.format("var %s = %s;%s", name, value, System.lineSeparator()));
    });
    Invocable inv = (Invocable) engine;
    // call function from script file
    Object result = null;
    try {
      engine.eval(stringBuilder.toString());
      Object eval = engine.eval("function compute() { "
          + formula
          + "}");
      result = inv.invokeFunction("compute");
    } catch (ScriptException | NoSuchMethodException e) {
      e.printStackTrace();
    }

    return (String) result;
  }

  public static void main(String[] args) {
    int i = 2147483584;
    System.out.println((int)(float)i);
    System.out.println(Integer.MAX_VALUE);

    JsCompute jsCompute = new JsCompute();
    Map<String, String> map = new HashMap<>();
    jsCompute.evaluate(map,"res");


  }
}
