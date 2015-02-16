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
        System.out.println("reflections.getFieldValueByName(secretClass,"+field+") = " + 
                reflections.getFieldValueByName(secretClass, "text"));
        assertEquals(field,reflections.getFieldValueByName(secretClass,"text"));
    }
    
    @Test(expected = NoSuchFieldException.class)
    public void testGetFieldValueByNameNoSuchFieldException() throws Exception {
        String field = "text";
        Reflections.SecretClass secretClass = new Reflections.SecretClass(field);
        System.out.println("secretClass = " + secretClass);
        Reflections reflections = new ReflectionsImpl();
        //Call NoSuchFieldException
        System.out.println("reflections.getFieldValueByName(secretClass,"+field+") = " +
                reflections.getFieldValueByName(secretClass, "noSuchField"));
    }
    
    @Test(expected = NullPointerException.class)
    public void testGetFieldValueByNameNullPointerExceptionByObject() throws Exception {
        String field = "text";
        Reflections.SecretClass secretClass = null;
        System.out.println("secretClass = " + secretClass);
        Reflections reflections = new ReflectionsImpl();
        System.out.println("reflections.getFieldValueByName(secretClass,"+field+") = " +
                reflections.getFieldValueByName(secretClass, "noSuchField"));
        //assertEquals(field,reflections.getFieldValueByName(secretClass,"noSuchField"));
    }

    @Test(expected = NullPointerException.class)
    public void testGetFieldValueByNameNullPointerExceptionByField() throws Exception {
        String field = "text";
        Reflections.SecretClass secretClass = new Reflections.SecretClass(field);
        System.out.println("secretClass = " + secretClass);
        Reflections reflections = new ReflectionsImpl();
        System.out.println("reflections.getFieldValueByName(secretClass,"+field+") = " +
                reflections.getFieldValueByName(secretClass, null));
        //assertEquals(field,reflections.getFieldValueByName(secretClass,"noSuchField"));
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