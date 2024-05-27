package com.mengka.springboot.handler;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.Subscribe;
import com.mengka.springboot.model.cmd.IsBookCmd;
import com.mengka.springboot.model.cmd.SellBookCmd;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author mengka
 * @version 1.0
 * @date 2021/5/23 14:33
 */
@Slf4j
@Service
public class BookHandler {

    public BookHandler(AsyncEventBus asyncEventBus){
        asyncEventBus.register(this);
    }

    @Subscribe
    public void handleIsBookCmd(IsBookCmd cmd){
        log.info("-----IsBookHandler----- "+cmd);
    }

    @Subscribe
    public void handleSellBookCmd(SellBookCmd cmd){
        log.info("-----SellBookHandler----- "+cmd);
    }
}
