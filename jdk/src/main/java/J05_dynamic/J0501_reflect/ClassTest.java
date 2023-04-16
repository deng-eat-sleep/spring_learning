package J05_dynamic.J0501_reflect;

import java.lang.Class;
import java.lang.reflect.Field;

public class ClassTest {

    public void testClass()  {

        Class<Integer> integerClass = int.class;
        integerClass.getName();

        try {
            Class.forName("int");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void testField() throws NoSuchFieldException {
        Class<ClassTest> c = ClassTest.class;
        Field field = c.getField("s");
        field.getModifiers();
    }
}
