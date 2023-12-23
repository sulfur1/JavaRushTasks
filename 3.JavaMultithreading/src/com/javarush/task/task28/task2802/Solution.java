package com.javarush.task.task28.task2802;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* 
Пишем свою ThreadFactory
*/

public class Solution {

    public static class AmigoThreadFactory implements ThreadFactory {
        private final AtomicInteger atomicNumberOfThread = new AtomicInteger(1);
        private static final AtomicInteger atomicNumberOfFactory = new AtomicInteger(1);

        private final String name;

        private final ThreadGroup threadGroup;
        public AmigoThreadFactory() {
            threadGroup = Thread.currentThread().getThreadGroup();
            StringBuilder builder = new StringBuilder(threadGroup.getName());
            builder.append("-pool-")
                    .append(atomicNumberOfFactory.getAndIncrement())
                    .append("-thread-");
            name = builder.toString();
        }

        @Override
        public Thread newThread(Runnable r) {

            Thread thread = new Thread(threadGroup, r, name + atomicNumberOfThread.getAndIncrement());

            thread.setDaemon(false);
            thread.setPriority(Thread.NORM_PRIORITY);


            return thread;
        }
    }

    public static void main(String[] args) {
        class EmulatorThreadFactoryTask implements Runnable {
            @Override
            public void run() {
                emulateThreadFactory();
            }
        }

        ThreadGroup group = new ThreadGroup("firstGroup");
        Thread thread = new Thread(group, new EmulatorThreadFactoryTask());

        ThreadGroup group2 = new ThreadGroup("secondGroup");
        Thread thread2 = new Thread(group2, new EmulatorThreadFactoryTask());

        thread.start();
        thread2.start();
    }

    private static void emulateThreadFactory() {
        AmigoThreadFactory factory = new AmigoThreadFactory();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        };
        factory.newThread(r).start();
        factory.newThread(r).start();
        factory.newThread(r).start();
    }
}
