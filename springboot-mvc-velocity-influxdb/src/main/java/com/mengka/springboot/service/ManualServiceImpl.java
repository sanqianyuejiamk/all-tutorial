package com.mengka.springboot.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.jws.WebService;


@WebService(endpointInterface = "com.mengka.springboot.service.ManualService")
public class ManualServiceImpl implements ManualService{
    private final static Logger logger = LogManager.getLogger(ManualServiceImpl.class);

    public int arrival(int regionCode, String berthCode) {
        logger.info("---------------, ManualService arrival..");
        return 0;
    }

    public int departure(int regionCode, String berthCode) {
        logger.info("---------------, ManualService departure..");
        return 0;
    }
}
