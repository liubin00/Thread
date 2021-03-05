package thread.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author
 * @data2021/3/519:25
 */
public class ReadWriteLock {

    public static void main(String[] args) {
        final DataSave ds = new DataSave();
        for (int i = 0; i <= 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    ds.put((long) (Math.random() * 1000));
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    ds.get();
                }
            }).start();
        }
    }

    static class DataSave {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        private long age;

        public void  put(long age) {
            //上写锁
            lock.writeLock().lock();
            System.out.println(Thread.currentThread().getName() + "准备写数据");
            try {
                Thread.sleep((long) Math.random() * 500);
                this.age = age;
                System.out.println(Thread.currentThread().getName() + "写入数据值为: " + age);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //解写锁
                lock.writeLock().unlock();
            }
        }

        public void get() {
            lock.readLock().lock();
            System.out.println(Thread.currentThread().getName() + "准备读取数据");
            try {
                Thread.sleep((long) Math.random() * 500);
                System.out.println(Thread.currentThread().getName() + "读取数据值为: " + age);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.readLock().unlock();
            }
        }
    }


}
