package com.mengka.springboot.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author huangyy
 * @description
 */
@Data
public class PageResult<T> implements Serializable{

    public int pageNum;

    public int pageSize;

    List<T> result;

    public int count;

    public int maxPage;

    /**
     * 标志
     */
    private boolean success = false;

    /**
     * 错误信息
     */
    private String errorMsg;
}
