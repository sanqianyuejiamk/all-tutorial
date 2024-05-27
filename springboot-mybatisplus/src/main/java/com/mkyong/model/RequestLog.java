package com.mkyong.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author mengka
 * @version 2021/4/25
 * @since
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
@TableName(value = "request_log")
public class RequestLog extends BaseDO{

    /**
     *  请求类型
     */
    @TableField(value = "reqst_type")
    private String reqstType;

    /**
     *  业务ID
     */
    @TableField(value = "business_id")
    private String businessId;

    /**
     *  执行耗时
     */
    @TableField(value = "exe_take")
    private Long exeTake;
}
