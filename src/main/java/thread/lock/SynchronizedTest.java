package thread.lock;

/**
 * @author
 * @data2021/3/519:18
 */
public class SynchronizedTest {
    public static void main(String[] args) {
        SynchronizedTest.init();
    }

    public static void init() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        Outprint.out("hadoop");
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
                        Thread.sleep(1000);
                        Outprint.out("spark");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    static class Outprint {
        public static void out(String str) {
            synchronized (Outprint.class) {
                System.out.println(Thread.currentThread().getName() + "正在操作当前方法");
                for (int i = 0; i < str.length(); i++) {
                    System.out.println(str.charAt(i));
                }
                System.out.println();
            }
        }
    }
}
