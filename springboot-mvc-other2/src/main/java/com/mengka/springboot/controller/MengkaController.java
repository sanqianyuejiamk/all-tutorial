package com.mengka.springboot.controller;

import com.google.common.eventbus.AsyncEventBus;
import com.mengka.springboot.model.cmd.IsBookCmd;
import com.mengka.springboot.model.cmd.SellBookCmd;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author mengka
 * @version 1.0
 * @date 2021/5/23 14:59
 */
@Slf4j
@RestController
@RequestMapping("/mk")
public class MengkaController {

    @Autowired
    protected AsyncEventBus asyncEventBus;

    /**
     *  http://127.0.0.1:8071/mk/testbus?ownerId=11
     *
     * @param ownerId
     * @return
     */
    @GetMapping(value = "/testbus")
    public String testAsync(@RequestParam String ownerId) {
        long start = System.currentTimeMillis();

        //同步获取结果
        IsBookCmd isBookCmd = (IsBookCmd) new IsBookCmd().setBizNo("111");
        asyncEventBus.post(isBookCmd);

        SellBookCmd sellBookCmd = (SellBookCmd) new SellBookCmd().setOrderId(ownerId).setSellTime(new Date()).setBizNo("111");
        asyncEventBus.post(sellBookCmd);

        long endTime = System.currentTimeMillis();
        return " -----" + (endTime - start) + "ms";
    }
}
