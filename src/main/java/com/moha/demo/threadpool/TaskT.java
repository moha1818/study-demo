package com.moha.demo.threadpool;



/*<bean id="threadPoolTaskExecutor"
class="org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor">

<!-- 核心线程数，默认为1 -->
<property name="corePoolSize" value="5" />

<!-- 最大线程数，默认为Integer.MAX_VALUE -->
<property name="maxPoolSize" value="10" />

<property name="keepAliveSeconds" value="300" />

<property name="rejectedExecutionHandler">
<bean class="java.util.concurrent.ThreadPoolExecutor$CallerRunsPolicy" />
</property>
</bean>*/

public class TaskT implements Runnable {
    private String path;
    private String fileName;

    public TaskT(String path, String fileName) {
        this.path = path;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        System.out.println("业务数据");
    }
}
