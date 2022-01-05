package com.farabbit.springboot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author mengka
 * @Date 2022-01-05 16:33
 */
@Slf4j
@Service
public class AddressService {

    @Autowired
    private CacheManager cacheManager;

    @Cacheable(cacheNames = "addresses")
    public String getAddress(long customerId)  {
        log.info("Method getAddress is invoked for customer {}", customerId);

        return "123-"+customerId+" Main St";
    }

    public String getAddress2(long customerId) {
        if(cacheManager.getCache("addresses2").get(customerId) != null) {
            return cacheManager.getCache("addresses2").get(customerId).get().toString();
        }

        log.info("Method getAddress2 is invoked for customer {}", customerId);

        String address = "addresses2 123-"+customerId+" Main St";

        cacheManager.getCache("addresses2").put(customerId, address);

        return address;
    }
}
