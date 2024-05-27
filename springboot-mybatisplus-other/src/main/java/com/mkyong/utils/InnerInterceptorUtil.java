package com.mkyong.utils;

import com.mkyong.base.FrException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import static com.mkyong.base.CommonCodeEnum.PARAM_VALIDATION_FAIL;

/**
 * @author mengka
 * @version 2021/4/26
 * @since
 */
public class InnerInterceptorUtil {

    public static void setFieldValue(Object object, Field field,Object value){
        field.setAccessible(true);
        try{
            //


            field.set(object,value);
        }catch (IllegalAccessException e){
            throw new FrException(PARAM_VALIDATION_FAIL);
        }
    }

    public static String getFieldValue(Field field,Object value){
        field.setAccessible(true);
        try{
            return field.get(value).toString();
        }catch (IllegalAccessException e){
            throw new FrException(PARAM_VALIDATION_FAIL);
        }
    }

    public static void setFieldValueByMap(List<Field> fields,Object key,Map<String,Object> updateFields){
        fields.stream().filter(f -> updateFields.containsKey(f.getName()))
                .forEach(f -> InnerInterceptorUtil.setFieldValue(key,f,updateFields.get(f.getName())));
    }
}
