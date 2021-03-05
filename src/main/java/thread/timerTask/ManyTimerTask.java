package thread.timerTask;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 多TimerTask及延时的测试
 *
 * @author
 * @data2021/3/515:01
 */
public class ManyTimerTask {
    private static Timer timer = new Timer();

    public static class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            try {
                System.out.println("运行时间为：" + new Date());
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class MyOtherTimerTask extends TimerTask {
        @Override
        public void run() {
            System.out.println("运行时间为 :" + new Date());
        }
    }

    public static void main(String[] args) {
        MyTimerTask timerTask = new MyTimerTask();
        MyOtherTimerTask otherTimerTask = new MyOtherTimerTask();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = sdf.parse("2021-03-05 15:20:20");
            Date date1 = sdf.parse("2021-03-05 15:30:30");
            System.out.println("myTask计划运行时间:" + date);
            System.out.println("myOtherTask计划运行时间:" + date1);
            timer.schedule(timerTask, date); // 在时间等于或者超过date的时候执行且仅执行一次
            timer.schedule(otherTimerTask, date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
