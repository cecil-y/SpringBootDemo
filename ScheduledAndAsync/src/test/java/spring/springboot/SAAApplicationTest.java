package spring.springboot;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring.springboot.async.Task;
import spring.springboot.async.TaskPool;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public class SAAApplicationTest {

    @Autowired
    private Task task;
    @Autowired
    private TaskPool taskPool;

    // 定时任务测试
    @Test
    public void test1() throws Exception {

    }

    // 使用@Async实现异步调用
    @Test
    public void test2() throws Exception {
        long start = System.currentTimeMillis();
        Future<String> task1 = task.doTaskOne();
        Future<String> task2 = task.doTaskTwo();
        Future<String> task3 = task.doTaskThree();
        while (true) {
            if (task1.isDone() && task2.isDone() && task3.isDone()) {
                //三个任务都调用完成，退出循环
                break;
            }
            Thread.sleep(1000);
        }
        long end = System.currentTimeMillis();
        System.out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");
    }

    // 自定义线程池
    @Test
    public void test3() throws Exception {
        taskPool.doTaskOne();
        taskPool.doTaskTwo();
        taskPool.doTaskThree();

        Thread.currentThread().join();
    }

    // ThreadPoolTaskScheduler线程池的优雅关闭
    @Test
    @SneakyThrows
    public void test4() throws Exception {
        for (int i = 0; i < 10000; i++) {
            taskPool.doTaskOnePlus();
            taskPool.doTaskTwoPlus();
            taskPool.doTaskThreePlus();

            if (i == 9999) {
                System.exit(0);
            }
        }
    }

    // 使用Future以及定义超时
    @Test
    public void test5() throws Exception{
        Future<String> future = task.run();
        //超过5s就会抛出异常：java.util.concurrent.TimeoutException
        String result = future.get(5, TimeUnit.SECONDS);
        log.info(result);
    }
}
