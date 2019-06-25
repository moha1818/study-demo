package com.moha.demo.controller;

import com.moha.demo.threadpool.TaskT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class MainFunction {

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    public void filesMng(String path, String fileName) {
        this.threadPoolTaskExecutor.execute(new CutFilesThread(path,fileName));
    }

    private class CutFilesThread implements Runnable {
        private String path;
        private String fileName;

        private CutFilesThread(String path, String fileName) {
            super();
            this.path = path;
            this.fileName = fileName;
        }

        @Override
        public void run() {
            System.out.println("barry... run...");
        }
    }
}
