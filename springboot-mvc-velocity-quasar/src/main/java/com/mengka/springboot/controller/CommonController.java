package com.mengka.springboot.controller;

import com.mengka.springboot.dao.domain.BookDO;
import com.mengka.springboot.dao.persistence.BookDOMapper;
import com.mengka.springboot.manager.CabbageForwardManager;
import com.mengka.springboot.manager.SysMapRegionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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

    /**
     *  druid代码：
     * https://github.com/sanqianyuejiamk/druid
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/rate")
    public String product(Map<String, Object> model, Long id){
        logger.info("CommonController rate id = {}",id);
        model.put("list",null);

        //add
        BookDO bookDO = new BookDO();
        bookDO.setName("数学");
        bookDO.setPrice(100);
        bookDO.setTenantId("2001");
        bookDOMapper.insert(bookDO);
        logger.info("CommonController add a new book! id = {}",bookDO.getId());

        return "product_rate";
    }

    @RequestMapping("/rate2")
    public String rate2(Map<String, Object> model, Long id){
        logger.info("CommonController rate id = {}",id);

        try {
            cabbageForwardManager.forward();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "product_rate";
    }

    @RequestMapping("/rate3")
    public String rate3(Map<String, Object> model, Long id){
        logger.info("CommonController rate id = {}",id);

        try {
            cabbageForwardManager.forward2();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "product_rate";
    }

    @RequestMapping("/map_01")
    public String map_01(Map<String, Object> model, Long id){
        logger.info("CommonController map_01 id = {}",id);

        sysMapRegionManager.initSysMapRegionData();
        return "product_rate";
    }
}
