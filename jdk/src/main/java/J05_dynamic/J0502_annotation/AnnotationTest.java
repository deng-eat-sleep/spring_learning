package J05_dynamic.J0502_annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class AnnotationTest {

    public class SimpleFormatter{

    }
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Lable{
        String value() default "";
    }


    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Format{
        String pattern() default "yyyy-MM-dd HH:mm:ss";
        String timezone() default "GMT+8";
    }

    public static class Student{
        @Lable("姓名")
        String name;

        public Student(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {
        Student student = new Student("test");
    }
}
