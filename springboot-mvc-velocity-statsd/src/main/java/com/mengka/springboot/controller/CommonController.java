package com.mengka.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.mengka.springboot.component.RedisComponent;
import com.mengka.springboot.dao.domain.BookDO;
import com.mengka.springboot.dao.persistence.BookDOMapper;
import com.mengka.springboot.manager.CabbageForwardManager;
import com.mengka.springboot.manager.SysMapRegionManager;
import com.mengka.springboot.util.TimeUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.JedisPublicMetrics;
import org.springframework.boot.actuate.metrics.Metric;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * @author huangyy
 * @description
 * @data 2017/02/19.
 */
@Controller
@RequestMapping("/v1")
public class CommonController {

    static final Logger logger = LogManager.getLogger(CommonController.class);

    @Autowired
    private BookDOMapper bookDOMapper;

    @Autowired
    private SysMapRegionManager sysMapRegionManager;

    @Autowired
    private CabbageForwardManager cabbageForwardManager;

    @Autowired
    private RedisComponent redisComponent;

    @Autowired
    private JedisPublicMetrics jedisPublicMetrics;

    /**
     * druid代码：
     * https://github.com/sanqianyuejiamk/druid
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/rate")
    public String product(Map<String, Object> model, Long id) {
        logger.info("CommonController rate id = {}", id);
        model.put("list", null);

        //add
        BookDO bookDO = new BookDO();
        bookDO.setName("数学");
        bookDO.setPrice(100);
        bookDO.setTenantId("2001");
        bookDOMapper.insert(bookDO);
        logger.info("CommonController add a new book! id = {}", bookDO.getId());

        return "product_rate";
    }

    @RequestMapping("/rate2")
    public String rate2(Map<String, Object> model, Long id) {
        logger.info("CommonController rate id = {}", id);

        try {
            cabbageForwardManager.forward();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "product_rate";
    }

    @RequestMapping("/rate3")
    public String rate3(Map<String, Object> model, Long id) {
        logger.info("CommonController rate id = {}", id);

        try {
            cabbageForwardManager.forward2();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "product_rate";
    }

    /**
     * 》》redis连接池数据采集：
     *  http://127.0.0.1:8073/metrics/redis.*
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/rate5")
    public String rate5(Map<String, Object> model, Long id) {
        logger.info("CommonController rate id = {}", id);

        redisComponent.setString("k1", "test[" + TimeUtil.toDate(new Date(), TimeUtil.FORMAT_YYYY_MM_DD_HH_MM_SS));

        String value = redisComponent.getString("k1");
        logger.info("-----------, value = {}", value);


        Collection<Metric<?>> collection = jedisPublicMetrics.metrics();
        logger.info("---------, metric = {}", JSON.toJSONString(collection));

        return "product_rate";
    }

    @RequestMapping("/map_01")
    public String map_01(Map<String, Object> model, Long id) {
        logger.info("CommonController map_01 id = {}", id);

        sysMapRegionManager.initSysMapRegionData();
        return "product_rate";
    }
}
