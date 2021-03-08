package thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author
 * @data2021/3/819:23
 */
public class ConditionTest {
    public static void main(String[] args) {
        final Commons commons = new Commons();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 50; i++) {
                    commons.sub(i);
                }
            }
        }).start();

        for (int i = 0; i <= 50; i++) {
            commons.main(i);
        }
    }
}

class Commons {
    private boolean sub = true;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void sub(int i) {
        lock.lock();
        try {
            while (!sub) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int j = 0; j <= 10; j++) {
                System.out.println("sub   " + j + "  loop of  " + i);
            }
            sub = false;
            condition.signal();

        } finally {
            lock.unlock();
        }
    }

    public void main(int i) {
        lock.lock();
        try {
            while (sub) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int j = 0; j <= 10; j++) {
                System.out.println("main   " + j + "  loop of  " + i);
            }
            sub = true;
            condition.signal();

        } finally {
            lock.unlock();
        }
    }
}
