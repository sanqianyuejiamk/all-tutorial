package com.farabbit.threaddemo.controller;

import com.bkatwal.api.RedisCacheService;
import com.bkatwal.util.CacheUtil;
import com.farabbit.threaddemo.manager.MengkaManager;
import com.farabbit.threaddemo.model.TestPojo;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Arrays;
import java.util.List;

@Controller
public class HelloController {

    private static final Logger logger = LogManager.getLogger(HelloController.class);

    private List<Integer> num = Arrays.asList(1, 2, 3, 4, 5);

    @Autowired
    private RedisCacheService redisCacheService;

    @Autowired
    private MengkaManager mengkaManager;

    @GetMapping("/")
    public String main(Model model) {

        // pre-java 8
        if (logger.isDebugEnabled()) {
            logger.debug("Hello from Log4j 2 - num : {}", num);
        }

        // java 8 lambda, no need to check log level
        logger.info("Hello from Log4j 2 - num : {}", () -> num);


        mengkaManager.saveByIdAndName(2, "testName");

        TestPojo testPojo = (TestPojo) redisCacheService
                .getFromCache("PUT_CACHE1", CacheUtil.buildCacheKey(new Object[] { 2, "testName" }));
        logger.info("--------, testPojo = {}",new Gson().toJson(testPojo));

        String[] cacheNames = new String[1];
        cacheNames[0] = "CACHE";
        redisCacheService.saveInRedis(cacheNames, "key12", "value");

        String val = (String) redisCacheService.getFromCache(cacheNames[0], "key12");
        logger.info("--------, val = {}",val);

        model.addAttribute("tasks", num);
        return "welcome"; //view
    }


    /**
     *  Cacheable注解,缓存存在则返回缓存中数据,不存在返回接口数据;
     *
     *  http://127.0.0.1:8073/tbb
     *
     * @param model
     * @return
     */
    @GetMapping("/tbb")
    public String tbb(Model model) {

        TestPojo testPojo = mengkaManager.getByIdAndName(2, "testName");


        mengkaManager.deleteById(2, "testName");

        TestPojo testPojo2 = mengkaManager.getByIdAndName(2, "testName");


        model.addAttribute("tasks", num);
        return "welcome"; //view
    }

    private int getNum() {
        return 100;
    }

}