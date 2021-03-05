package thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author
 * @data2021/3/519:10
 */
public class LockTest {
    public static void main(String[] args) {
        LockTest.init();
    }

    public static void init() {
        final Outprint outprint = new Outprint();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(2000);
                        outprint.out("hadoop");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(2000);
                        outprint.out("spark");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    static class Outprint {
        Lock lock = new ReentrantLock();

        public void out(String str) {
            lock.lock();
            try {
                for (int i = 0; i < str.length(); i++) {
                    System.out.println(Thread.currentThread().getName() + ":正在运行");
                    System.out.println(str.charAt(i));
                }
                System.out.println();
                //释放锁（如果上面的代码在unlock之前出错,那么锁将不会被释放，所以最好放到finally中
                //lock.unlock();
            } finally {
                //释放锁
                lock.unlock();
            }
        }
    }
}
