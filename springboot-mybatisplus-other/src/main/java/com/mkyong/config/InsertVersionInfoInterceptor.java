package com.mkyong.config;

import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.mkyong.request.RequestInfoContextHolder;
import com.mkyong.utils.InnerInterceptorUtil;
import com.mkyong.utils.TidGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author mengka
 * @version 2021/4/25
 * @since
 */
@Slf4j
public class InsertVersionInfoInterceptor implements InnerInterceptor {

    @Override
    public boolean willDoUpdate(Executor executor, MappedStatement ms, Object parameter) throws SQLException {
        log.info("---------, sql:{}",ms.getBoundSql(parameter).getSql());
        log.info("---------, 当前approveId:{}", RequestInfoContextHolder.getApplyHolder());
        return true;
    }

    @Override
    public void beforeUpdate(Executor executor, MappedStatement ms, Object parameter) throws SQLException {
        if(ms.getSqlCommandType().equals(SqlCommandType.INSERT)){
            //获取实体对象
            Class<?> clazz = parameter.getClass();
            List<Field> fields = TableInfoHelper.getAllFields(clazz);

            TidGenerator generator = new TidGenerator(10);
            Map<String,Object> map = new HashMap(1);
            map.put("reqstNo",generator.nextTid());
            InnerInterceptorUtil.setFieldValueByMap(fields,parameter,map);

            log.info("beforeUpdate:{}",parameter.toString());
            RequestInfoContextHolder.setApplyHolder(map.get("reqstNo").toString());
        }else if(ms.getSqlCommandType().equals(SqlCommandType.UPDATE)){

        }
    }

}
