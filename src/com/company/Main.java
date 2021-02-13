package com.company;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Main {

        public static void main(String[] args) {
            CountDownLatch xdl = new CountDownLatch(1);
            CountDownLatch cdl = new CountDownLatch(15);
            Semaphore semaphore = new Semaphore(5 , true);
            new Uploader(xdl);


            for (int i = 1; i <= 10; i++) {
                new Douloaders("Че то такое " + i, semaphore, xdl, cdl);
            }
            for (int i = 1; i <= 10; i++) {
                new Douloaders("ЗДРАСЬТЕ!!!! " + i, semaphore, xdl, cdl);
            }
            try {
                cdl.await();
                System.out.println("Файл  удалится через секунду");
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

