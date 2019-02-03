package spring.springboot.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.Future;

/**
 * 异步调用
 * 我们需要使用Future<T>来返回异步调用的结果
 */
@Component
@Slf4j
public class Task {

    //Future<T>
    //Future是对于具体的Runnable或者Callable任务的执行结果进行取消、查询是否完成、获取结果的接口。必要时可以通过get方法获取执行结果，该方法会阻塞直到任务返回结果。
    //cancel方法用来取消任务，如果取消任务成功则返回true，如果取消任务失败则返回false。参数mayInterruptIfRunning表示是否允许取消正在执行却没有执行完毕的任务，如果设置true，则表示可以取消正在执行过程中的任务。
    // 如果任务已经完成，则无论mayInterruptIfRunning为true还是false，此方法肯定返回false，即如果取消已经完成的任务会返回false；
    // 如果任务正在执行，若mayInterruptIfRunning设置为true，则返回true，若mayInterruptIfRunning设置为false，则返回false；如果任务还没有执行，则无论mayInterruptIfRunning为true还是false，肯定返回true。
    //isCancelled方法表示任务是否被取消成功，如果在任务正常完成前被取消成功，则返回 true。
    //isDone方法表示任务是否已经完成，若任务完成，则返回true；
    //get()方法用来获取执行结果，这个方法会产生阻塞，会一直等到任务执行完毕才返回；
    //get(long timeout, TimeUnit unit)用来获取执行结果，如果在指定时间内，还没获取到结果，就直接返回null。

    private static Random random = new Random();

    @Async
    public Future<String> doTaskOne() throws Exception {
        doTask("一");
        return new AsyncResult<>("任务一完成");
    }

    @Async
    public Future<String> doTaskTwo() throws Exception {
        doTask("二");
        return new AsyncResult<>("任务二完成");
    }

    @Async
    public Future<String> doTaskThree() throws Exception {
        doTask("三");
        return new AsyncResult<>("任务三完成");
    }

    private void doTask(String num) throws Exception {
        System.out.println("开始做任务" + num);
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("完成任务" + num + "，耗时：" + (end - start) + "毫秒");
    }

    // 使用Future以及定义超时
    @Async("taskExecutor")
    public Future<String> run() throws Exception {
        long sleep = random.nextInt(10000);
        log.info("开始任务，需耗时：" + sleep + "毫秒");
        Thread.sleep(sleep);
        log.info("任务完成");
        return new AsyncResult<>("test");
    }
}
