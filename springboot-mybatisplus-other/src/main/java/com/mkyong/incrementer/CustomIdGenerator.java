package com.mkyong.incrementer;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.mkyong.utils.TidGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.stereotype.Component;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 自定义ID生成器
 * 仅作为示范
 *
 *
 * 2021-05-05 15:56:31 INFO  c.m.incrementer.CustomIdGenerator - bizKey:com.mkyong.model.User
 * 2021-05-05 15:56:31 INFO  c.m.incrementer.CustomIdGenerator - 为xx生成主键值->:1389851485769543680
 * 2021-05-05 15:56:31 INFO  c.m.incrementer.CustomIdGenerator - bizKey:com.mkyong.model.User
 * 2021-05-05 15:56:31 INFO  c.m.incrementer.CustomIdGenerator - 为xx生成主键值->:1389851485769543681
 * 2021-05-05 15:56:31 INFO  c.m.incrementer.CustomIdGenerator - bizKey:com.mkyong.model.User
 * 2021-05-05 15:56:31 INFO  c.m.incrementer.CustomIdGenerator - 为xx生成主键值->:1389851485773737984
 *
 *
 * @author nieqiuqiu 2019/11/30
 */
@Slf4j
@Component
public class CustomIdGenerator implements IdentifierGenerator {

    private final AtomicLong al = new AtomicLong(1);

    @Override
    public Long nextId(Object entity) {
        //可以将当前传入的class全类名来作为bizKey,或者提取参数来生成bizKey进行分布式Id调用生成.
        String bizKey = entity.getClass().getName();
        log.info("bizKey:{}", bizKey);

        /*
        MetaObject metaObject = SystemMetaObject.forObject(entity);
        String name = (String) metaObject.getValue("name");
        final long id = al.getAndAdd(1);
        log.info("为{}生成主键值->:{}", name, id);
        */

        IdWorker idWorker = new IdWorker();
        final long id = idWorker.nextId();

        log.info("为xx生成主键值->:{}", id);
        return id;
    }
}
