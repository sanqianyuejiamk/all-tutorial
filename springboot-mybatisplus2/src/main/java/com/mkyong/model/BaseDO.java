package com.mkyong.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import java.util.Date;

/**
 * @author mengka
 * @version 2021/4/25
 * @since
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(exclude = {"gmtCreate","gmtModified","reqstNo"})
@ToString(exclude = {"gmtCreate","gmtModified","reqstNo"})
public class BaseDO {

//    @TableId(value = "id",type = IdType.ASSIGN_ID)
//    private Long id;

    @TableField(value = "gmt_create",fill = FieldFill.INSERT)
    Date gmtCreate;

    @TableField(value = "gmt_modified",fill = FieldFill.INSERT_UPDATE)
    Date gmtModified;

    @TableField(value = "reqst_no")
    String reqstNo;
}
