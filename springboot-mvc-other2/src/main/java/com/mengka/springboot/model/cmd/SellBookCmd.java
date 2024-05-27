package com.mengka.springboot.model.cmd;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import java.util.Date;

/**
 * @author mengka
 * @version 1.0
 * @date 2021/5/22 19:42
 */
@Data
@ToString(callSuper = true)
@Accessors(chain = true)
public class SellBookCmd extends SimpleCommand{

    private Date sellTime;

    private String orderId;
}
