package com.mengka.springboot.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mengka.springboot.domain.SoftPhoneLog;
import com.mengka.springboot.utils.SequentialUuidHexGenerator;
import com.mengka.springboot.vo.DebugMode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.DatatypeConverter;

/**
 * @author mengka
 * @version 2021/4/18
 * @since
 */
@Slf4j
@Service
public class CommandService {

    //默认30天删除日志
    public static final long LOG_DELETE_TIME = 1000 * 60 * 60 * 24 * 30;

    //加密后的信息
    private Map<String, Object> tiNetInfoMap;

    @PostConstruct
    public void init() {
        Map<String, Object> tiNetMap = new HashMap<>();
//        tiNetMap.put("baseUrl", platformConfig.getTiNetBaseUrl());
//        tiNetMap.put("enterpriseId", platformConfig.getTiNetEnterpriseId());
//        tiNetMap.put("token", platformConfig.getTiNetToken());
        tiNetMap.put("env", "test");

        try {
            String s = new ObjectMapper().writeValueAsString(tiNetMap);
            byte[] bytes = s.getBytes();
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = (byte) (bytes[i] ^ 0xf2);
            }
            String tiNetInfo = DatatypeConverter.printHexBinary(bytes);
            Map<String, Object> map = new HashMap<>();
            map.put("data", tiNetInfo);
            tiNetInfoMap = map;
        } catch (Exception e) {
            log.error("初始化出现错误 [{}]", e);
        }
    }

    //删除过期的日志
//    @Scheduled(cron = "0 28 2 * * ?")
//    public void deleteLog() {
//        repository.deleteAllByDeleteTimestampBefore(System.currentTimeMillis());
//    }

    /**
     * 日志记录到表中
     *
     * @param softPhoneLog
     */
    public void log(SoftPhoneLog softPhoneLog) {
        softPhoneLog.setLogId(SequentialUuidHexGenerator.generate());
        if (softPhoneLog.getCreateTimestamp() == 0) {
            softPhoneLog.setCreateTimestamp(System.currentTimeMillis());
        }
        softPhoneLog.setDeleteTimestamp(softPhoneLog.getCreateTimestamp() + LOG_DELETE_TIME);
        log.info("softPhoneLog = {}",new Gson().toJson(softPhoneLog));
//        repository.save(log);
    }

    public DebugMode getDebugMode(String sdkId) {
//        Object o = redisService.get(prefix + sdkId);
        Object o = null;
        DebugMode debugMode = new DebugMode();
        debugMode.setSdkId(sdkId);
//        debugMode.setEnv(platformConfig.getEnv());
        if (o != null) {
            debugMode.setMode(DebugMode.MODE_ON);
        } else {
            debugMode.setMode(DebugMode.MODE_OFF);
        }
        return debugMode;
    }

    public Map<String, Object> getPlatformInfo(Map<String, Object> info) {
        if (info != null) {
            Object platform = info.get("platform");
            if ("TI-NET".equals(platform)) {
                return tiNetInfoMap;
            }
        }
        return null;
    }

    public void setDebugMode(DebugMode debugMode) {
        if (debugMode == null) {
            return;
        }
        if (DebugMode.MODE_ON.equals(debugMode.getMode())) {
//            redisService.set(prefix + debugMode.getSdkId(), "1", 1, TimeUnit.HOURS);
        } else {
            //移除debugMode
//            redisService.deleteKey(prefix + debugMode.getSdkId());
        }
    }
}
