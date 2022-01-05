package com.farabbit.springboot.controller;

import com.farabbit.springboot.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @author mengka
 * @Date 2022-01-05 16:35
 */
@Slf4j
@RestController
public class AddressController {

    @Autowired
    private AddressService addressService;

    @Autowired
    private CacheManager cacheManager;

    @GetMapping("/address/{id}")
    public ResponseEntity<String> getAddress(@PathVariable("id") long customerId) {
        return ResponseEntity.ok(addressService.getAddress(customerId));
    }

    @GetMapping("/address2/{id}")
    public ResponseEntity<String> getAddress2(@PathVariable("id") long customerId) {
        return ResponseEntity.ok(addressService.getAddress2(customerId));
    }

    /**
     *  http://127.0.0.1:8071/test2
     *
     * @return
     */
    @GetMapping("/test2")
    public ResponseEntity<String> test2() {
        Collection<String> cacheNames = cacheManager.getCacheNames();
        Object value2 = cacheManager.getCache("addresses").get(1L).get().toString();
        CaffeineCache cache = (CaffeineCache)cacheManager.getCache("addresses");
        com.github.benmanes.caffeine.cache.Cache<Object, Object> nativeCache = cache.getNativeCache();
        Map data = nativeCache.asMap();
        Set<Long> keys = data.keySet();

        Object value = data.get(1L);

        return ResponseEntity.ok(String.valueOf(value));
    }
}
