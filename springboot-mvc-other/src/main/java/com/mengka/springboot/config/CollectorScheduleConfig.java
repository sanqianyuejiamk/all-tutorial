package com.mengka.springboot.config;

import com.mengka.springboot.schedule.VjApplyDetailJob;
import com.mengka.springboot.utils.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author huangyy
 * @description
 * @data 2016/12/09.
 */
@Slf4j
@Component
@Configurable
@EnableScheduling
public class CollectorScheduleConfig {

    @Autowired
    private VjApplyDetailJob applyDetailJob;

    //每1分钟执行一次
    @Scheduled(cron = "0 */1 *  * * * ")
    public void reportCurrentByCron() {
        log.debug("collectorScheduleConfig By Cron: The time is now " + TimeUtil.toDate(new Date(), TimeUtil.YYYY_MM_DD_HH_MM_SS));
        applyDetailJob.syncApplyDetail();
    }

}
