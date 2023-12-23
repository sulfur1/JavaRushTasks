package com.javarush.task.task28.task2805;

import java.util.concurrent.atomic.AtomicInteger;

public class MyThread extends Thread {
    private static volatile AtomicInteger CURRENT_PRIORITY = new AtomicInteger(MIN_PRIORITY);

    private synchronized void currentPriority() {
        ThreadGroup tGroup = getThreadGroup();
        int newPriority = CURRENT_PRIORITY.getAndIncrement();
        if (tGroup != null) {
            int threadGroupMaxPriority = tGroup.getMaxPriority();
            if (newPriority > threadGroupMaxPriority) {
                newPriority = threadGroupMaxPriority;
            }
        }
        setPriority(newPriority);

        if (CURRENT_PRIORITY.get() > MAX_PRIORITY) {
            CURRENT_PRIORITY.set(1);
        }
    }
    public MyThread() {
        currentPriority();
    }

    public MyThread(Runnable task) {
        super(task);
        currentPriority();
    }

    public MyThread(ThreadGroup group, Runnable task) {
        super(group, task);
        currentPriority();
    }

    public MyThread(String name) {
        super(name);
        currentPriority();
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
        currentPriority();
    }

    public MyThread(Runnable task, String name) {
        super(task, name);
        currentPriority();
    }

    public MyThread(ThreadGroup group, Runnable task, String name) {
        super(group, task, name);
        currentPriority();
    }

    public MyThread(ThreadGroup group, Runnable task, String name, long stackSize) {
        super(group, task, name, stackSize);
        currentPriority();
    }

}
