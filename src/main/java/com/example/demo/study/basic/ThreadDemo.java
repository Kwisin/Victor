package com.example.demo.study.basic;


import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadDemo {

    private volatile int val = 0;
    private volatile int val1 = 0;

    public void addVal() {
        synchronized (this) {
            val++;
        }
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {

        }
        val1++;
    }

    public static void main(String[] args) {
//        ReentrantLock reentrantLock = new ReentrantLock();
//
        LockSupport.park();
//
//
        ConcurrentHashMap<String, Integer> integerIntegerConcurrentHashMap = new ConcurrentHashMap<>();
//
//        Hashtable<Integer, Integer> integerIntegerHashtable = new Hashtable<>();
//
//
//        // AbstractQueuedSynchronizer
//        ReentrantLock reentrantLock = new ReentrantLock();
//        Semaphore semaphore = new Semaphore(10, true);
        CountDownLatch countDownLatch = new CountDownLatch(10);

//        Runnable checkedRunnable = () -> {
//            try {
//                Thread.sleep(1000);
//               integerIntegerConcurrentHashMap.put()
//                countDownLatch.countDown();
//            } catch (InterruptedException ex) {
//                System.out.println(ex.getStackTrace());
//            }finally {
//                try {
//                    countDownLatch.await();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//            System.out.println(System.nanoTime());
//
//        };

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                long val = System.nanoTime();
                System.out.println(val);
                integerIntegerConcurrentHashMap.put(Thread.currentThread().getName(), 1);
                countDownLatch.countDown();
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i));

            thread.start();
        }
        try {
            countDownLatch.await();
            System.out.println(integerIntegerConcurrentHashMap.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



//        //里面的Worker也是用这个工具
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 20, 100, TimeUnit.NANOSECONDS, new ArrayBlockingQueue<>(10));
//        for (int i = 0; i < 500; i++) {
//            int finalI = i;
//
//            try {
//                threadPoolExecutor.execute(() -> {
//                    System.out.println(finalI);
//                    try {
//                        Thread.sleep(10);
//                    } catch (InterruptedException e) {
//                    }
//                });
//            } catch (RejectedExecutionException e) {
//                boolean terminated = threadPoolExecutor.isTerminated();
//                System.out.println(terminated);
//            }
//
//        }


    }


    private void dirtyData() {
        ThreadDemo demo = new ThreadDemo();

        Runnable runnable2 = () -> {

            for (int i = 0; i < 1000; i++) {
                demo.addVal();
            }

        };

        Thread t1 = new Thread(runnable2, "t1");
        Thread t2 = new Thread(runnable2, "t2");

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (Exception e) {

        }
        System.out.println(demo.val);
        System.out.println(demo.val1);


        ReentrantLock reentrantLock = new ReentrantLock();


        LockSupport.park();
    }


    public void deadLock() {
        ThreadDemo threadDemo1 = new ThreadDemo();
        ThreadDemo threadDemo2 = new ThreadDemo();

        Thread thread = new Thread(() -> {
            synchronized (threadDemo1) {
                synchronized (threadDemo2) {

                }
            }
        });
        thread.start();

        Thread thread1 = new Thread(() -> {
            synchronized (threadDemo2) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {

                }
                synchronized (threadDemo1) {

                }
            }
        });
        thread1.start();


        try {
            thread.join();
            thread1.join();
        } catch (Exception e) {

        }

        System.out.println("111");

    }


    public void testCountDownLatch() {

    }

    public void testSemaphore() {

    }

}
