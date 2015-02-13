package JavaSE.reflection;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReflectionsImplTest {

    @Test
    public void testGetFieldValueByName() throws Exception {
        String field = "text";
        Reflections.SecretClass secretClass = new Reflections.SecretClass(field);
        System.out.println("secretClass = " + secretClass);
        Reflections reflections = new ReflectionsImpl();
        assertEquals(field,reflections.getFieldValueByName(secretClass,"text"));
        
        
    }

    @Test
    public void testGetProtectedMethodNames() throws Exception {

    }

    @Test
    public void testGetAllImplementedMethodsWithSupers() throws Exception {

    }

    @Test
    public void testGetExtendsHierarchy() throws Exception {

    }

    @Test
    public void testGetImplementedInterfaces() throws Exception {

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