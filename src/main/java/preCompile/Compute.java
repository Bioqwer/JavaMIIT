package preCompile;

import java.util.Map;

public interface Compute<V, R> {

  public R evaluate(Map<String, V> variables, String formula, String varResult);

}
