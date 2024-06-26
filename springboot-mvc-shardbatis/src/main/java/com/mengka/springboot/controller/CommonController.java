package com.mengka.springboot.controller;

import com.mengka.springboot.dao.domain.BookDO;
import com.mengka.springboot.manager.SyncVoiceCallManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Map;

/**
 * @author huangyy
 * @description
 * @data 2017/02/19.
 */
@Slf4j
@Controller
@RequestMapping("/v1")
public class CommonController {

//    @Autowired
//    private BookDOMapper bookDOMapper;

    @Autowired
    private SyncVoiceCallManager syncVoiceCallManager;

    @RequestMapping("/rate")
    public String product(Map<String, Object> model, Long id){
        log.info("CommonController rate id = {}",id);
        model.put("list",null);

        //add
        BookDO bookDO = new BookDO();
        bookDO.setName("数学");
        bookDO.setPrice(100);
        bookDO.setTenantId("2001");
//        bookDOMapper.insert(bookDO);
        log.info("CommonController add a new book! id = {}",bookDO.getId());


        String userId = "00013383624290";
        String phoneNo = "13383624290";
        syncVoiceCallManager.syncVoiceCall(userId,phoneNo);

        return "product_rate";
    }
}
