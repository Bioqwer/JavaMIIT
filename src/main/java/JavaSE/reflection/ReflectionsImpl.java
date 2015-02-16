package JavaSE.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

/**
 * Created by Antony on 13.02.2015.
 */
public class ReflectionsImpl implements Reflections {
    
    @Override
    public Object getFieldValueByName(Object object, String fieldName) throws NoSuchFieldException,NullPointerException {
        if(object==null||fieldName==null)
            throw new NullPointerException();
        else {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            try {
                return field.get(object);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            throw new NullPointerException();
        }
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
