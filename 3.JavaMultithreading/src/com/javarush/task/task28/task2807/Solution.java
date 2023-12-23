package com.javarush.task.task28.task2807;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* 
Знакомство с ThreadPoolExecutor
*/

public class Solution {
    public static void main(String[] args) throws InterruptedException {
        // Add your code here
        LinkedBlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<>();

        for (int i = 1; i < 11; i++) {
            final int count = i;
            blockingQueue.add(() -> doExpensiveOperation(count));
        }
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(3, 5, 1000, TimeUnit.MILLISECONDS, blockingQueue);
        poolExecutor.prestartAllCoreThreads();
        poolExecutor.shutdown();
        poolExecutor.awaitTermination(5, TimeUnit.SECONDS);
        /* Example output
pool-1-thread-2, localId=2
pool-1-thread-3, localId=3
pool-1-thread-1, localId=1
pool-1-thread-3, localId=5
pool-1-thread-2, localId=4
pool-1-thread-3, localId=7
pool-1-thread-1, localId=6
pool-1-thread-3, localId=9
pool-1-thread-2, localId=8
pool-1-thread-1, localId=10
         */
    }

    private static void doExpensiveOperation(int localId) {
        System.out.println(Thread.currentThread().getName() + ", localId=" + localId);
    }
}
