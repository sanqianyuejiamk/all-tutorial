package com.mkyong.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import java.util.Date;
import java.util.Objects;

/**
 * @author mengka
 * @version 2021/4/25
 * @since
 */
@Configuration
@MapperScan({"com.mkyong.repository.mapper"})
public class MybatisObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject,"gmtCreate",Date.class,new Date());
        this.strictInsertFill(metaObject,"gmtModified",Date.class,new Date());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject,"gmtModified",Date.class,new Date());
    }

    @Override
    public MetaObjectHandler setFieldValByName(String fieldName, Object fieldVal, MetaObject metaObject) {
        if ("reqstNo".equals(fieldName)) {
            fieldVal = "MAX";
        }
        return MetaObjectHandler.super.setFieldValByName(fieldName, fieldVal, metaObject);
    }

}
