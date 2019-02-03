package spring.springboot.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Random;

@Slf4j
@Component
public class TaskPool {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static Random random = new Random();

    @Async("taskExecutor")
    public void doTaskOne() throws Exception {
        doTask("一");
    }

    @Async("taskExecutor")
    public void doTaskTwo() throws Exception {
        doTask("二");
    }

    @Async("taskExecutor")
    public void doTaskThree() throws Exception {
        doTask("三");
    }

    @Async("taskExecutorPlus")
    public void doTaskOnePlus() throws Exception {
        doTaskPlus("一");
    }

    @Async("taskExecutorPlus")
    public void doTaskTwoPlus() throws Exception {
        doTaskPlus("二");
    }

    @Async("taskExecutorPlus")
    public void doTaskThreePlus() throws Exception {
        doTaskPlus("三");
    }

    private void doTask(String num) throws Exception {
        log.info("开始做任务" + num);
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        log.info("完成任务" + num + "，耗时：" + (end - start) + "毫秒");
    }

    private void doTaskPlus(String num) throws Exception{
        log.info("开始做任务" + num);
        long start = System.currentTimeMillis();
        log.info(stringRedisTemplate.randomKey());
        long end = System.currentTimeMillis();
        log.info("完成任务" + num + "，耗时：" + (end - start) + "毫秒");
    }

}
