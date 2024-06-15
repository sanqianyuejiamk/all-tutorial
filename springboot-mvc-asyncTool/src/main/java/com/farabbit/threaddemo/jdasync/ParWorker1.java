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
public class ParWorker1 implements IWorker<String, String>, ICallback<String, String> {

    @Override
    public String action(String s, Map<String, WorkerWrapper> map) {
        WorkerWrapper workerA = map.get("workerA");
//        System.out.println("获取workerA的结果：" + JSONUtil.toJsonStr(workerA));
        if (Objects.nonNull(workerA)) {
            String result = (String)workerA.getWorkResult().getResult();
            System.out.println("ParWorker1 get result from A: "+result);
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "result = " + SystemClock.now() + "---param = " + s + " from 1";
    }

    @Override
    public String defaultValue() {
        return "worker1--default";
    }

    @Override
    public void begin() {
        //System.out.println(Thread.currentThread().getName() + "- start --" + System.currentTimeMillis());
    }

    @Override
    public void result(boolean success, String param, WorkResult<String> workResult) {
        if (success) {
            System.out.println("callback worker1 success--" + SystemClock.now() + "----" + workResult.getResult()
                    + "-threadName:" +Thread.currentThread().getName());
        } else {
            System.err.println("callback worker1 failure--" + SystemClock.now() + "----"  + workResult.getResult()
                    + "-threadName:" +Thread.currentThread().getName());
        }
    }

}