package org.maoxiaoxu.appliaction.auto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadPoolExecutor;
@Component
public class ThreadAutoService {

    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;

    public ThreadPoolExecutor getThreadPoolExecutor(){
        return threadPoolExecutor;
    }
}
