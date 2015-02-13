package JavaSE.reflection;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

/**
 * Created by Antony on 13.02.2015.
 */
public class ReflectionsImpl implements Reflections {
    
    @Override
    public Object getFieldValueByName(Object object, String fieldName) throws NoSuchFieldException {
        return object.getClass().getField(fieldName);
    }

    @Override
    public Set<String> getProtectedMethodNames(Class clazz) {
        return null;
    }

    @Override
    public Set<Method> getAllImplementedMethodsWithSupers(Class clazz) {
        return null;
    }

    @Override
    public List<Class> getExtendsHierarchy(Class clazz) {
        return null;
    }

    @Override
    public Set<Class> getImplementedInterfaces(Class clazz) {
        return null;
    }

    @Override
    public List<Class> getThrownExceptions(Method method) {
        return null;
    }

    @Override
    public String getFooFunctionResultForDefaultConstructedClass() {
        return null;
    }

    @Override
    public String getFooFunctionResultForClass(String constructorParameter, String string, Integer... integers) {
        return null;
    }
}
