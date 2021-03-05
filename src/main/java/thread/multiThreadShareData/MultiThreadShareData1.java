package thread.multiThreadShareData;

/**
 * 多线程访问共享对象和数据的方式(一)
 * 将共享数据封装到另一个对象中，然后这个对象逐一传递给Runable对象，
 * 每个线程对共享数据的操作方式也分配到那个对象的方法上去完成，这样容易实现对该数据进行的各种操作的互斥和通信。
 *
 * @author
 * @data2021/3/517:45
 */
public class MultiThreadShareData1 {

    public static void main(String[] args) {
        final ShareData sharedata = new ShareData();
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    sharedata.inc();
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    sharedata.dec();
                }
            }).start();
        }

    }


    static class ShareData {
        private int count = 10;

        public synchronized void inc() {
            count++;
            System.out.println("线程进行了加操作" + count);
        }

        public synchronized void dec() {
            count--;
            System.out.println("线程进行了减操作" + count);
        }
    }
}
