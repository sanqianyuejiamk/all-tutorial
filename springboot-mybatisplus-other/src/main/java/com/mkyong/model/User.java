package com.mkyong.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName(value = "user")
public class User extends BaseDO implements Serializable {

    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long id;

    @TableField(value = "name")
    private String name;

    @TableField(value = "age")
    private Integer age;

    @TableField(value = "email")
    private String email;

    /**
     * 租户 ID
     */
    @TableField(value = "tenant_id")
    private Long tenantId;

    /**
     *  关联表user_addr
     */
    @TableField(exist = false)
    private String addrName;

    /**
     *  乐观锁
     */
    @Version
    private Integer version;
}
