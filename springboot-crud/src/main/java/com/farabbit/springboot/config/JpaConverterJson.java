package com.farabbit.springboot.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;
import java.util.Map;

/**
 * @author mengka
 * @Date 2022-02-13 19:57
 */
@Slf4j
@Converter(autoApply = true)
public class JpaConverterJson implements AttributeConverter<Map, String> {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Map meta) {
        try {
            if(meta == null){
                return null;
            }
//            else if(meta instanceof java.lang.String){
//                return String.valueOf(meta);
//            }
            return objectMapper.writeValueAsString(meta);
        } catch (JsonProcessingException ex) {
            log.error("JpaConverterJson convertToDatabaseColumn error!",ex);
            return null;
            // or throw an error
        }
    }

    @Override
    public Map convertToEntityAttribute(String dbData) {
        try {
            Class aa = Object.class;
            if(StringUtils.isBlank(dbData)){
                return null;
            }
//            else if(Object.class.getSimpleName() == java.lang.String){
//                return dbData;
//            }
            return objectMapper.readValue(dbData, Map.class);
        } catch (IOException ex) {
            // logger.error("Unexpected IOEx decoding json from database: " + dbData);
            log.error("JpaConverterJson convertToEntityAttribute error!",ex);
            return null;
        }
    }

}