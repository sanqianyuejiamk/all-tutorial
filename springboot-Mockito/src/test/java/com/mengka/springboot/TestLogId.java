package com.mengka.springboot;

import com.mengka.springboot.utils.SequentialUuidHexGenerator;
import org.junit.Test;

/**
 * @author mengka
 * @version 2021/4/18
 * @since
 */
public class TestLogId {

    /**
     *  JVM: 78e58878
     *  ip: 40284775 (十进制-1076381557)
     *  count: 0
     *
     *  logId = 78e588780178e58a25e4402847750000
     */
    @Test
    public void getLogId(){
        String logId = SequentialUuidHexGenerator.generate();
        System.out.println("logId = "+logId);
    }
}
