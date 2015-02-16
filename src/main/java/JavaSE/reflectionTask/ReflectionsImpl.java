package JavaSE.reflectionTask;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

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
    public Set<String> getProtectedMethodNames(Class clazz) throws NullPointerException {
        HashSet<String> result = new HashSet<>();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method:methods)
        {
           if(method.toString().contains("protected"))
               result.add(method.getName());
        }
        return result;
    }

    @Override
    public Set<Method> getAllImplementedMethodsWithSupers(Class clazz) {
        HashSet<Method> result = new HashSet<>();
        Class temp = clazz;
        while (temp.getClass().getName().contains(Object.class.getClass().getName()))
        {
            Collections.addAll(result, temp.getDeclaredMethods());
            if(temp.getSuperclass()==null)
                break;
            else
                temp = temp.getSuperclass();
        }
        return result;
    }

    @Override
    public List<Class> getExtendsHierarchy(Class clazz) {
        ArrayList<Class> result = new ArrayList<>();
        for (Class temp = clazz;temp.getClass().getName().contains(Object.class.getClass().getName());temp = temp.getSuperclass())
        {
            result.add(temp);
            if(temp.getSuperclass()==null)
                break;
        }
        return result;
    }

    @Override
    public Set<Class> getImplementedInterfaces(Class clazz) {
        HashSet<Class> result = new HashSet<>();
        Collections.addAll(result, clazz.getInterfaces());
        return result;
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
