package com.farabbit.threaddemo.manager;

import com.bkatwal.annotation.CachePut;
import com.bkatwal.annotation.CacheDelete;
import com.bkatwal.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.farabbit.threaddemo.model.*;
/**
 * @author mengka
 * @version 2021/3/24
 * @since
 */
@Service
public class MengkaManager {

    @CachePut(cacheNames = {
            "PUT_CACHE1" }, keyExpression = "#param1,#param2")
    public TestPojo saveByIdAndName(int id, String name) {
        return new TestPojo(id, name);
    }

    @CacheDelete(cacheNames = {
            "PUT_CACHE1" }, keyExpression = "#param1,#param2")
    public void deleteById(int id, String name){
        //some delete operation
    }

    @Cacheable(cacheName = "PUT_CACHE1", keyExpression = "#param1,#param2")
    public TestPojo getByIdAndName(int id, String name){
        //some  operation
        return new TestPojo(331,"test data");
    }
}
