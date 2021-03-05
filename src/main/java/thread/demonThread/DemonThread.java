package thread.demonThread;

/**
 * 守护线程
 *
 * @author
 * @data2021/3/514:51
 */
public class DemonThread extends Thread {
    private int i = 0;

    @Override
    public void run() {
        while (true) {
            try {
                i++;
                System.out.println("守护线程正在工作 i=" + i);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        DemonThread demonThread = new DemonThread();
        demonThread.setDaemon(true);
        demonThread.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("用户线程main结束了，守护线程也就结束了");
    }
}
