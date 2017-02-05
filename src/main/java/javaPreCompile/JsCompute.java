package javaPreCompile;

import java.util.Map;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class JsCompute implements Compute<String, Number> {


  private static ScriptEngineManager manager = new ScriptEngineManager();
  private ScriptEngine engine = manager.getEngineByName("JavaScript");

  @Override
  public Number evaluate(Map<String, String> variables, String formula, String varResult) {
    StringBuilder stringBuilder = new StringBuilder();
    variables.forEach((name, value) -> {
      stringBuilder.append(String.format("var %s = %s;%s", name, value, System.lineSeparator()));
    });
    Invocable inv = (Invocable) engine;
    // call function from script file
    Number result = null;
    try {
      engine.eval(stringBuilder.toString());
      Object eval = engine.eval("function compute() { "
          + formula + ";" + System.lineSeparator()
          + "return " + varResult
          + "}");
      result = (Number) inv.invokeFunction("compute");
    } catch (ScriptException | NoSuchMethodException e) {
      e.printStackTrace();
    }

    return result;
  }
}
