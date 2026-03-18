package next.reflection;

import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class Junit4Runner {
    @Test
    public void run() throws Exception {
        Class clazz = Junit4Test.class;

        Method[] methods = clazz.getDeclaredMethods();

        for(Method m : methods){
//            for(Annotation a : m.getAnnotations()){
//                if(a.toString().contains("MyTest")){
//                    m.invoke(clazz.newInstance());
//                }
//            }

            if(m.isAnnotationPresent(MyTest.class)){
                m.invoke(clazz.newInstance());
            }
        }

    }
}


