package com.company;

import  java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;


public class Douloaders extends Thread{
    private Semaphore semaphore;
    private CountDownLatch xdl;
    private int speedDownload = 15;
    private CountDownLatch cdl;


    public Douloaders(String name, Semaphore semaphore,
                      CountDownLatch countDownLatch,
                      CountDownLatch countDownLatch1) {
        super(name);
        this.cdl = countDownLatch;
        this.semaphore = semaphore;
        this.xdl = countDownLatch1;
        start();
    }

    @Override
    public synchronized void run() {
        try {
            cdl.await();
            semaphore.acquire();

            System.out.println(getName() + " скачивает сервер файла");
            sleep(100 / speedDownload * 100);


            System.out.println(getName() + " успешно скачен файл сервера ");

            xdl.countDown();
            semaphore.release();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
