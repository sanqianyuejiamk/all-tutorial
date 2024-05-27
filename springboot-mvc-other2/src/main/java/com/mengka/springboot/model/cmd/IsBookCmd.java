package com.mengka.springboot.model.cmd;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author mengka
 * @version 1.0
 * @date 2021/5/22 16:18
 */
@Data
@ToString(callSuper = true)
@Accessors(chain = true)
public class IsBookCmd extends SimpleCommand {
}
