package com.mkyong.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.io.Serializable;
import java.util.List;

/**
 * @author mengka
 * @version 2021/4/25
 * @since
 */
public interface MyBaseMapper<T> extends BaseMapper<T> {

    /**自定义方法*/
    int deleteById(Serializable id);


    /**内置的选装组件*/
    int insertBatchSomeColumn(List<T> entityList);
}
