package JavaSE.reflectionTask;

import org.junit.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;

public class ReflectionsImplTest {

    Reflections reflections = new ReflectionsImpl();

    @Test
    public void testGetFieldValueByName() throws Exception {
        String field = "text";
        Reflections.SecretClass secretClass = new Reflections.SecretClass(field);
        System.out.println("secretClass = " + secretClass);
        System.out.println("reflections.getFieldValueByName(secretClass,"+field+") = " + 
                reflections.getFieldValueByName(secretClass, "text"));
        assertEquals(field,reflections.getFieldValueByName(secretClass,"text"));
    }
    
    @Test(expected = NoSuchFieldException.class)
    public void testGetFieldValueByNameNoSuchFieldException() throws Exception {
        String field = "text";
        Reflections.SecretClass secretClass = new Reflections.SecretClass(field);
        System.out.println("secretClass = " + secretClass);
        //Call NoSuchFieldException
        System.out.println("reflections.getFieldValueByName(secretClass,"+field+") = " +
                reflections.getFieldValueByName(secretClass, "noSuchField"));
    }
    
    @Test(expected = NullPointerException.class)
    public void testGetFieldValueByNameNullPointerExceptionByObject() throws Exception {
        String field = "text";
        Reflections.SecretClass secretClass = null;
        System.out.println("secretClass = " + secretClass);
        System.out.println("reflections.getFieldValueByName(secretClass,"+field+") = " +
                reflections.getFieldValueByName(secretClass, "noSuchField"));
        //assertEquals(field,reflections.getFieldValueByName(secretClass,"noSuchField"));
    }

    @Test(expected = NullPointerException.class)
    public void testGetFieldValueByNameNullPointerExceptionByField() throws Exception {
        String field = "text";
        Reflections.SecretClass secretClass = new Reflections.SecretClass(field);
        System.out.println("secretClass = " + secretClass);
        System.out.println("reflections.getFieldValueByName(secretClass,"+field+") = " +
                reflections.getFieldValueByName(secretClass, null));
        //assertEquals(field,reflections.getFieldValueByName(secretClass,"noSuchField"));
    }
    
    //For Testing
    static class Protect{
        protected static final void method(){}

        @Override
        public String toString() {
            return "Protect{}";
        }
    }
    
    @Test
    public void testGetProtectedMethodNames() throws Exception {
        System.out.println("reflections.getProtectedMethodNames(Protect.class) = " + reflections.getProtectedMethodNames(Protect.class));
        HashSet<String> res = new HashSet<>();
        res.add("method");
        
        assertEquals(res,reflections.getProtectedMethodNames(Protect.class));
    }

    @Test
    public void testGetAllImplementedMethodsWithSupers() throws Exception {
        System.out.println("reflections.getAllImplementedMethodsWithSupers = " + reflections.getAllImplementedMethodsWithSupers(Protect.class));
        HashSet<Method> result = new HashSet<>();
        Collections.addAll(result,Object.class.getDeclaredMethods());
        Collections.addAll(result,Protect.class.getDeclaredMethods());
        
        assertEquals(result,reflections.getAllImplementedMethodsWithSupers(Protect.class));
    }

    @Test
    public void testGetExtendsHierarchy() throws Exception {
        System.out.println("reflections.getExtendsHierarchy(Protect.class) = " + reflections.getExtendsHierarchy(Protect.class));
        ArrayList<Class> expected = new ArrayList<>();
        expected.add(Protect.class);
        expected.add(Object.class);
        
        assertEquals(expected,reflections.getExtendsHierarchy(Protect.class));
    }

    @Test
    public void testGetImplementedInterfaces() throws Exception {
        System.out.println("reflections.getImplementedInterfaces(reflections.getClass()) = " + reflections.getImplementedInterfaces(reflections.getClass()));
    }

    @Test
    public void testGetThrownExceptions() throws Exception {

    }

    @Test
    public void testGetFooFunctionResultForDefaultConstructedClass() throws Exception {

    }

    @Test
    public void testGetFooFunctionResultForClass() throws Exception {

    }
}