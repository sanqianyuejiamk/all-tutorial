package com.farabbit.threaddemo.jdasync;

import cn.hutool.core.date.SystemClock;
import cn.hutool.json.JSONUtil;
import com.jd.platform.async.callback.ICallback;
import com.jd.platform.async.callback.IWorker;
import com.jd.platform.async.worker.WorkResult;
import com.jd.platform.async.wrapper.WorkerWrapper;

import java.util.Map;
import java.util.Objects;

/**
 * @author wuweifeng wrote on 2019-11-20.
 */
public class ParWorker3 implements IWorker<String, String>, ICallback<String, String> {

    @Override
    public String action(String s, Map<String, WorkerWrapper> map) {
        WorkerWrapper workerB = map.get("workerB");
        WorkerWrapper workerC = map.get("workerC");
        System.out.println("获取workerB的结果：" + JSONUtil.toJsonStr(workerB.getWorkResult()));
        if (Objects.nonNull(workerB)) {
            String result = (String)workerB.getWorkResult().getResult();
            System.out.println("ParWorker3 get result from B: "+result);
        }
        if (Objects.nonNull(workerC)) {
            String result = (String)workerC.getWorkResult().getResult();
            System.out.println("ParWorker3 get result from C: "+result);
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "result = " + SystemClock.now() + "---param = " + s + " from 3";
    }

    @Override
    public String defaultValue() {
        return "worker3--default";
    }

    @Override
    public void begin() {
        //System.out.println(Thread.currentThread().getName() + "- start --" + System.currentTimeMillis());
    }

    @Override
    public void result(boolean success, String param, WorkResult<String> workResult) {
        if (success) {
            System.out.println("callback worker3 success--" + SystemClock.now() + "----" + workResult.getResult()
                    + "-threadName:" +Thread.currentThread().getName());
        } else {
            System.err.println("callback worker3 failure--" + SystemClock.now() + "----"  + workResult.getResult()
                    + "-threadName:" +Thread.currentThread().getName());
        }
    }

}