package com.mkyong.t_01;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Builder;
import lombok.Data;
import java.util.Date;

/**
 * @author mengka
 * @version 2021/4/29
 * @since
 */
@Data
@Builder
public class BookDTO {

    private String id;

    private String name;

    private String email2;

    private Date gmtCreate;

    private Date gmtModified;

    private String reqstNo;

    private String addr;
    private String addrId;
}