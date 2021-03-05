package thread.currentThread;

/**
 * @author
 * @data2021/3/516:05
 */
public class CurrentThread {
    public static void main(String[] args) {
        Other other = new Other();
        other.start();
        other.interrupt();
        System.out.println("当前线程是: " + other.getName() + "当前线程是否停止:  " + other.isInterrupted());
        //Thread.currentThread表示当前代码段正在被哪个线程调用的相关信息。
        System.out.println("当前线程是: " + other.currentThread().getName() + "当前线程是否停止：  " + other.interrupted());
    }
}

class Other extends Thread {
    @Override
    public void run() {
        System.out.println("Other 当前线程的名字: " + this.getName());
        System.out.println("Other 当前线程的名字: " + this.currentThread().getName());
    }
}
