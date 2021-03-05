package thread.shareData;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 线程间的数据共享及static线程安全问题
 *
 * @author
 * @data2021/3/516:48
 */
public class ShareData {

    private static int data = 0;

    private static Map<Thread, Integer> threadData = new HashMap<>();

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //局部变量属于线程安全
                    int data = new Random().nextInt();
                    System.out.println("当前线程: " + Thread.currentThread().getName() + "  data:  " + data);
                    threadData.put(Thread.currentThread(), data);

                    new A().get();
                    new B().get();
                }
            }).start();
        }
    }

    /**
     * 内部类和静态内部类都是延时加载的，也就是说只有在明确用到内部类时才加载。只使用外部类时不加载。
     * 外部类初次加载，会初始化静态变量、静态代码块、静态方法，但不会加载内部类和静态内部类。
     * 实例化外部类，调用外部类的静态方法、静态变量，则外部类必须先进行加载，但只加载一次。
     * 直接调用静态内部类时，外部类不会加载。
     */
    static class A {
        public void get() {
            int data = threadData.get(Thread.currentThread());
            System.out.println("A: " + Thread.currentThread().getName() + "  data:  " + data);
        }
    }

    static class B {
        public void get() {
            int data = threadData.get(Thread.currentThread());
            System.out.println("B:" + Thread.currentThread().getName() + "  data:  " + data);
        }
    }
}
