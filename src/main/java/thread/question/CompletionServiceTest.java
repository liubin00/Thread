package thread.question;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 启动10个线程，哪个先运行完就返回那个结果
 *
 * @author
 * @data2021/3/518:26
 */
public class CompletionServiceTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CompletionService completionService = new ExecutorCompletionService(executorService);
        for (int i = 0; i <= 10; i++) {
            final int result = i;
            completionService.submit(new Callable() {
                @Override
                public Object call() throws Exception {
                    Thread.sleep(new Random().nextInt(5000));
                    return result;
                }
            });
        }
        System.out.println(completionService.take().get());
    }
}
