package thread.lock;

import java.util.HashMap;
import java.util.Map;

/**
 * 读锁重入
 * 条件:当前线程没有写操作或者写请求，必须持有读线程（不管是否有读请求)
 *
 * @author
 * @data2021/3/519:50
 */
public class ReentrackReadLock implements Runnable {

    Map<Thread, Integer> mapReadLock = new HashMap<>();
    private int write = 0;
    private int writeRequest = 0;

    public Boolean isRead(Thread readThread) {
        return mapReadLock.get(readThread) != null;
    }

    /**
     * 判断读锁的重入的条件是否都满足
     *
     * @param readThread
     * @return
     */
    public Boolean isCanGetReadAccess(Thread readThread) {
        if (write > 0)
            return false;
        if (writeRequest > 0)
            return false;
        if (isRead(readThread))
            return true;
        //第一次时以上条件都不满足
        return true;
    }

    /**
     * 获取线程的总数
     *
     * @param readThread
     * @return
     */
    public int getReadThreadCount(Thread readThread) {
        Integer count = mapReadLock.get(readThread);
        return count == null ? 0 : count.intValue();
    }

    public void readLock() throws InterruptedException {
        Thread readThread = Thread.currentThread();
        System.out.println(isCanGetReadAccess(readThread));
        if (!isCanGetReadAccess(readThread))
            wait();
        mapReadLock.put(readThread, getReadThreadCount(readThread) + 1);
    }

    public void unReadLock() {
        Thread thread = Thread.currentThread();
        if (getReadThreadCount(thread) == 1)
            mapReadLock.remove(thread);
        else
            mapReadLock.put(thread, getReadThreadCount(thread) - 1);
        notifyAll();
    }

    @Override
    public void run() {
        //等于readLock
        Thread readThread = Thread.currentThread();
        if (!isCanGetReadAccess(readThread)) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        mapReadLock.put(readThread, getReadThreadCount(readThread) + 1);
    }

    public static void main(String[] args) {
        Runnable readThread = new ReentrackReadLock();
        Thread thread = new Thread(readThread);
        thread.start();
    }
}
