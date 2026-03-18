package next.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

import next.optional.User;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.assertj.core.api.Assertions.*;

public class ReflectionTest {
    private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);

    @Test
    @DisplayName("테스트1: 리플렉션을 이용해서 클래스와 메소드의 정보를 정확하게 출력해야 한다.")
    public void showClass() {
        SoftAssertions s = new SoftAssertions();

        Class<Question> clazz = Question.class;
        logger.debug("class Name {}", clazz.getName());
        logger.debug("Declared Constructors:");
        for(Constructor construct : clazz.getDeclaredConstructors()){
            logger.debug("        Name : {}", construct.getName());
            logger.debug("                Access Modifier : {}", construct.getModifiers());
            logger.debug("                Number of Parameters : {}", construct.getParameterCount());
            StringBuilder sb = new StringBuilder();
            for(Class clz : construct.getParameterTypes()){
                sb.append(clz.getName());
                sb.append(" ");
            }
            logger.debug("                Parameters : {}", sb);
        }

        logger.debug("Declared Fields : ");
        for(Field field : clazz.getDeclaredFields()){
            logger.debug("        Name : {}", field.getName());
            logger.debug("                Access Modifier : {}", field.getModifiers());
            logger.debug("                Type : {}" , field.getType());
        }
        logger.debug("Declared Methods : ");
        for(Method method : clazz.getDeclaredMethods()){
            logger.debug("        Name : {}", method.getName());
            logger.debug("                Access Modifier : {}", method.getModifiers());
            logger.debug("                Return Type : {}" , method.getReturnType());
            logger.debug("                Number of Parameters : {}", method.getParameterCount());
            StringBuilder sb = new StringBuilder();
            for(Class clz : method.getParameterTypes()){
                sb.append(clz.getName());
                sb.append(" ");
            }
            logger.debug("                Parameters : {}", sb);

        }
    }

    @Test
    public void constructor() throws Exception {
        Class<Question> clazz = Question.class;
        Constructor[] constructors = clazz.getConstructors();
        for (Constructor constructor : constructors) {
            Class[] parameterTypes = constructor.getParameterTypes();
            logger.debug("paramer length : {}", parameterTypes.length);
            for (Class paramType : parameterTypes) {
                logger.debug("param type : {}", paramType);
            }
        }
    }



    @Test
    public void privateFieldAccess() throws Exception{
        Class<Student> clazz = Student.class;
        Field[] fields = clazz.getDeclaredFields();
        Student modifiedStudent = new Student();
        for(Field f : fields){
            f.setAccessible(true);
            if(f.getName().equals("name")){
                f.set(modifiedStudent,"modifiedName");
            }
            else{
                f.set(modifiedStudent, 999);
            }
        }
        logger.debug("Current name in modifiedStudent Object : {}", modifiedStudent.getName());
        logger.debug("Current name age modifiedStudent Object : {}", modifiedStudent.getAge());
    }




    @Test
    public void userConstructionTest() throws Exception {
        Class clazz = User.class;
        Constructor[] availableConstructors = clazz.getDeclaredConstructors();
        Constructor onlyConstructor = availableConstructors[0];

        User user = (User) onlyConstructor.newInstance("reflectiveName", 999);
        logger.debug("Newly created user name : {}", user.getName());
        logger.debug("Newly created user age : {}", user.getAge());

    }
}
