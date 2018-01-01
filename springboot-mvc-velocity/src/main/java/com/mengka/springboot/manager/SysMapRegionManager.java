package com.mengka.springboot.manager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mengka.springboot.dao.domain.SysMapRegionDO;
import com.mengka.springboot.dao.persistence.SysMapRegionDOMapper;
import com.mengka.springboot.map_01.MapCityDO;
import com.mengka.springboot.map_01.map_01;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author huangyy
 * @date 2018/01/01.
 */
@Log4j2
@Service
public class SysMapRegionManager {

    @Autowired
    private SysMapRegionDOMapper sysMapRegionDOMapper;

    public void initSysMapRegionData() {
        String path = "/Users/hyy044101331/Documents/中国地图城市数据.json";
        String result = map_01.readAll(path);

        JSONObject jsonObject = JSON.parseObject(result);
        //初始化城市数据
        for (String key : jsonObject.keySet()) {
            JSONObject jsonObject1 = jsonObject.getJSONObject(key);
            MapCityDO mapCityDO = JSON.parseObject(jsonObject1.toJSONString(), MapCityDO.class);
            log.info("mapCityDO[{}] = {}", key, JSON.toJSONString(mapCityDO));
            initCityData(mapCityDO);
        }
    }

    public void initCityData(MapCityDO mapCityDO) {
        if (mapCityDO == null) {
            return;
        }
        SysMapRegionDO sysMapRegionDO = new SysMapRegionDO();
        sysMapRegionDO.setName(mapCityDO.getName());
        sysMapRegionDO.setX(mapCityDO.getX());
        sysMapRegionDO.setY(mapCityDO.getY());
        sysMapRegionDO.setProvince(mapCityDO.getProvince());
        sysMapRegionDO.setLat(mapCityDO.getLat());
        sysMapRegionDO.setLon(mapCityDO.getLon());
        sysMapRegionDO.setLevel(mapCityDO.getLevel().getLevel());
        sysMapRegionDO.setCityNo(String.valueOf(mapCityDO.getCityNo()));
        sysMapRegionDOMapper.insert(sysMapRegionDO);
    }
}
