package com.moha.demo.threadpool;


import com.moha.demo.entity.CheckEmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by maozewei on 2017/11/28.
 */
public class StartTaskThread implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(StartTaskThread.class);

    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    private String email;

    private int i;

    private MongoTemplate mongoTemplate;

    private static final String EMAILVALIDATOR = "EmailValidator";
    public StartTaskThread(ThreadPoolTaskExecutor threadPoolTaskExecutor, String email, int i, MongoTemplate mongoTemplate) {
        LOGGER.info("email {}, i {}", email, i);
        this.threadPoolTaskExecutor = threadPoolTaskExecutor;
        this.email = email;
        this.i = i;
        this.mongoTemplate = mongoTemplate;
    }


    @Override
    public synchronized void run() {
        String task = email;
        LOGGER.info("创建任务并提交到线程池中：" + task);
        FutureTask<CheckEmailValidator> futureTask = new FutureTask<CheckEmailValidator>( new ThreadPoolTask(task));
        threadPoolTaskExecutor.execute(futureTask);
        // 在这里可以做别的任何事情
        CheckEmailValidator result = null;
        try {
            // 取得结果，同时设置超时执行时间为1秒。同样可以用future.get()，不设置执行超时时间取得结果
            result = futureTask.get();
        } catch (InterruptedException e) {
            futureTask.cancel(true);
        } catch (ExecutionException e) {
            futureTask.cancel(true);
        } catch (Exception e) {
            futureTask.cancel(true);
            // 超时后，进行相应处理
        } finally {
            if(result!=null){
                LOGGER.info("task@" + i + ":result=" +result.getEmail() + ", status="+result.getStatus());
            }else{
                LOGGER.info("task@" + i +":result=null,status=null");
            }

            mongoTemplate.insert(result,EMAILVALIDATOR);
        }

    }
}
