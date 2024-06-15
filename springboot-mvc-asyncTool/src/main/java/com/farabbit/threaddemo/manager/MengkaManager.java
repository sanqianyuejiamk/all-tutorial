package com.farabbit.threaddemo.manager;

import cn.hutool.core.date.SystemClock;
import com.farabbit.threaddemo.jdasync.ParWorker;
import com.farabbit.threaddemo.jdasync.ParWorker1;
import com.farabbit.threaddemo.jdasync.ParWorker2;
import com.farabbit.threaddemo.jdasync.ParWorker3;
import com.jd.platform.async.executor.Async;
import com.jd.platform.async.wrapper.WorkerWrapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

/**
 * @author mengka
 * @Date 2024-06-15 19:47
 */
@Service
public class MengkaManager {

    /**
     *  执行流程图：
     *  https://images.gitee.com/uploads/images/2019/1226/140405_93800bc7_303698.png
     */
    @SneakyThrows
    public void test(){
        ParWorker w = new ParWorker();
        ParWorker1 w1 = new ParWorker1();
        ParWorker2 w2 = new ParWorker2();
        ParWorker3 w3 = new ParWorker3();

        WorkerWrapper<String, String> workerWrapper3 =  new WorkerWrapper.Builder<String, String>()
                .worker(w3)
                .callback(w3)
                .param("3")
                .build();

        WorkerWrapper<String, String> workerWrapper2 =  new WorkerWrapper.Builder<String, String>()
                .worker(w2)
                .callback(w2)
                .param("2")
                .next(workerWrapper3)
                .build();

        WorkerWrapper<String, String> workerWrapper1 =  new WorkerWrapper.Builder<String, String>()
                .worker(w1)
                .callback(w1)
                .param("1")
                .next(workerWrapper3)
                .build();

        WorkerWrapper<String, String> workerWrapper =  new WorkerWrapper.Builder<String, String>()
                .worker(w)
                .callback(w)
                .param("0")
                .next(workerWrapper1, workerWrapper2)
                .build();

        long now = SystemClock.now();
        System.out.println("begin-" + now);

        Async.beginWork(3100, workerWrapper);
//        Async.beginWork(2100, workerWrapper);

        System.out.println("end-" + SystemClock.now());
        System.err.println("cost-" + (SystemClock.now() - now));

        System.out.println(Async.getThreadCount());
        Async.shutDown();

    }
}
