package thread.question;

/**
 * 子线程循环10次，接着主线程循环100次，接着又回到子线程循环10次
 * 接着再回到主线程又循环100，如此循环50次，请写出程序。
 *
 * @author
 * @data2021/3/516:34
 */
public class MainAndSubCyclical {
    public static void main(String[] args) {
        final Common common = new Common();
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 50; i++) {
                            common.sub(i);
                        }
                    }
                }
        ).start();

        for (int i = 0; i < 50; i++) {
            common.main(i);
        }
    }
}


class Common {
    private boolean sub = true;

    public synchronized void sub(int i) {
        //使用will而不用if可以避免虚假唤醒
        while (!sub) {
            try {
                //等待主main运行完
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int j = 0; j < 10; j++) {
            System.out.println("子线程循环第:" + j + "次" + i);
        }
        sub = false;
        this.notify();
    }

    public synchronized void main(int i) {
        while (sub) {
            try {
                //等待让sub运行完
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int j = 0; j < 100; j++) {
            System.out.println("主线程循环第:" + j + "次" + i);
        }
        sub = true;
        this.notify();
    }
}
