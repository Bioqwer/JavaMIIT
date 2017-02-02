package javaPreCompile;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Stack {

  public static void main(String[] args) throws ScriptException, NoSuchMethodException {

    ScriptEngineManager manager = new ScriptEngineManager();
    ScriptEngine engine = manager.getEngineByName("JavaScript");

    // JavaScript code in a String
    String script1 = (String) "function hello(name) {print ('Hello, ' + name);}";
    String script2 = (String) "function getValue(a,b) { if (a===\"Number\") return 1; else return b;}";
    // evaluate script
    engine.eval(script1);
    engine.eval(script2);

    Invocable inv = (Invocable) engine;

    inv.invokeFunction("hello", "Scripting!!");  //This one works.
    Object res = inv.invokeFunction("getValue", "Number", "asd");
    System.out.println(res);
  }

}
