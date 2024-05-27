package com.mengka.springboot.config;

//
//import com.cic.ircsofa.cache.service.RedisService;
//import com.cic.ircsofa.cache.service.lock.DistributedLockService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.core.RedisTemplate;
//
//import javax.annotation.PostConstruct;
//import java.time.Duration;
//import java.util.Optional;
//import java.util.Random;
//import java.util.UUID;
//
///**
// * @Author wujie
// * @Class DistributedSeqnum
// * @Description 分布式取号器  因为配置一模一样 只能接用redis获取不同服务实例的num 支持99个服务
// * @Date 2021/3/28 14:40
// */
//@Configuration
//public class DistributedNameManager {
//    private final static String lockName = "callcenter-soft-phone-AquireInstanceNumLock";
//    private final static String INSTANCE_NAME = "callcenter-soft-phone-InstanceNum";
//    private final static String NAME_PREIFX = "callcenter-soft-phone.wb.name.";
//    private final static Logger logger = LoggerFactory.getLogger(DistributedNameManager.class);
//    private final static int SERVICE_NUM = 99;
//
//    @Autowired
//    private DistributedLockService lockService;
//    @Autowired
//    private RedisService redisService;
//
//    private static RedisTemplate<String, Object> redisTemplate;
//    private static int instanceSeq = new Random().nextInt(SERVICE_NUM);
//
//    @PostConstruct
//    public void init() {
//        try {
//            String requestId = UUID.randomUUID().toString();
//            try {
//                lockService.tryLock(lockName, requestId, 1000L, 10);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            redisTemplate = redisService.getRedisTemplate();
//            Long increment = redisTemplate.opsForValue().increment(INSTANCE_NAME, 1);
//            instanceSeq = increment.intValue();
//            if (increment >= SERVICE_NUM) {
//                redisTemplate.opsForValue().set(INSTANCE_NAME, 0);
//            }
//
//        } catch (Exception e) {
//            logger.error("取号出现错误 自动降级处理{}", e);
//        }
//
//    }
//
//    public static int getInstanceSeq() {
//        return instanceSeq;
//    }
//
//    public static void putInstanceAndName(String name) {
//        if (redisTemplate != null) {
//            redisTemplate.opsForValue().set(NAME_PREIFX + name, instanceSeq, Duration.ofDays(1));
//        }
//    }
//
//    public static void removeInstanceAndName(String name) {
//        if (redisTemplate != null) {
//            redisTemplate.delete(NAME_PREIFX + name);
//        }
//    }
//
//    /**
//     * 根据用户名去获取集群中真正的实例序号
//     *
//     * @param name
//     * @return
//     */
//    public static int getInstanceSeqByName(String name) {
//        Object object = redisTemplate.opsForValue().get(NAME_PREIFX + name);
//        if (object != null) {
//            try {
//
//                return (int) object;
//            } catch (Exception e) {
//                logger.error("值错误强转 [{}]", object);
//            }
//        }
//        return -1;
//    }
//}