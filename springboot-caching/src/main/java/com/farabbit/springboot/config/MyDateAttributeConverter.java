package com.farabbit.springboot.config;

import com.farabbit.springboot.util.TimeUtil;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Date;

/**
 * @author mengka
 * @Date 2024-03-10 16:21
 */
@Slf4j
@Converter(autoApply = true)
public class MyDateAttributeConverter implements AttributeConverter<Date, String> {
    @Override
    public String convertToDatabaseColumn(Date date) {
        try{
            return TimeUtil.toDate(date,TimeUtil.FORMAT_YYYY_MM_DD_HH_MM_SS);
        }catch (Exception e){
            log.error("MyDateAttributeConverter convertToDatabaseColumn error!",e);
            return null;
        }
    }

    @Override
    public Date convertToEntityAttribute(String s) {
        try{
            return TimeUtil.toDate(s,TimeUtil.FORMAT_YYYY_MM_DD_HH_MM_SS);
        }catch (Exception e){
            log.error("MyDateAttributeConverter convertToEntityAttribute error!",e);
            return null;
        }
    }
}
