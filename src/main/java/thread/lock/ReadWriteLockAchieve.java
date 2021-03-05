package thread.lock;

/**
 * 读写锁的简单实现
 * 读写锁：读读共存
 * 读写不能共存  写不能共存
 *
 * @author
 * @data2021/3/519:45
 */
public class ReadWriteLockAchieve {
    private int writeRequest = 0;
    private int read = 0;
    private int write = 0;

    public void readLock() throws InterruptedException {
        //判断是否有写操作或写请求
        if (write > 0 || writeRequest > 0) {
            wait();
        }
        read++;
    }

    public void unreadLock() {
        read--;
        notifyAll();
    }

    public void writeQuest() throws InterruptedException {
        //有多少线程请求写操作并无关系
        writeRequest++;
        if (read > 0 || write > 0) {
            wait();
        }
        writeRequest--;
        write++;
    }

    public void unwriteLock() {
        write--;
        notifyAll();
    }
}
