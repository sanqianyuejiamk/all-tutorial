package com.mengka.springboot.map_01;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.log4j.Log4j2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * @author huangyy
 * @date 2018/01/01.
 */
@Log4j2
public class map_01 {

    public static void main(String[] args) throws Exception {
        String path = "/Users/hyy044101331/Documents/中国地图城市数据.json";
        String result = readAll(path);

        JSONObject jsonObject = JSON.parseObject(result);


        for (String key : jsonObject.keySet()) {
            JSONObject jsonObject1 = jsonObject.getJSONObject(key);
            MapCityDO mapCityDO = JSON.parseObject(jsonObject1.toJSONString(), MapCityDO.class);
            log.info("mapCityDO[{}] = {}", key, JSON.toJSONString(mapCityDO));
        }
    }

    public static String readAll(String path) {
        String result = "";
        StringBuffer stringBuffer = new StringBuffer();
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(
                    new FileInputStream(path));
            BufferedReader bufferedReader = new BufferedReader(
                    inputStreamReader);
            stringBuffer = new StringBuffer();
            int str;
            while ((str = bufferedReader.read()) != -1) {
                stringBuffer.append((char) str);
            }
            result = stringBuffer.toString();
        } catch (Exception e) {
            log.error(e);
        }
        return result;
    }
}
