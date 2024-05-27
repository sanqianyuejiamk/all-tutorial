package com.mkyong.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.extension.injector.methods.InsertBatchSomeColumn;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author mengka
 * @version 2021/4/25
 * @since
 */
@Configuration
public class MybatisConfig extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
        //增加自定义方法
        methodList.add(new DeleteById());
//        methodList.add()

        /**内置选装组件：批量插入*/
        methodList.add(new InsertBatchSomeColumn(i -> i.getFieldFill() != FieldFill.UPDATE));

        return methodList;
    }

    @Bean
    public Map<String,Object> requestMap(){
        return new ConcurrentHashMap<>(16);
    }

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        mybatisPlusInterceptor.addInnerInterceptor(new InsertVersionInfoInterceptor());


//        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
//        paginationInnerInterceptor.setDbType(DbType.MYSQL);
//
//        InsertVersionInfoInterceptor insertVersionInfoInterceptor = new InsertVersionInfoInterceptor();
//        List<InnerInterceptor> innerInterceptors = new ArrayList<>();
//        innerInterceptors.add(paginationInnerInterceptor);
//        innerInterceptors.add(insertVersionInfoInterceptor);
//        mybatisPlusInterceptor.setInterceptors(innerInterceptors);

        return mybatisPlusInterceptor;
    }

    @Bean
    public String myInterceptor(SqlSessionFactory sqlSessionFactory){

        return "interceptor";
    }
}
