package org.maoxiaoxu.appliaction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.maoxiaoxu.appliaction.auto.ThreadAutoService;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@SpringBootTest()
public class MaoXiaoXuApplicationTest {

    private static final Log logger = LogFactory.getLog (MaoXiaoXuApplicationTest.class);
    @Resource
    ThreadAutoService threadAutoService;

    static class TestRunnable implements Runnable {

        private final String thread;
        private static final long min = 1;
        private static final long max = 10;

        public TestRunnable (String name) {
            this.thread = name;
        }

        @Override
        public void run () {
            try {
                long rangeLong = min + (((long) (new Random ().nextDouble () * (max - min))));
                TimeUnit.SECONDS.sleep (rangeLong);
                logger.info (this.thread);
            } catch (InterruptedException e) {
                throw new RuntimeException (e);
            }
        }
    }

    @Test
    public void contextLoads () {
        ThreadPoolExecutor executor = threadAutoService.getThreadPoolExecutor ();

        List<TestRunnable> runnable = Lists.newArrayList (
                new TestRunnable ("111"),
                new TestRunnable ("222"),
                new TestRunnable ("333"),
                new TestRunnable ("444"),
                new TestRunnable ("555"),
                new TestRunnable ("666"),
                new TestRunnable ("777"),
                new TestRunnable ("888"),
                new TestRunnable ("999"),
                new TestRunnable ("000")
        );
        for (int i = 0; i < 21; i++) {
            runnable.add (new TestRunnable (UUID.randomUUID ().toString ()));
        }
        for (TestRunnable testRunnable : runnable) {
            executor.execute (testRunnable);
        }
        while (true){
            if (executor.getActiveCount () == 0) {
                executor.shutdown ();
                break;
            }
        }
    }
}
