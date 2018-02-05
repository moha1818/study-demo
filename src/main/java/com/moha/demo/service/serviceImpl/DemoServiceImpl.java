package com.moha.demo.service.serviceImpl;

import com.moha.demo.service.DemoService;
import com.moha.demo.threadpool.StartTaskThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class DemoServiceImpl implements DemoService{

    private static int produceTaskSleepTime = 10;

    private static int produceTaskMaxNumber = 1000;

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    @Autowired
    private MongoTemplate mongoTemplate;

    public ThreadPoolTaskExecutor getThreadPoolTaskExecutor() {
        return threadPoolTaskExecutor;
    }

    public void setThreadPoolTaskExecutor(
            ThreadPoolTaskExecutor threadPoolTaskExecutor) {
        this.threadPoolTaskExecutor = threadPoolTaskExecutor;
    }

    public void testThreadPoolExecutor(List<String> data) {
        Iterator iterList = data.iterator();
        int i = 0;
        while (iterList.hasNext()){
            String email = (String) iterList.next();
            new Thread(new StartTaskThread(threadPoolTaskExecutor, email,i,mongoTemplate)).start();
            //threadPoolTaskExecutor.execute(new StartTaskThread(threadPoolTaskExecutor, email,i,mongoTemplate));
            i++;
        }

    }

    @Override
    public void ser(List<String> data){
        testThreadPoolExecutor(data);
    }
}
