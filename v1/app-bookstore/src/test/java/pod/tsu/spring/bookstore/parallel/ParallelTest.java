package pod.tsu.spring.bookstore.parallel;

import org.instancio.Random;
import org.instancio.support.DefaultRandom;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Parallel")
class ParallelTest {

    private final String startMsg = "%s - %s start.";
    private final String endMsg = "%s - %s end.";
    private final Random random = new DefaultRandom();

    @Test
    public void firstTest_runs() throws InterruptedException{
        System.out.println(String.format(startMsg, Thread.currentThread().getName(), "first"));
        Thread.sleep(random.longRange(100, 600));
        System.out.println(String.format(endMsg, Thread.currentThread().getName(), "first"));
    }

    @Test
    public void secondTest_runs() throws InterruptedException{
        System.out.println(String.format(startMsg, Thread.currentThread().getName(), "second"));
        Thread.sleep(random.longRange(100, 600));
        System.out.println(String.format(endMsg, Thread.currentThread().getName(), "second"));
    }

    @Test
    public void thirdTest_runs() throws InterruptedException{
        System.out.println(String.format(startMsg, Thread.currentThread().getName(), "third"));
        Thread.sleep(random.longRange(100, 600));
        System.out.println(String.format(endMsg, Thread.currentThread().getName(), "third"));
    }

}