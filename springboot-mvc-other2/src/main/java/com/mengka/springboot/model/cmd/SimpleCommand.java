package com.mengka.springboot.model.cmd;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.UUID;

/**
 * @author mengka
 * @version 1.0
 * @date 2021/5/22 16:00
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class SimpleCommand extends Command {

    public SimpleCommand(){
        this.setReqstNo(UUID.randomUUID().toString().replace("-",""));
    }

    /**
     *  操作人
     */
    private String operater;

    /**
     *  请求类型
     */
    private String reqstType;

    /**
     *  请求号
     */
    private String reqstNo;

    /**
     *  业务编码
     */
    private String bizNo;
}
