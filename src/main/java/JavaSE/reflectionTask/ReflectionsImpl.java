package JavaSE.reflectionTask;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by Antony on 13.02.2015.
 */
public class ReflectionsImpl implements Reflections {
    
    @Override
    public Object getFieldValueByName(Object object, String fieldName) throws NoSuchFieldException{
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        Object result = new Object();
        try {
            result = field.get(object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return result;
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
        ArrayList<Class> result = new ArrayList<>();
        Collections.addAll(result,method.getExceptionTypes());
        return result;
    }

    @Override
    public String getFooFunctionResultForDefaultConstructedClass() {
        try {
            Constructor<SecretClass> constructor = SecretClass.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            SecretClass secretClass = constructor.newInstance();
            Method foo = SecretClass.class.getDeclaredMethod("foo");
            foo.setAccessible(true);
            return (String) foo.invoke(secretClass);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getFooFunctionResultForClass(String constructorParameter, String string, Integer... integers) {
        try {
            Constructor<SecretClass> constructor = SecretClass.class.getConstructor(String.class);
            SecretClass secretClass = constructor.newInstance(constructorParameter);
            Method foo = SecretClass.class.getDeclaredMethod("foo",String.class,integers.getClass());
            foo.setAccessible(true);
            return (String) foo.invoke(secretClass,string,integers);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
