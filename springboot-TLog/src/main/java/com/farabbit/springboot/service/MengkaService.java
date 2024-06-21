package com.farabbit.springboot.service;

import com.yomahub.tlog.core.annotation.TLogAspect;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 *  TLog日志追踪：
 *   https://tlog.yomahub.com/pages/f62a84/#%E5%BC%82%E6%AD%A5%E6%97%A5%E5%BF%97
 *
 *   TLog自动的对你的日志进行打标签，自动生成TraceId贯穿你微服务的一整条链路。并且提供上下游节点信息。适合中小型企业以及想快速解决日志追踪问题的公司项目使用。
 *
 * @author huangyy
 * @email e-yongyon.huang@geely.com
 * @date 2024/6/21 13:18
 */
@Slf4j
@Service
public class MengkaService {

    /**
     * [2024-06-21 13:22:42,399] [http-nio-8071-exec-1] [INFO] -  c.f.s.c.TestController 36 <0><1804022106376089600> -------, 调用t1接口
     * [2024-06-21 13:22:47,603] [http-nio-8071-exec-1] [INFO] -  c.f.s.s.MengkaService 18 <0><1804022106376089600> [id:"044101331"] 这是第一条日志
     * [2024-06-21 13:22:47,604] [http-nio-8071-exec-1] [INFO] -  c.f.s.s.MengkaService 19 <0><1804022106376089600> [id:"044101331"] 这是第二条日志
     * [2024-06-21 13:22:47,605] [http-nio-8071-exec-1] [INFO] -  c.f.s.s.MengkaService 20 <0><1804022106376089600> [id:"044101331"] 这是第三条日志
     * [2024-06-21 13:22:47,609] [Thread-24] [INFO] -  c.f.s.s.MengkaService 21 <0><1804022106376089600> [id:"044101331"] 这是异步日志
     * @param id
     * @param name
     */
    @TLogAspect({"id"})
    public void demo1(String id,String name){
        log.info("这是第一条日志");
        log.info("这是第二条日志");
        log.info("这是第三条日志");
        new Thread(() -> log.info("这是异步日志")).start();
    }
}
