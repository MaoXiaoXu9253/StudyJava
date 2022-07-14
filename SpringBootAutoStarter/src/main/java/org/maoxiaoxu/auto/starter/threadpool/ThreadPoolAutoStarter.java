package org.maoxiaoxu.auto.starter.threadpool;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class ThreadPoolAutoStarter {

    private static final Log logger = LogFactory.getLog(ThreadPoolAutoStarter.class);
    private static final int corePoolSize = 5;
    private static final int maxPoolSize = 15;
    private static final long time = 60L;
    private static final TimeUnit unit = TimeUnit.SECONDS;
    private static final ArrayBlockingQueue<Runnable> handler = new ArrayBlockingQueue<>(maxPoolSize * corePoolSize);

    @Bean
    @ConditionalOnClass(ThreadPoolExecutor.class)
    public ThreadPoolExecutor getBasePool(){
        logger.info("ThreadPoolAutoStarter init");
        return new ThreadPoolExecutor(corePoolSize, maxPoolSize, time, unit, handler);
    }

}
