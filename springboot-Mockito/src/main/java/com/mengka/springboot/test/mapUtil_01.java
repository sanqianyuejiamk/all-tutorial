package com.mengka.springboot.test;

import com.google.gson.Gson;
import com.mengka.springboot.utils.MapUtil;
import com.mengka.springboot.utils.TimeUtil;

import java.util.*;

/**
 * @author mengka
 * @version 2021/4/19
 * @since
 */
public class mapUtil_01 {

    public static void main(String... args){

        List recordFile = new ArrayList();
        Map<String, String> recordMap1 = new HashMap<>();
        recordMap1.put("13018911022","通话001");
        Map<String, String> recordMap2 = new HashMap<>();
        recordMap2.put("13018911023","通话002");
        recordFile.add(recordMap1);
        recordFile.add(recordMap2);
        Map map = new HashMap();
        map.put("recordFile",recordFile);
        map.put("startTime",TimeUtil.toDate(new Date(),TimeUtil.FORMAT_YYYY_MM_DD_HH_MM_SS));

        List<Map<String, String>> list = MapUtil.getMap(map, "recordFile");
        System.out.println(new Gson().toJson(list));


        String startTimeStr = MapUtil.getMap(map, "startTime", String.class, "0");
        System.out.println("startTimeStr = "+startTimeStr);
    }
}
