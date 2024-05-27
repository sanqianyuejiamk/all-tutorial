package com.farabbit.threaddemo;

import com.bkatwal.util.CacheUtil;
import com.farabbit.threaddemo.model.TestPojo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.Redisson;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.codec.KryoCodec;
import org.redisson.config.Config;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

/**
 *
 *  Redisson supports many popular codecs, including Kryo.
 *  It maintains the connection pool and topology update for clusters, master/slave, and single Redis servers.
 *
 *  The Redisson API covers not only Redis hash operations but also fully implements java.util.Map and java.util.concurrent.ConcurrentMap interfaces.
 *  It also supports map entry eviction and local cache for map entires.
 *
 *  https://dzone.com/articles/3-ways-to-use-redis-hash-in-java
 *
 * 【Conclusion】
 *  It is bluntly obvious that in terms of code simplicity, Redisson is a much better choice than other Redis Java clients when working with Redis Hashes.
 *
 *  Reddisson提供的都是分布式对象，用来操作redis集群
 *  操作的数据分布在集群之中
 *
 * @author mengka
 * @version 2021/3/24
 * @since
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class RedissonTest {

    @Test
    public void redisOpTest() {
        Config config = new Config();
        config.setCodec(new KryoCodec(Arrays.asList(Long.class, TestPojo.class)))
                .useSingleServer().setAddress("redis://127.0.0.1:6379");

        RedissonClient redisson = Redisson.create(config);

        RMap<Long, TestPojo> map = redisson.getMap("PUT_CACHE1_test");
        long key = CacheUtil.buildCacheKey(new Object[] { 1, "testName" });
        TestPojo value = new TestPojo(221, "New York");
        map.put(key, value);

        TestPojo mappedValue = map.get(CacheUtil.buildCacheKey(new Object[] { 1, "testName" }));

//        MyValue newValue = new MyValue("Narrow street", "New York");
//        map.fastPutIfAbsent(key, newValue);

        redisson.shutdown();
    }
}
