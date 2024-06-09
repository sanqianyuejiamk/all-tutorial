package com.mengka.springboot.xxljob;

import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.XxlJob;
import groovy.util.logging.Slf4j;


@Component
@Slf4j
public class MyDemoJobHandler {//extends IJobHandler{

    private static final Logger logger = LoggerFactory.getLogger(MyDemoJobHandler.class);

//    @Override
//    public void execute() throws Exception {
//
//        logger.info("xxl-job, heoooo world.");
//
//    }

    @XxlJob("demoJobHandler")
    public ReturnT<String> demoJobHandler() throws Exception {
        XxlJobHelper.log("XXL-JOB, Hello World.");
        for (int i = 0; i < 5; i++) {
            logger.info("this is >>>>>>>>>>> " + i);
            TimeUnit.SECONDS.sleep(2);
        }
        return ReturnT.SUCCESS;
    }

}