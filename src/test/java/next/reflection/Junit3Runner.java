package next.reflection;

import com.google.common.base.Stopwatch;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class Junit3Runner {

    private static final Logger log = LoggerFactory.getLogger(Junit3Runner.class);

    @Test
    @DisplayName("Question 클래스의 모든 필드, 생성자, 메소드에 대한 정보를 출력한다.")
    public void runner() throws Exception {
        Class clazz = Junit3Test.class;
        Method[] methods = clazz.getDeclaredMethods();
        for(Method m : methods){
            if(m.getName().startsWith("test")){
                m.invoke(clazz.newInstance());
            }
        }
    }


    @Test
    @DisplayName("ElapsedTime 어노테이션 존재시 수행시간을 측정한다")
    public void time() throws Exception{
        Class clazz = Junit3Test.class;
        Method[] methods = clazz.getDeclaredMethods();
        for(Method m : methods){
            if(m.isAnnotationPresent(ElapsedTime.class)){
                Stopwatch watch = Stopwatch.createStarted();
                m.invoke(clazz.newInstance());
                watch.stop();
                log.debug("Method {} have ran with {} nanoseconds elapsed time", m.getName(), watch.elapsed(TimeUnit.NANOSECONDS));
            }
        }
    }
}
